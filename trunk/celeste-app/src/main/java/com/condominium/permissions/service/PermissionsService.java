package com.condominium.permissions.service;

import java.util.List;

import com.condominium.permissions.exception.PermissionsException;
import com.condominium.permissions.view.PermissionView;

public interface PermissionsService {

	public List<PermissionView> permissionList(int userId) throws PermissionsException;
	
	public void savePermissions(List<PermissionView> views, int userId) throws PermissionsException;
}
