package com.iwork.system.service;

import java.util.Collection;
import java.util.List;

import org.apache.shiro.authz.AuthorizationException;

import com.iwork.system.entity.Permission;

public interface PermissionService {

	/***
	 * 创建权限
	 * 
	 * @param permission
	 * @return
	 */
	public Permission createPermission(Permission permission);

	/***
	 * 删除权限
	 * 
	 * @param permissionId
	 */
	public void deletePermission(Long permissionId);

	public boolean isPermitted(String permission);

	public boolean isPermitted(Permission permission);

	public boolean[] isPermitted(String... permissions);

	public boolean[] isPermitted(List<Permission> permissions);

	public boolean isPermittedAll(String... permissions);

	public boolean isPermittedAll(Collection<Permission> permissions);

	public void checkPermission(String permission) throws AuthorizationException;

	public void checkPermission(Permission permission) throws AuthorizationException;

	public void checkPermissions(String... permissions) throws AuthorizationException;

	public void checkPermissions(Collection<Permission> permissions) throws AuthorizationException;

}
