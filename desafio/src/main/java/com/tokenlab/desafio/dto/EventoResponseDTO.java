package com.tokenlab.desafio.dto;

import java.time.Instant;

public class EventoResponseDTO {

	private Long idEvento;
	private Instant horaInicio;
	private Instant horaTermino;
	private String descricao;

	public EventoResponseDTO(Long idEvento, Instant horaInicio, Instant horaTermino, String descricao) {
		this.idEvento = idEvento;
		this.horaInicio = horaInicio;
		this.horaTermino = horaTermino;
		this.descricao = descricao;
	}
	
	public Long getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
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
