package com.condominium.permissions.dto;

import java.io.Serializable;
/**
 * 
 * @author rioslore
 *
 */
public class PermissionsModuleDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3536870355440119719L;
	private int tpId;
	private String tpDescription;
	private String shortDesc;	
	
	public int getTpId() {
		return tpId;
	}
	public void setTpId(int tpId) {
		this.tpId = tpId;
	}
	public String getTpDescription() {
		return tpDescription;
	}
	public void setTpDescription(String tpDescription) {
		this.tpDescription = tpDescription;
	}
	public String getShortDesc() {
		return shortDesc;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}	
	
}
