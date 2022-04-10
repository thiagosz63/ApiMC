package com.tsa.ApiMC.entities.enums;

public enum Perfil {
	ADMIM(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE"); 

	private int cod;
	private String description;

	private Perfil(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}

	public static Perfil toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (Perfil x : Perfil.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("id invalido " + cod);
	}

}
