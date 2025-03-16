package com.tokenlab.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tokenlab.desafio.dto.EventoRequestDTO;
import com.tokenlab.desafio.service.EventoService;

@RestController
@CrossOrigin("http://localhost:5173/")
@RequestMapping("/v1/eventos")
public class EventoController {

	@Autowired
	private EventoService eventoService;

	@PostMapping
	public ResponseEntity<Void> adicionarEvento(@RequestBody EventoRequestDTO eventoDTO) {
		eventoService.salvarEvento(eventoDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
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
