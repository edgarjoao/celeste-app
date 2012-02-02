package com.condominium.events.dto;

import java.io.Serializable;
import java.util.Date;

import com.condominium.condom.dto.CondominiumsDTO;
import com.condominium.user.dto.UserDTO;

public class EventsDTO implements Serializable {

	private int id;
	private String description;
	private Date date;
	private String status;
	private CondominiumsDTO condominiumsDTO;
	private UserDTO userDTO;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public CondominiumsDTO getCondominiumsDTO() {
		return condominiumsDTO;
	}
	public void setCondominiumsDTO(CondominiumsDTO condominiumsDTO) {
		this.condominiumsDTO = condominiumsDTO;
	}
	public UserDTO getUserDTO() {
		return userDTO;
	}
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}		
}
