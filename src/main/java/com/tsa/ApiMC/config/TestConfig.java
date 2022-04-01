package com.tsa.ApiMC.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.tsa.ApiMC.service.DBService;
import com.tsa.ApiMC.service.EmailService;
import com.tsa.ApiMC.service.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	DBService dbService;

	@Bean
	public Boolean instantiateDataBase() throws ParseException {
		dbService.instantiateTestDataBase();
		return true;
	}
	
	@Bean
	public EmailService emailService(){
		return new MockEmailService();
	}
}
