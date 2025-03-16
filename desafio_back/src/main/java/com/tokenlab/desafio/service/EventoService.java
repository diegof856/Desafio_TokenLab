package com.tokenlab.desafio.service;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tokenlab.desafio.dto.EventoRequestDTO;
import com.tokenlab.desafio.exceptions.EventoNaoEncontradoException;
import com.tokenlab.desafio.exceptions.InformacoesEventoVazias;
import com.tokenlab.desafio.model.Evento;
import com.tokenlab.desafio.repositories.EventoRepository;
import com.tokenlab.desafio.utils.Utils;

@Service
public class EventoService {

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private Utils utils;

	public void salvarEvento(EventoRequestDTO eventoDTO) {
		try {
			Evento evento = this.criarObjetoEvento(eventoDTO);
			

			evento.setUsuario(this.utils.buscarUsuarioId(eventoDTO.getIdUsuario()));
			this.eventoRepository.save(evento);
		}catch (NullPointerException e) {
			throw new InformacoesEventoVazias();
		}catch (DateTimeException e) {
			throw new InformacoesEventoVazias();
		}
		
	}

	private Evento criarObjetoEvento(EventoRequestDTO eventoDTO) {
		return new Evento(null, this.converterHora(eventoDTO.getHoraInicio()), this.converterHora(eventoDTO.getHoraTermino()), eventoDTO.getDescricao(),
				eventoDTO.getNomeEvento());
	}

	private LocalTime converterHora(String hora) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime localTime = LocalTime.parse(hora, formatter);
		
		return localTime;
	}


	public void atualizarEvento(EventoRequestDTO eventoDTO, Long id) {
		try {
			
			Evento evento = buscarEventoPorId(id);
			evento.setDescricao(eventoDTO.getDescricao());
			evento.setHoraInicio(this.converterHora(eventoDTO.getHoraInicio()));
			evento.setHoraTermino(this.converterHora(eventoDTO.getHoraTermino()));
			evento.setNomeEvento(eventoDTO.getNomeEvento());
			this.eventoRepository.save(evento);
		}catch (NullPointerException e) {
			throw new InformacoesEventoVazias();
		}catch (DateTimeException e) {
			throw new InformacoesEventoVazias();
		}
		

	}

	public void deletarEvento(Long id) {
		this.eventoRepository.delete(buscarEventoPorId(id));
	}

	private Evento buscarEventoPorId(Long id) {
		return eventoRepository.findById(id).orElseThrow(() -> new EventoNaoEncontradoException());
	}

}
