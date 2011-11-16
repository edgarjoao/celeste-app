package com.condominium.mail;

import com.condominium.mail.exception.MailServiceException;

public interface MailService {

public void sendMail(String from, String to, String subject, String body);
	
	public void sendAlertMail(Exception exception, String folio);
	
	public void sendPDFMail(String to, String folio) throws MailServiceException;
}
