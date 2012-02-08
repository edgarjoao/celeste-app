package com.condominium.permissions.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condominium.permissions.converter.PermissionsConverter;
import com.condominium.permissions.dao.PermissionsDAO;
import com.condominium.permissions.dto.PermissionsDTO;
import com.condominium.permissions.dto.PermissionsModuleDTO;
import com.condominium.permissions.exception.PermissionsException;
import com.condominium.permissions.service.PermissionsService;
import com.condominium.permissions.view.PermissionView;
import com.condominium.permissions.view.PermissionsView;

@Service("permissionsService")
public class PermissionsServiceImpl implements PermissionsService {

	private static final Logger log = Logger.getLogger(PermissionsServiceImpl.class);
	
	@Autowired
	PermissionsDAO permissionsDAO;	
	
	public List<PermissionView> permissionList(int userId) throws PermissionsException {
		List<PermissionView> permissionViews = new ArrayList<PermissionView>(0);
		try{			
			PermissionsConverter converter = new PermissionsConverter();
			List<PermissionsDTO> permissionsDTOs = new ArrayList<PermissionsDTO>(0);
			List<PermissionsModuleDTO> modules = permissionsDAO.getPermissionsModuleList();					
			for (PermissionsModuleDTO mDto : modules) {			
				PermissionView view = new PermissionView();
				view.setShortDesc(mDto.getShortDesc());
				view.setTpDescription(mDto.getTpDescription());			
				permissionsDTOs = permissionsDAO.getPermissionsDTOList(mDto.getTpId());
				List<PermissionsDTO> pDTOs = new ArrayList<PermissionsDTO>(0);
				for(PermissionsDTO pDto: permissionsDTOs){										
					pDto.setSelected(permissionsDAO.hasPermission(userId, pDto.getPerId()));
					pDTOs.add(pDto);
				}				
				view.setPermissionsViews(converter.convertDTOsToViews(pDTOs));
				permissionViews.add(view);
			}		
		}catch(PermissionsException pException){
			log.error(pException);
			PermissionsException permissionsException = new PermissionsException(pException, PermissionsException.LAYER_SERVICE, PermissionsException.ACTION_SELECT);
			throw permissionsException;
		}		
		return permissionViews;
	}

	public void savePermissions(List<PermissionView> views, int userId) throws PermissionsException {		
		try{
			//Se eliminan todos los permisos del usuario.
			permissionsDAO.deletePermissions(userId);	
			//Se recorre la lista para verificar los permisos seleccionados.
			for (PermissionView view : views) {
				for(PermissionsView v : view.getPermissionsViews()){
					if(v.isSelected()){
						permissionsDAO.addUserPermissions(userId, v.getPerId());
					}
				}
			}
		}catch(PermissionsException pException){
			log.error(pException);
			PermissionsException permissionsException = new PermissionsException(pException, PermissionsException.LAYER_SERVICE, PermissionsException.ACTION_INSERT);
			throw permissionsException;
		}	
	}
	
}
