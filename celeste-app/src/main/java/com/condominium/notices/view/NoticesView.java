package com.condominium.notices.view;

import java.io.Serializable;
/**
 * 
 * @author rioslore
 *
 */
public class NoticesView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8537801151403192857L;
	
	private String id;
	private String date;
	private String title;
	private String detail;
	private String typeNoticeId;
	private String typeNoticeDescription;
	
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
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getTypeNoticeId() {
		return typeNoticeId;
	}
	public void setTypeNoticeId(String typeNoticeId) {
		this.typeNoticeId = typeNoticeId;
	}
	public String getTypeNoticeDescription() {
		return typeNoticeDescription;
	}
	public void setTypeNoticeDescription(String typeNoticeDescription) {
		this.typeNoticeDescription = typeNoticeDescription;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}	
	
}
