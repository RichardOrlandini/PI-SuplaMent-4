package com.br.SuplaMent.domain.pedido.dto;

import java.time.LocalDateTime;

public record DetalhePedidoDTO(Long id, Long idCliente, Long idPaciente, LocalDateTime data) {
}
