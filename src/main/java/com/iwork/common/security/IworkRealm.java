package com.iwork.common.security;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwork.system.entity.Permission;
import com.iwork.system.entity.Role;
import com.iwork.system.entity.User;
import com.iwork.system.service.PermissionService;
import com.iwork.system.service.RoleService;
import com.iwork.system.service.UserService;



/***
 * apache realm认证、授权最终执行类，HomeRealm
 * realm是shiro的桥梁，进行数据源配置，shrio提供了常用的realm数据源配置，
 * 如LDAP的JndiLdapRealm，JDBC的JdbcRealm，ini文件的IniRealm，properties文件的PropertiesRealm等，
 * 也可以插入自己的 Realm实现来代表自定义的数据源。此处使用了自定义的IworkRealm进行配置
 * 
 * @author Administrator
 * 
 */
public class IworkRealm extends AuthorizingRealm  {

	    static Logger logger = Logger.getLogger(IworkRealm.class);
	
	    @Autowired
	    private UserService userService = null;
	 
	    public UserService getUserService() {
	    	return userService;
	    }
		public void setUserService(UserService userService) {
			this.userService = userService;
		}
     
		@Autowired
	    private RoleService roleService = null;
		
		public RoleService getRoleService() {
			return roleService;
		}
		public void setRoleService(RoleService roleService) {
			this.roleService = roleService;
		}
		
		@Autowired
		private PermissionService permissionService = null;
		
		public PermissionService getPermissionService() {
			return permissionService;
		}
		public void setPermissionService(PermissionService permissionService) {
			this.permissionService = permissionService;
		}
		
		/**
		 * 授权方法，在配有缓存的情况下，只加载一次。
		 */
	  @Override
	  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
          
		    logger.info("User Login to initialize authorized.....");
		  
			ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
			//1)获取用户信息的所有资料，如权限角色等.
			User user = userService.findByUserName(shiroUser.loginName);
			
			if (user != null) {
				  SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
				  //2)info.setRoles(角色集合);
				  List<Role> roleLists = null ; //roleService.selectList(user.getUserId());
				  for(Role role: roleLists){
                      info.addRole(role.getRoleName());
                      
					  //3)info.setStringPermissions(权限集合);
					  List<Permission>   permissionLists =  null; //permissionService.selectList(role.getRoleId());
					  for(Permission permission: permissionLists){
						  info.addStringPermission(permission.getPermissionName());
					  }
				  }
				  logger.info("User [" + user.getUserName()+ "] logged in successfully.");
				  return info;
		  }else{
			  logger.info("User [" + user.getUserName()+ "] logged in fail.!");
			  return null;
		  }
	  }
		/**
		 * 登陆认证
		 */
		@Override
		protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
	               throws AuthenticationException {
			
			 logger.info("User Login to initialize authenticationToken......");
			User user = null;
		    SimpleAuthenticationInfo info = null;
			// token中储存着输入的用户名和密码
			UsernamePasswordToken upToken = (UsernamePasswordToken)token;
			String username = upToken.getUsername();
			try{
					// 与数据库中用户名和密码进行比对。比对成功则返回info，比对失败则抛出对应信息的异常AuthenticationException
				    user = userService.findByUserName(username);
				    user = new User();
				    user.setUserName("admin");
				    user.setPassword("test");
					// 用户不存在
					if (user == null || "".equals(user) || "null".equals(user)) {
						 logger.info("The user [" + upToken.getUsername() + "] not found!");
						 throw new UnknownAccountException("The user [" + upToken.getUsername() + "] not found!");
					} else if(user.getPassword().equals(new String(upToken.getPassword()))){
					    	//验证用户状态，是否有效用户
						   if (user.isActive()) {
							   logger.info("This user's [" + upToken.getUsername() + "] status is not enabled!");
							   throw new DisabledAccountException("This user's [" + upToken.getUsername() + "] status is not enabled"); 
						   }
				   }else{
					   throw new AuthenticationException();  
				   }
			}catch(Exception e){
				  e.printStackTrace();
			}
			SecurityUtils.getSubject().getSession().setAttribute("user", user);
			info = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());  
		    //return  new SimpleAuthenticationInfo(new ShiroUser(user.getUserName(), user.getRealName()),user.getPassword(), ByteSource.Util.bytes(EncodeUtils.hexDecode (user.getPassword())), getName());	
		    return info;
		}
		
		/**
		 * 更新用户授权信息缓存.
		 */
		public void clearCachedAuthorizationInfo(String principal) {
			SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
			clearCachedAuthorizationInfo(principals);
		}

		/**
		 * 清除所有用户授权信息缓存.
		 */
		public void clearAllCachedAuthorizationInfo() {
			Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
			if (cache != null) {
				for (Object key : cache.keys()) {
					cache.remove(key);
				}
			}
		}
		
		/**
		 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
		 */
		public static class ShiroUser implements Serializable {
			private static final long serialVersionUID = -1373760761780840081L;
			public String loginName;
			public String name;

			public ShiroUser(String loginName, String name) {
				this.loginName = loginName;
				this.name = name;
			}

			public String getName() {
				return name;
			}

			/**
			 * 本函数输出将作为默认的<shiro:principal/>输出.
			 */
			@Override
			public String toString() {
				return loginName;
			}

			/**
			 * 重载equals,只计算loginName;
			 */
			@Override
			public int hashCode() {
				return HashCodeBuilder.reflectionHashCode(this,new String[] { "loginName" });
			}

			/**
			 * 重载equals,只比较loginName
			 */
			@Override
			public boolean equals(Object obj) {
				return EqualsBuilder.reflectionEquals(this, obj,new String[] { "loginName" });
			}
		}
}
