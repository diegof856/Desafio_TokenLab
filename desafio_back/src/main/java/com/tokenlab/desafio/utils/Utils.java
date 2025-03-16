package com.tokenlab.desafio.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tokenlab.desafio.model.Usuario;
import com.tokenlab.desafio.repositories.UsuarioRepository;

@Component
public class Utils {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario buscarUsuarioId(Long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado!!!"));

	}
}
