package com.iwork.system.service.impl;

import java.util.Collection;
import java.util.List;

import org.apache.shiro.authz.AuthorizationException;

import com.iwork.system.entity.Permission;
import com.iwork.system.service.PermissionService;

public class PermissionServiceImpl implements PermissionService {

	@Override
	public Permission createPermission(Permission permission) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePermission(Long permissionId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isPermitted(String permission) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPermitted(Permission permission) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean[] isPermitted(String... permissions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean[] isPermitted(List<Permission> permissions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPermittedAll(String... permissions) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPermittedAll(Collection<Permission> permissions) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void checkPermission(String permission)
			throws AuthorizationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkPermission(Permission permission)
			throws AuthorizationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkPermissions(String... permissions)
			throws AuthorizationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkPermissions(Collection<Permission> permissions)
			throws AuthorizationException {
		// TODO Auto-generated method stub
		
	}

}
