package com.tokenlab.desafio.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tokenlab.desafio.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
	Page<Evento> findByUsuarioIdUsuario(Long usuarioId, Pageable pageable);
}
