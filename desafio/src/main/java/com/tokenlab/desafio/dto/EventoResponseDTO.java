package com.tokenlab.desafio.dto;

import java.time.Instant;

public record EventoResponseDTO(Long idEvento, Instant horaInicio, Instant horaTermino, String descricao, String nomeEvento) {


}
