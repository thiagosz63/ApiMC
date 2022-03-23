package com.tsa.ApiMC.entities;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.tsa.ApiMC.entities.enums.PaymentStatus;

@Entity
@JsonTypeName("pagamentoComBoleto")
public class PaymentBillet extends Payment {

	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimento;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamento;

	public PaymentBillet() {
	}

	public PaymentBillet(Integer id, PaymentStatus status, Request request, Date dataVencimento, Date dataPagamento) {
		super(id, status, request);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	

}
