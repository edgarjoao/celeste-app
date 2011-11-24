package com.condominium.user.dto;

import java.io.Serializable;
/**
 * 
 * @author rioslore
 *
 */
public class UserDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1826539222724541646L;
	private int roleId;
	private String rolDescription;
	private int userId;
	private String username;
	private String password;
	private String nombre;
	private String apaterno;
	private String amaterno;
	private int telCasa;
	private int telCelular;
	private String email;
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRolDescription() {
		return rolDescription;
	}
	public void setRolDescription(String rolDescription) {
		this.rolDescription = rolDescription;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApaterno() {
		return apaterno;
	}
	public void setApaterno(String apaterno) {
		this.apaterno = apaterno;
	}
	public String getAmaterno() {
		return amaterno;
	}
	public void setAmaterno(String amaterno) {
		this.amaterno = amaterno;
	}
	public int getTelCasa() {
		return telCasa;
	}
	public void setTelCasa(int telCasa) {
		this.telCasa = telCasa;
	}
	public int getTelCelular() {
		return telCelular;
	}
	public void setTelCelular(int telCelular) {
		this.telCelular = telCelular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
	
}
