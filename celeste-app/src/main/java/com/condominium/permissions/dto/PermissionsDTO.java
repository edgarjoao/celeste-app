package com.condominium.permissions.dto;

import java.io.Serializable;
/**
 * 
 * @author rioslore
 *
 */
public class PermissionsDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7486984794211651203L;
	private int perId;
	private String perDescription;
	private String shortName;
	private int tpId;
	private boolean selected;
	
	public int getPerId() {
		return perId;
	}
	public void setPerId(int perId) {
		this.perId = perId;
	}
	public String getPerDescription() {
		return perDescription;
	}
	public void setPerDescription(String perDescription) {
		this.perDescription = perDescription;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public int getTpId() {
		return tpId;
	}
	public void setTpId(int tpId) {
		this.tpId = tpId;
	}
	
}
