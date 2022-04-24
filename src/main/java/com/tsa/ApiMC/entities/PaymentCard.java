package com.tsa.ApiMC.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.tsa.ApiMC.entities.enums.PaymentStatus;

@Entity
@Table(name = "`payment_card`")
@JsonTypeName("pagamentoComCartao")
public class PaymentCard extends Payment {

	private static final long serialVersionUID = 1L;

	private Integer numeroDeParcelas;

	public PaymentCard() {
	}

	public PaymentCard(Integer id, PaymentStatus status, Order order, Integer numeroDeParcelas) {
		super(id, status, order);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	

}
