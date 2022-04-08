package com.tsa.ApiMC.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.tsa.ApiMC.service.validation.ClientInsert;
@ClientInsert
public class ClientNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotEmpty(message = "Preenchimento Obligatorio")
	@Size(min = 5, max = 120, message = "O campo deve conter entre 5 e 120 caracteres")
	private String name;
	@NotEmpty(message = "Preenchimento Obligatorio")
	@Email(message = "Digite um email valido")
	private String email;
	@NotEmpty(message = "Preenchimento Obligatorio")
	private String cpfOuCnpj;
	private Integer type;
	@NotEmpty(message = "Preenchimento Obligatorio")
	private String senha;

	@NotEmpty(message = "Preenchimento Obligatorio")
	private String logradouro;
	@NotEmpty(message = "Preenchimento Obligatorio")
	private String number;
	private String complemento;
	private String bairro;
	@NotEmpty(message = "Preenchimento Obligatorio")
	private String cep;

	@NotEmpty(message = "Preenchimento Obligatorio")
	private String fone1;
	private String fone2;
	private String fone3;

	private Integer cityId;

	public ClientNewDTO() {
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

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getFone1() {
		return fone1;
	}

	public void setFone1(String fone1) {
		this.fone1 = fone1;
	}

	public String getFone2() {
		return fone2;
	}

	public void setFone2(String fone2) {
		this.fone2 = fone2;
	}

	public String getFone3() {
		return fone3;
	}

	public void setFone3(String fone3) {
		this.fone3 = fone3;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
