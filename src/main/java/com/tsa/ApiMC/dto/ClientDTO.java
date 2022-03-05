package com.tsa.ApiMC.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.tsa.ApiMC.entities.Client;

public class ClientDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message = "Preenchimento Obligatorio")
	@Size(min = 5, max = 120, message = "O campo deve conter entre 5 e 1200 caracteres")
	private String name;
	@NotEmpty(message = "Preenchimento Obligatorio")
	@Email(message = "Digite um email valido")
	private String email;
	
	public ClientDTO() {
	}
	public ClientDTO(Client obj) {
		
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
		ClientDTO other = (ClientDTO) obj;
		return Objects.equals(id, other.id);
	}
}
