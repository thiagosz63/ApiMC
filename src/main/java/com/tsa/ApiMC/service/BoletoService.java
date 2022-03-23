package com.tsa.ApiMC.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.tsa.ApiMC.entities.PaymentBillet;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PaymentBillet pagto, Date instante) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instante);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
		
	}

}
