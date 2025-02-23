package com.tokenlab.desafio.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Table(name = "tb_evento")
@Entity(name = "evento")
public class Evento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEvento;

	@NotNull
	private Instant horaInicio;

	@NotNull
	private Instant horaTermino;

	@NotNull
	private String descricao;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "idUsuario", nullable = false)
	private Usuario usuario;

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idEvento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		return Objects.equals(idEvento, other.idEvento);
	}
	
}
