package com.tokenlab.desafio.model;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "tb_evento")
@Entity(name = "evento")
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEvento;
	private Instant horaInicio;
	private Instant horaTermino;
	private String descricao;
	
	
	
	public Evento() {
		
	}


	public Evento(Long idEvento, Instant horaInicio, Instant horaTermino, String descricao) {
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
