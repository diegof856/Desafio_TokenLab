package com.tokenlab.desafio.infra;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tokenlab.desafio.exceptions.EmailJaCadastradoException;
import com.tokenlab.desafio.exceptions.EventoNaoEncontradoException;
import com.tokenlab.desafio.exceptions.SenhaIncorretaException;
import com.tokenlab.desafio.exceptions.UsuarioNaoEncontradoException;

@RestControllerAdvice
public class RestExceptionHandler {
	@ExceptionHandler(UsuarioNaoEncontradoException.class)
	private ResponseEntity<Object> usuarioNaoEncontrado(UsuarioNaoEncontradoException exception){
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.NOT_FOUND.value());
		body.put("mensagem", exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
	}
	
	@ExceptionHandler(SenhaIncorretaException.class)
	private ResponseEntity<Object> senhaIncorreta(SenhaIncorretaException exception){
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.FORBIDDEN.value());
		body.put("mensagem", exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
	}
	@ExceptionHandler(EmailJaCadastradoException.class)
	private ResponseEntity<Object> emailJaCadastrado(EmailJaCadastradoException exception){
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.FORBIDDEN.value());
		body.put("mensagem", exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
	}
	@ExceptionHandler(EventoNaoEncontradoException.class)
	private ResponseEntity<Object> eventoNaoEncontrado(EventoNaoEncontradoException exception){
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.NOT_FOUND.value());
		body.put("mensagem", exception.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
	}
}
