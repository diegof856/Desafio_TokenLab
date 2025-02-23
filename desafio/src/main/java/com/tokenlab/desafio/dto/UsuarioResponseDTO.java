package com.tokenlab.desafio.dto;

import java.util.ArrayList;
import java.util.List;

public class UsuarioResponseDTO {
	private Long idUsuario;
	private String nome;

	private List<EventoResponseDTO> eventos = new ArrayList<>();

	public UsuarioResponseDTO() {

	}

	public UsuarioResponseDTO(Long idUsuario, String nome) {
		this.idUsuario = idUsuario;
		this.nome = nome;
	}

	public List<EventoResponseDTO> getEventos() {
		return eventos;
	}
	

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public void adicionarEventos(EventoResponseDTO evento) {
		this.eventos.add(evento);
	}

}
