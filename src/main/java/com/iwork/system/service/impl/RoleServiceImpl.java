package com.iwork.system.service.impl;

import java.util.Collection;
import java.util.List;

import org.apache.shiro.authz.AuthorizationException;

import com.iwork.system.entity.Role;
import com.iwork.system.service.RoleService;

public class RoleServiceImpl implements RoleService {

	@Override
	public Role createRole(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRole(Long roleId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void correlationPermissions(Long roleId, Long... permissionIds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasRole(String roleIdentifier) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean[] hasRoles(List<String> roleIdentifiers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasAllRoles(Collection<String> roleIdentifiers) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void checkRole(String roleIdentifier) throws AuthorizationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkRoles(Collection<String> roleIdentifiers)
			throws AuthorizationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkRoles(String... roleIdentifiers)
			throws AuthorizationException {
		// TODO Auto-generated method stub
		
	}

}
