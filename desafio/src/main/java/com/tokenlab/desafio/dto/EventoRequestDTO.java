package com.tokenlab.desafio.dto;

import java.time.Instant;

public class EventoRequestDTO {

	private Long id;
	private Instant horaInicio;
	private Instant horaTermino;
	private String descricao;

	
	public EventoRequestDTO(Long id, Instant horaInicio, Instant horaTermino, String descricao) {
		
		this.id = id;
		this.horaInicio = horaInicio;
		this.horaTermino = horaTermino;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Instant horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Instant getHoraTermino() {
		return horaTermino;
	}

	public void setHoraTermino(Instant horaTermino) {
		this.horaTermino = horaTermino;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
