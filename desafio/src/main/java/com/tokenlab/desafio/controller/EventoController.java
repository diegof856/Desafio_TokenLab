package com.tokenlab.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tokenlab.desafio.dto.EventoRequestDTO;
import com.tokenlab.desafio.dto.EventoResponseDTO;
import com.tokenlab.desafio.model.Evento;
import com.tokenlab.desafio.service.EventoService;

@RestController
@RequestMapping("/v1/eventos")
public class EventoController {

	@Autowired
	private EventoService eventoService;

	@PostMapping("/{id}")
	public ResponseEntity<Void> adicionarEvento(@PathVariable Long id, @RequestBody Evento evento) {
		eventoService.salvarEvento(id, evento);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<Page<EventoResponseDTO>> listarEventos(Pageable pageable) {
		 return ResponseEntity.ok(eventoService.listarEventos(pageable));
	}

	@PutMapping("{id}")
	public ResponseEntity<Void> modificarEvento(@PathVariable Long id, @RequestBody EventoRequestDTO eventoDTO) {
		eventoService.atualizarEvento(eventoDTO, id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deletarEvento(@PathVariable Long id) {
		eventoService.deletarEvento(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
