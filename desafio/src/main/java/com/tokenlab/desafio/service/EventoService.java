package com.tokenlab.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tokenlab.desafio.dto.EventoRequestDTO;
import com.tokenlab.desafio.dto.EventoResponseDTO;
import com.tokenlab.desafio.exceptions.EventoNaoEncontradoException;
import com.tokenlab.desafio.model.Evento;
import com.tokenlab.desafio.repositories.EventoRepository;
import com.tokenlab.desafio.utils.Utils;

@Service
public class EventoService {

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private Utils utils;

	public void salvarEvento(Long id, Evento evento) {
		evento.setUsuario(this.utils.buscarUsuarioId(id));
		this.eventoRepository.save(evento);
	}

	public Page<EventoResponseDTO> listarEventos(Pageable pageable) {
		return eventoRepository.findAll(pageable).map(this::getEventoResponse);
	}

	private EventoResponseDTO getEventoResponse(Evento evento) {
		return new EventoResponseDTO(evento.getIdEvento(),evento.getHoraInicio(), evento.getHoraTermino(), evento.getDescricao());

	}

	public void atualizarEvento(EventoRequestDTO eventoDTO, Long id) {
		Evento evento = buscarEventoPorId(id);
		evento.setDescricao(eventoDTO.getDescricao());
		evento.setHoraInicio(eventoDTO.getHoraInicio());
		evento.setHoraTermino(eventoDTO.getHoraTermino());
		this.eventoRepository.save(evento);

	}

	public void deletarEvento(Long id) {
		this.eventoRepository.delete(buscarEventoPorId(id));
	}

	private Evento buscarEventoPorId(Long id) {
		return eventoRepository.findById(id).orElseThrow(() -> new EventoNaoEncontradoException());
	}

}
