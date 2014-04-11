package com.iwork.common.utils.page;


	/**
	 * 
	 * @description SQLServer数据库实现
	 * 
	 */
	public class SqlServerPage implements Dialect {

		public String getPageSql(String sql, int offset, int limit) {
			sql = sql.trim();
			StringBuffer pageSql = new StringBuffer(sql.length() + 100);
			//注意：排序现在默认为Id
			pageSql.append("select * from(select a.*,row_number() over (order by id desc) rownum from( ");
			pageSql.append(sql);
			pageSql.append(") a )b where rownum> " + offset + " and rownum <= " + (offset + limit));
			return pageSql.toString();
		}

}
