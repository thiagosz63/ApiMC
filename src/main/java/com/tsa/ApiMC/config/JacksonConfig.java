package com.tsa.ApiMC.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsa.ApiMC.entities.PaymentBillet;
import com.tsa.ApiMC.entities.PaymentCard;

@Configuration
public class JacksonConfig {

	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(PaymentCard.class);
				objectMapper.registerSubtypes(PaymentBillet.class);
				super.configure(objectMapper);
			};
		};
		return builder;
	}
}
