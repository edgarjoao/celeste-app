package com.condominium.user.dao;

import com.condominium.user.dto.UserDTO;
import com.condominium.user.exception.UserException;

/**
 * 
 * @author Edgar Joao
 *
 */
public interface UserDAO {

	public UserDTO loginUser(String username, String password) throws UserException;
	
	public void insertUser(UserDTO dto) throws UserException;
	
	//public List<UserDTO> getUsersList() throws UserException;

	
}
