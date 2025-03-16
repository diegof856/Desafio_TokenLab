package com.tokenlab.desafio.exceptions;

public class SenhaIncorretaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SenhaIncorretaException() {
		super("Senha incorreta");
	}
	public SenhaIncorretaException(String mensagem) {
		super(mensagem);
	}
}
