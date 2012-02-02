package com.condominium.receipts.view;

import java.io.Serializable;

import com.condominium.condom.view.CondominiumsView;
/**
 * 
 * @author rioslore
 *
 */
public class ReceiptsView implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7180881955523237777L;
	private String id;
	private String date;
	private String amount;
	private String descount;
	private String comments;
	private int userId;
	private CondominiumsView condominiumsView = new CondominiumsView();
	private ReceiptsItemView receiptsItemView = new ReceiptsItemView();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDescount() {
		return descount;
	}
	public void setDescount(String descount) {
		this.descount = descount;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public CondominiumsView getCondominiumsView() {
		return condominiumsView;
	}
	public void setCondominiumsView(CondominiumsView condominiumsView) {
		this.condominiumsView = condominiumsView;
	}
	public ReceiptsItemView getReceiptsItemView() {
		return receiptsItemView;
	}
	public void setReceiptsItemView(ReceiptsItemView receiptsItemView) {
		this.receiptsItemView = receiptsItemView;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}	
	
}
