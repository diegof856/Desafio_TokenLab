package com.tokenlab.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.tokenlab.desafio.dto.EventoResponseDTO;
import com.tokenlab.desafio.dto.UsuarioRequestDTO;
import com.tokenlab.desafio.dto.UsuarioResponseDTO;
import com.tokenlab.desafio.exceptions.EmailJaCadastradoException;
import com.tokenlab.desafio.exceptions.SenhaIncorretaException;
import com.tokenlab.desafio.exceptions.UsuarioNaoEncontradoException;
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
		try {
		    usuarioRepository.save(usuario);
		} catch (DataIntegrityViolationException e) {
		    throw new EmailJaCadastradoException();
		}
	}

	public void atualizarSenha(String senha, String email) {
		Usuario usuario = buscarUsuarioEmail(email);
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
	    
	    if (!usuario.getSenha().equals(senha)) {
	        throw new SenhaIncorretaException("A senha não corresponde ao usuário.");
	    }
	    
	    return new UsuarioResponseDTO(usuario.getIdUsuario(), usuario.getNome());
	}

	private Usuario buscarUsuarioEmail(String email) {
			return usuarioRepository.findByEmail(email)
			.orElseThrow(() -> new UsuarioNaoEncontradoException());
		
	}
	private EventoResponseDTO transformarEvento(Evento evento) {
		return new EventoResponseDTO(evento.getIdEvento(), evento.getHoraInicio(), evento.getHoraTermino(), evento.getDescricao(), evento.getNomeEvento());
	}
	
}
