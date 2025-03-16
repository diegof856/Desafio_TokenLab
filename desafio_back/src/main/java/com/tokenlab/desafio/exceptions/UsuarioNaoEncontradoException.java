package com.tokenlab.desafio.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UsuarioNaoEncontradoException() {
		super("Usuario não encontrado");
	}
	
	public UsuarioNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

}
