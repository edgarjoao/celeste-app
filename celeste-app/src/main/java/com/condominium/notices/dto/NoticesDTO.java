package com.condominium.notices.dto;

import java.util.Date;

public class NoticesDTO {

	private int id;
	private Date date;
	private String title;
	private String detail;
	private int typeNoticeId;
	private String typeNoticeDescription;
	
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
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getTypeNoticeId() {
		return typeNoticeId;
	}
	public void setTypeNoticeId(int typeNoticeId) {
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
