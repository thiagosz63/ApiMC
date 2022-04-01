package com.tsa.ApiMC.service;

import org.springframework.mail.SimpleMailMessage;

import com.tsa.ApiMC.entities.Order;

public interface EmailService {

	void sendOrderConfirmationEmail(Order obj);

	void sendEmail(SimpleMailMessage msg);
}
