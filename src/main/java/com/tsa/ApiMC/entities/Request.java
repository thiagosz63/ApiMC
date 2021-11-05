package com.tsa.ApiMC.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Request implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date instante;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "request")
	private Payment payment;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;

	@ManyToOne
	@JoinColumn(name = "enderecoEnrega_id")
	private Address enderecoEntrega;
	@OneToMany(mappedBy = "id.request")
	private Set<OrderItem> itens = new HashSet<>();

	public Request() {
	}

	public Request(Integer id, Date instante, Client client, Address enderecoEntrega) {
		super();
		this.id = id;
		this.instante = instante;
		this.client = client;
		this.enderecoEntrega = enderecoEntrega;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Address getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(Address enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public Set<OrderItem> getItens() {
		return itens;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		return Objects.equals(id, other.id);
	}
}
