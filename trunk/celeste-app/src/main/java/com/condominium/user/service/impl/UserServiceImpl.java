package com.condominium.user.service.impl;

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
		
}
