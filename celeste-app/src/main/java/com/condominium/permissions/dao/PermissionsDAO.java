package com.condominium.permissions.dao;

import java.util.List;

import com.condominium.permissions.dto.PermissionsDTO;
import com.condominium.permissions.dto.PermissionsModuleDTO;
import com.condominium.permissions.exception.PermissionsException;

public interface PermissionsDAO {

	public List<PermissionsModuleDTO> getPermissionsModuleList() throws PermissionsException;
	public List<PermissionsDTO> getPermissionsDTOList(int tpId) throws PermissionsException;
	public boolean hasPermission(int usrId, int perId) throws PermissionsException;	
	public void addUserPermissions(int userId, int perId) throws PermissionsException;
	public void deletePermissions(int userId) throws PermissionsException;
}
