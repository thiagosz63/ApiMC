package com.tsa.ApiMC.entities;

import javax.persistence.Entity;

import com.tsa.ApiMC.entities.enums.PaymentStatus;

@Entity
public class PaymentCard extends Payment {

	private static final long serialVersionUID = 1L;

	private Integer numeroDeParcelas;

	public PaymentCard() {
	}

	public PaymentCard(Integer id, PaymentStatus status, Request request, Integer numeroDeParcelas) {
		super(id, status, request);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	

}
