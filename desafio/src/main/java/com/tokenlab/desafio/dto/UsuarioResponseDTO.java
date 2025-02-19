package com.tokenlab.desafio.dto;

public class UsuarioResponseDTO {

	private String nome;

	public UsuarioResponseDTO(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
