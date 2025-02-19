package com.tokenlab.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tokenlab.desafio.dto.UsuarioRequestDTO;
import com.tokenlab.desafio.dto.UsuarioResponseDTO;
import com.tokenlab.desafio.model.Usuario;
import com.tokenlab.desafio.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public void criarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
		Usuario usuario = new Usuario(usuarioRequestDTO.getNome(), usuarioRequestDTO.getEmail(),
				usuarioRequestDTO.getSenha());

		this.usuarioRepository.save(usuario);
	}

	public void atualizarSenha(String senha, Long id) {
		Usuario usuario = buscarUsuarioId(id);
		usuario.setSenha(senha);
		usuarioRepository.save(usuario);
	}

	public void removerUsuario(Long id) {
		usuarioRepository.delete(buscarUsuarioId(id));
	}

	
	public UsuarioResponseDTO fazerLogin(String email, String senha) {
		Usuario usuario = buscarUsuarioEmail(email);
		if(usuario.getSenha().equals(senha)) {
			return new UsuarioResponseDTO(usuario.getNome());
		}else {
			throw new IllegalArgumentException("A senha não corresponde ao usuário!!!");
		}
	}
	
	private Usuario buscarUsuarioId(Long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado!!!"));
	}
	private Usuario buscarUsuarioEmail(String email) {
		return usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario não encontrado!!!"));
	}
}
