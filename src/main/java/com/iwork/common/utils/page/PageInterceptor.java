package com.iwork.common.utils.page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.parameter.DefaultParameterHandler;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;

import com.iwork.common.utils.ReflectionUtils;

/**
 *  MyBatis分页拦截器，用于拦截需要进行分页查询的操作，然后对其进行分页处理。 
 * 
 *  利用拦截器实现Mybatis分页的原理： 
 *  要利用JDBC对数据库进行操作就必须要有一个对应的Statement对象，Mybatis在执行Sql语句前就会产生一个包含Sql语句的Statement对象，而且对应的Sql语句 
 *  是在Statement之前产生的，所以我们就可以在它生成Statement之前对用来生成Statement的Sql语句下手。在Mybatis中Statement语句是通过RoutingStatementHandler对象的 
 *  prepare方法生成的。所以利用拦截器实现Mybatis分页的一个思路就是拦截StatementHandler接口的prepare方法，然后在拦截器方法中把Sql语句改成对应的分页查询Sql语句，之后再调用 
 *  StatementHandler对象的prepare方法，即调用invocation.proceed()。 
 *  对于分页而言，在拦截器里面我们还需要做的一个操作就是统计满足当前条件的记录一共有多少，这是通过获取到了原始的Sql语句后，把它改为对应的统计语句再利用Mybatis封装好的参数和设 
 *  置参数的功能把Sql语句中的参数进行替换，之后再执行查询记录数的Sql语句进行总记录数的统计。 
 *    
 *  对于实现自己的Interceptor而言有两个很重要的注解，一个是@Intercepts，其值是一个@Signature数组。
 *  @Intercepts用于表明当前的对象是一个Interceptor，而@Signature则表明要拦截的接口、方法以及对应的参数类型
 *    
 *  第一个@Signature我们定义了该Interceptor将拦截Executor接口中参数类型为MappedStatement、Object、RowBounds和ResultHandler的query方法；
 *  第二个@Signature我们定义了该Interceptor将拦截StatementHandler中参数类型为Connection的prepare方法
 *  
 *  
 *  对于StatementHandler其实只有两个实现类，一个是RoutingStatementHandler，另一个是抽象类BaseStatementHandler，  
 *  BaseStatementHandler有三个子类，分别是SimpleStatementHandler，PreparedStatementHandler和CallableStatementHandler，  
 *  SimpleStatementHandler是用于处理Statement的，PreparedStatementHandler是处理PreparedStatement的，而CallableStatementHandler是  
 *  处理CallableStatement的。Mybatis在进行Sql语句处理的时候都是建立的RoutingStatementHandler，而在RoutingStatementHandler里面拥有一个  
 *  StatementHandler类型的delegate属性，RoutingStatementHandler会依据Statement的不同建立对应的BaseStatementHandler，即SimpleStatementHandler、  
 *  PreparedStatementHandler或CallableStatementHandler，在RoutingStatementHandler里面所有StatementHandler接口方法的实现都是调用的delegate对应的方法。  
 *  我们在PageInterceptor类上已经用@Signature标记了该Interceptor只拦截StatementHandler接口的prepare方法，又因为Mybatis只有在建立RoutingStatementHandler的时候  
 *  是通过Interceptor的plugin方法进行包裹的，所以我们这里拦截到的目标对象肯定是RoutingStatementHandler对象。
 *  
 *       
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PageInterceptor implements Interceptor {

	private final static Log log = LogFactory.getLog(PageInterceptor.class);

	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		//BoundSql boundSql = statementHandler.getBoundSql();
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler);
		RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");
		if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
			//return invocation.proceed();
		}
		  RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();  
		  //通过反射获取到当前RoutingStatementHandler对象的delegate属性  
	      StatementHandler delegate = (StatementHandler)ReflectionUtils.getFieldValue(handler, "delegate");  
	      //获取到当前StatementHandler的 boundSql，这里不管是调用handler.getBoundSql()还是直接调用delegate.getBoundSql()结果是一样的，因为之前已经说过了  
	      //RoutingStatementHandler实现的所有StatementHandler接口方法里面都是调用的delegate对应的方法。  
	      BoundSql boundSql = delegate.getBoundSql();  
	      //拿到当前绑定Sql的参数对象，就是我们在调用对应的Mapper映射语句时所传入的参数对象  
	      Object obj = boundSql.getParameterObject();  
	      Page<?> page=null;
	      //这里我们简单的通过传入的是Page对象就认定它是需要进行分页操作的。  
	      if (obj instanceof Page<?>) {  
	           page = (Page<?>) obj;  
	      }  
		 Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
		 Dialect.Type databaseType = null;
		 try {
			 databaseType = Dialect.Type.valueOf(configuration.getVariables().getProperty("dialect").toUpperCase());
		 } catch (Exception e) {
			 log.error(e);
		 }
		 if (databaseType == null) {
			throw new RuntimeException("the value of the dialect property in mybatis-config.xml is not defined : " + configuration.getVariables().getProperty("dialect"));
		}
		Dialect dialect = null;
		switch (databaseType) {
		case MYSQL:
			dialect = new MySql5Page();
			break;
		case MSSQL:
			dialect = new SqlServerPage();
			break;
		case ORACLE:
			dialect = new OraclePage();
			break;
		default:
			dialect = new MySql5Page();
		}
		String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
		metaStatementHandler.setValue("delegate.boundSql.sql", dialect.getPageSql(originalSql, (page.getPageNo()-1)*page.getPageSize(), page.getPageSize()));
		metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
		metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
		
		 //通过反射获取delegate父类BaseStatementHandler的mappedStatement属性  
        MappedStatement mappedStatement = (MappedStatement)ReflectionUtils.getFieldValue(delegate, "mappedStatement");  
        //拦截到的prepare方法参数是一个Connection对象  
        Connection connection = (Connection)invocation.getArgs()[0];  
        //获取当前要执行的Sql语句，也就是我们直接在Mapper映射语句中写的Sql语句  
        this.setTotalRecord(page,mappedStatement, connection); 
		return invocation.proceed();
	}
	
	/***
	 * plugin方法是拦截器用于封装目标对象的，通过该方法我们可以返回目标对象本身，也可以返回一个它的代理.
	 * 当返回的是代理的时候我们可以对其中的方法进行拦截来调用intercept方法
	 */
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/***
	 * 用于在Mybatis配置文件中指定一些属性
	 */
	public void setProperties(Properties properties) {
	}
	/** 
     * 给当前的参数对象page设置总记录数 
     * 
     * @param page Mapper映射语句对应的参数对象 
     * @param mappedStatement Mapper映射语句 
     * @param connection 当前的数据库连接 
     */  
    private void setTotalRecord(Page<?> page, MappedStatement mappedStatement, Connection connection) {  
       //获取对应的BoundSql，这个BoundSql其实跟我们利用StatementHandler获取到的BoundSql是同一个对象。  
       //delegate里面的boundSql也是通过mappedStatement.getBoundSql(paramObj)方法获取到的。  
       BoundSql boundSql = mappedStatement.getBoundSql(page);  
       //获取到我们自己写在Mapper映射语句中对应的Sql语句  
       String sql = boundSql.getSql();  
       //通过查询Sql语句获取到对应的计算总记录数的sql语句  
       String countSql = "select count(0) from ("  + sql+  ") as tmp_count" ;  //记录统计     
       
      //通过BoundSql获取对应的参数映射  
       List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();  
       //利用Configuration、查询记录数的Sql语句countSql、参数映射关系parameterMappings和参数对象page建立查询记录数对应的BoundSql对象。  
       BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, page);  
       //通过mappedStatement、参数对象page和BoundSql对象countBoundSql建立一个用于设定参数的ParameterHandler对象  
       ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, page, countBoundSql);  
       //通过connection建立一个countSql对应的PreparedStatement对象。  
       PreparedStatement pstmt = null;  
       ResultSet rs = null;  
       try {  
           pstmt = connection.prepareStatement(countSql);  
           //通过parameterHandler给PreparedStatement对象设置参数  
           parameterHandler.setParameters(pstmt);  
           //之后就是执行获取总记录数的Sql语句和获取结果了。  
           rs = pstmt.executeQuery();  
           if (rs.next()) {  
              int totalRecord = rs.getInt(1);  
              //给当前的参数page对象设置总记录数  
              page.setTotalRecord(totalRecord);  
           }  
       } catch (SQLException e) {  
           e.printStackTrace();  
       } finally {  
           try {  
              if (rs != null)  
                  rs.close();  
               if (pstmt != null)  
                  pstmt.close();  
           } catch (SQLException e) {  
              e.printStackTrace();  
           }  
       }  
    }  
}
