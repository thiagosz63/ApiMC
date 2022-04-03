package com.tsa.ApiMC.service;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.tsa.ApiMC.entities.Order;

public interface EmailService {

	//versao texto plano
	void sendOrderConfirmationEmail(Order obj);

	void sendEmail(SimpleMailMessage msg);
	
	//versao html
	void sendOrderConfirmationHtmlEmail(Order obj);
	
	void sendHtmlEmail(MimeMessage msg);
}
