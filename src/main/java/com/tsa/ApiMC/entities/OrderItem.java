package com.tsa.ApiMC.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonIgnore
	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();
	private Double desconto;
	private Integer quantidade;
	private Double preco;

	public OrderItem() {
	}

	public OrderItem(Request request, Product product, Double desconto, Integer quantidade, Double preco) {
		super();
		id.setRequest(request);
		id.setProduct(product);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	public Double getSubTotal() {
		return (preco - desconto) * quantidade;
	}

	@JsonIgnore
	public Request getRequest() {
		return id.getRequest();
	}

	public Product getProduct() {
		return id.getProduct();
	}

	public OrderItemPK getId() {
		return id;
	}

	public void setId(OrderItemPK id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
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
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}
}
