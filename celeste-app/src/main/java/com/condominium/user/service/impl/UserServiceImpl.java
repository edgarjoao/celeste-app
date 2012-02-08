package com.condominium.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condominium.user.converter.UserConverter;
import com.condominium.user.dao.UserDAO;
import com.condominium.user.exception.UserException;
import com.condominium.user.service.UserService;
import com.condominium.user.view.UserView;
/**
 * 
 * @author Edgar Joao
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;
	
	public UserView loginUser(String username, String password) throws UserException {				
		UserView view = null;		
		try{
			UserConverter converter = new UserConverter();
			view = converter.convertDTOToView(userDAO.loginUser(username, password));
		}catch(UserException userException){
			throw userException;
		}		
		return view;
	}

	public UserView getUserById(int userId) throws UserException {
		UserView view = null;		
		try{
			UserConverter converter = new UserConverter();
			view = converter.convertDTOToView(userDAO.getUserById(userId));
		}catch(UserException userException){
			throw userException;
		}		
		return view;
	}

	public List<SelectItem> getRoles() throws UserException {
		List<SelectItem> list = new ArrayList<SelectItem>(0);
		try{
			list = userDAO.getRoles();
		}catch(UserException userException){
			throw userException;
		}
		return list;
	}

	public void editUser(UserView userView) throws UserException {
		try{
		UserConverter converter = new UserConverter();
		this.userDAO.editUser(converter.convertViewToDTO(userView));
		}catch(UserException userException){
			throw userException;
		}
	}
		
}