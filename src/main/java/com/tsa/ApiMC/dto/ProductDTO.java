package com.tsa.ApiMC.dto;

import java.io.Serializable;
import java.util.Objects;

import com.tsa.ApiMC.entities.Product;

public class ProductDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private double price;
	
	public ProductDTO() {
	}

	public ProductDTO(Product obj) {
	
		id = obj.getId();
		name = obj.getName();
		price = obj.getPrice();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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
		ProductDTO other = (ProductDTO) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
