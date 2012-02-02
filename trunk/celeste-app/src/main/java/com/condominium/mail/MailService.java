package com.condominium.mail;

/**
 * 
 * @author joao
 *
 */
public interface MailService {

public void sendMail(String from, String to, String subject, String body);
	
	public void sendAlertMail(String msg);
	
	public void sendErrorAlertMail(Exception exception, String msg);
	
}
