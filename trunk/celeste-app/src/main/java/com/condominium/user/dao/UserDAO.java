package com.condominium.user.dao;

import java.util.List;

import javax.faces.model.SelectItem;

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
	
	public UserDTO getUserById(int userId) throws UserException;
	
	public void editUser(UserDTO userDTO) throws UserException;
	
	public List<SelectItem> getRoles() throws UserException;

	
}
