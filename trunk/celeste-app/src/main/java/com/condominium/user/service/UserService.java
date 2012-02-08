package com.condominium.user.service;

import java.util.List;

import javax.faces.model.SelectItem;

import com.condominium.user.exception.UserException;
import com.condominium.user.view.UserView;

public interface UserService {

	public UserView loginUser(String username, String password) throws UserException;
	
	public UserView getUserById(int userId) throws UserException;
	
	public List<SelectItem> getRoles() throws UserException;
	
	public void editUser(UserView userView) throws UserException;
}
