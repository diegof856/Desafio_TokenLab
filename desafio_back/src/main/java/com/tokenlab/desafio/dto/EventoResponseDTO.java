package com.tokenlab.desafio.dto;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record EventoResponseDTO(Long idEvento, @JsonFormat(pattern = "HH:mm")LocalTime horaInicio, @JsonFormat(pattern = "HH:mm")LocalTime horaTermino, String descricao, String nomeEvento) {


}
