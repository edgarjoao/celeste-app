package com.condominium.receipts.dto;

import java.io.Serializable;
import java.util.Date;

import com.condominium.condom.dto.CondominiumsDTO;
/**
 * 
 * @author rioslore
 *
 */
public class ReceiptsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6150558277603008747L;
	private int id;
	private Date date;
	private double amount;
	private double descount;
	private String comments;
	private int catId;
	private int condId;
	private CondominiumsDTO condominiumsDTO;
	private ReceiptsItemDTO receiptsItemDTO;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getDescount() {
		return descount;
	}
	public void setDescount(double descount) {
		this.descount = descount;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public CondominiumsDTO getCondominiumsDTO() {
		return condominiumsDTO;
	}
	public void setCondominiumsDTO(CondominiumsDTO condominiumsDTO) {
		this.condominiumsDTO = condominiumsDTO;
	}
	public ReceiptsItemDTO getReceiptsItemDTO() {
		return receiptsItemDTO;
	}
	public void setReceiptsItemDTO(ReceiptsItemDTO receiptsItemDTO) {
		this.receiptsItemDTO = receiptsItemDTO;
	}
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	public int getCondId() {
		return condId;
	}
	public void setCondId(int condId) {
		this.condId = condId;
	}
			
}
