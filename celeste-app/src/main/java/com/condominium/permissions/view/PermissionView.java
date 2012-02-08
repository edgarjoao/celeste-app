package com.condominium.permissions.view;

import java.util.List;

public class PermissionView {

	private String tpDescription;
	private String shortDesc;
	private List<PermissionsView> permissionsViews;
	
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
	public List<PermissionsView> getPermissionsViews() {
		return permissionsViews;
	}
	public void setPermissionsViews(List<PermissionsView> permissionsViews) {
		this.permissionsViews = permissionsViews;
	}
		
}
