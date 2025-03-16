package com.tokenlab.desafio.exceptions;

public class InformacoesEventoVazias extends RuntimeException {


	private static final long serialVersionUID = 1L;
	
	public InformacoesEventoVazias() {
		super("As informações do evento não podem ser vazias");
	}
	public InformacoesEventoVazias(String mensagem) {
		super(mensagem);
	}

}
