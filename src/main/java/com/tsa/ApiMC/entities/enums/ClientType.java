package com.tsa.ApiMC.entities.enums;

public enum ClientType {
	PessoaFisica(1, "Pessoa Fisica"), 
	PessoaJuridica(2, "Pessoa Juridica");

	private int cod;
	private String description;

	private ClientType(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}

	public static ClientType toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (ClientType x : ClientType.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("id invalido " + cod);
	}

}
