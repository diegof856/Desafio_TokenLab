package com.tokenlab.desafio.dto;

public class EventoRequestDTO {

	private Long idUsuario;
	private String horaInicio;
	private String horaTermino;
	private String descricao;
	private String nomeEvento;

	
	public EventoRequestDTO(Long idUsuario, String horaInicio, String horaTermino, String descricao, String nomeEvento) {
		
		this.idUsuario = idUsuario;
		this.horaInicio = horaInicio;
		this.horaTermino = horaTermino;
		this.descricao = descricao;
		this.nomeEvento = nomeEvento;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraTermino() {
		return horaTermino;
	}

	public void setHoraTermino(String horaTermino) {
		this.horaTermino = horaTermino;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}
}
