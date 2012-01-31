package com.condominium.mail.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.condominium.mail.MailService;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
    private JavaMailSender mailSender;
    @Autowired
    private SimpleMailMessage simpleMailMessage;
    
	public void sendMail(String from, String to, String subject, String body) {
		
	}
	
	public void sendAlertMail(String msg) {
		SimpleMailMessage mailMessage = new SimpleMailMessage(simpleMailMessage);
		StringBuilder mailMessageBuilder = null;
		mailMessageBuilder = new StringBuilder(0);			
		mailMessageBuilder.append(" Mensaje: ");
		mailMessageBuilder.append(msg);
		mailMessage.setSubject("Alerta - " + msg);
        mailMessage.setText(mailMessageBuilder.toString());        
        mailSender.send(mailMessage); 		
	}
	
	public void sendErrorAlertMail(Exception exception, String msg) {
		SimpleMailMessage mailMessage = new SimpleMailMessage(simpleMailMessage);
		StringBuilder mailMessageBuilder = null;
		mailMessageBuilder = new StringBuilder(0);			
		mailMessageBuilder.append(" Mensaje: ");
		mailMessageBuilder.append(exception.getMessage());
		mailMessage.setSubject("Alerta - Error Ocurrido: " + msg);
        mailMessage.setText(mailMessageBuilder.toString());        
        mailSender.send(mailMessage); 		
	}



}
