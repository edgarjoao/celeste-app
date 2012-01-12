package com.condominium.condom.service;

import java.util.List;

import javax.faces.model.SelectItem;

import com.condominium.condom.view.CondominiumsView;
import com.condominium.user.exception.UserException;

public interface CondominiumsService {
	
	public List<CondominiumsView> getCondominiumsList() throws UserException;
	
	public List<SelectItem> getCondominiumsItems() throws UserException;

}
