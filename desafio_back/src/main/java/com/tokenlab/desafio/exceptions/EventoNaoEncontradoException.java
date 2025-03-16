package com.tokenlab.desafio.exceptions;

public class EventoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EventoNaoEncontradoException() {
		super("Evento não encontrado");
	}
	public EventoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
}
