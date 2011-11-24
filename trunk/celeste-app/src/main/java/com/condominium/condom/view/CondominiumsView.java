package com.condominium.condom.view;

import java.io.Serializable;
import java.util.List;

import com.condominium.user.view.UserView;
/**
 * 
 * @author rioslore
 *
 */
public class CondominiumsView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5864521621646674388L;
	private String id;
	private String houseNumber;
	private String typeId;
	private String typeDescription;
	private UserView userView;
	private List<VehiclesView> vehiclesViewList;
	private String condominiumId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getTypeDescription() {
		return typeDescription;
	}
	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}
	public UserView getUserView() {
		return userView;
	}
	public void setUserView(UserView userView) {
		this.userView = userView;
	}
	public List<VehiclesView> getVehiclesViewList() {
		return vehiclesViewList;
	}
	public void setVehiclesViewList(List<VehiclesView> vehiclesViewList) {
		this.vehiclesViewList = vehiclesViewList;
	}
	public String getCondominiumId() {
		return condominiumId;
	}
	public void setCondominiumId(String condominiumId) {
		this.condominiumId = condominiumId;
	}	
	
}
