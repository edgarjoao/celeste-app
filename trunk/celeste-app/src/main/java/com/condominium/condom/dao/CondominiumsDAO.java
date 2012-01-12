package com.condominium.condom.dao;

import java.util.List;

import com.condominium.condom.dto.CondominiumsDTO;
import com.condominium.user.exception.UserException;

public interface CondominiumsDAO {

	public List<CondominiumsDTO> getCondominiumsList() throws UserException;
}
