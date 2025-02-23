package com.tokenlab.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tokenlab.desafio.dto.EventoResponseDTO;
import com.tokenlab.desafio.dto.UsuarioRequestDTO;
import com.tokenlab.desafio.dto.UsuarioResponseDTO;
import com.tokenlab.desafio.model.Evento;
import com.tokenlab.desafio.model.Usuario;
import com.tokenlab.desafio.repositories.UsuarioRepository;
import com.tokenlab.desafio.utils.Utils;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private Utils utils;

	public void criarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
		Usuario usuario = new Usuario(usuarioRequestDTO.getNome(), usuarioRequestDTO.getEmail(),
				usuarioRequestDTO.getSenha());

		this.usuarioRepository.save(usuario);
	}

	public void atualizarSenha(String senha, Long id) {
		Usuario usuario = utils.buscarUsuarioId(id);
		usuario.setSenha(senha);
		usuarioRepository.save(usuario);
	}
	
	public void removerUsuario(Long id) {
		usuarioRepository.delete(utils.buscarUsuarioId(id));
	}
	public UsuarioResponseDTO buscarEventos(Long idUsuario) {
		Usuario usuario = utils.buscarUsuarioId(idUsuario);
		UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO(usuario.getIdUsuario(),usuario.getNome());
		for(Evento evento:usuario.getEventos()) {
			usuarioDTO.adicionarEventos(this.transformarEvento(evento));
		}
		return usuarioDTO;
   }

	public UsuarioResponseDTO fazerLogin(String email, String senha) {
		Usuario usuario = buscarUsuarioEmail(email);
		if (usuario.getSenha().equals(senha)) {
			return new UsuarioResponseDTO(usuario.getIdUsuario(),usuario.getNome());
		} else {
			throw new IllegalArgumentException("A senha não corresponde ao usuário!!!");
		}
	}

	private Usuario buscarUsuarioEmail(String email) {
		return usuarioRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Usuario não encontrado!!!"));
	}
	private EventoResponseDTO transformarEvento(Evento evento) {
		return new EventoResponseDTO(evento.getIdEvento(), evento.getHoraInicio(), evento.getHoraTermino(), evento.getDescricao());
	}
	
}
