package com.tokenlab.desafio.exceptions;

public class EmailJaCadastradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmailJaCadastradoException() {
		super("E-mail já cadastrado. Escolha outro.");
	}
	public EmailJaCadastradoException(String mensagem) {
		super(mensagem);
	}
}
