package com.condominium.user.service;

import com.condominium.user.exception.UserException;
import com.condominium.user.view.UserView;

public interface UserService {

	public UserView loginUser(String username, String password) throws UserException;
}
