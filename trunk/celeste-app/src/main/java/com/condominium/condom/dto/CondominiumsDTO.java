package com.condominium.condom.dto;

import java.io.Serializable;
import java.util.List;

import com.condominium.user.dto.UserDTO;
/**
 * 
 * @author rioslore
 *
 */
public class CondominiumsDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6301337009513659983L;
	private int id;
	private int houseNumber;
	private int typeId;
	private String typeDescription;
	private UserDTO userDTO;
	private List<VehiclesDTO> vehiclesViewList;	
	private int condominiumId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeDescription() {
		return typeDescription;
	}
	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}
	public UserDTO getUserDTO() {
		return userDTO;
	}
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	public List<VehiclesDTO> getVehiclesViewList() {
		return vehiclesViewList;
	}
	public void setVehiclesViewList(List<VehiclesDTO> vehiclesViewList) {
		this.vehiclesViewList = vehiclesViewList;
	}
	public int getCondominiumId() {
		return condominiumId;
	}
	public void setCondominiumId(int condominiumId) {
		this.condominiumId = condominiumId;
	}	
}
