package com.iwork.system.service;

import java.util.Collection;
import java.util.List;

import org.apache.shiro.authz.AuthorizationException;

import com.iwork.system.entity.Role;

public interface RoleService {
	
	public Role createRole(Role role);

	public void deleteRole(Long roleId);

	/***
	 * 添加角色-权限之间关系
	 * @param roleId
	 * @param permissionIds
	 */
	public void correlationPermissions(Long roleId, Long... permissionIds);

	/***
	 * 移除角色-权限之间关系
	 * @param roleId
	 * @param permissionIds
	 */
	public void uncorrelationPermissions(Long roleId, Long... permissionIds);

	
	public boolean hasRole(String roleIdentifier);
	
	public boolean[] hasRoles(List<String> roleIdentifiers);
	
	public boolean hasAllRoles(Collection<String> roleIdentifiers);
	
	public void checkRole(String roleIdentifier) throws AuthorizationException;
	
	public void checkRoles(Collection<String> roleIdentifiers) throws AuthorizationException;

	public void checkRoles(String... roleIdentifiers) throws AuthorizationException;
	
	
}
