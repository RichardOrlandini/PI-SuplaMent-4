package com.br.SuplaMent.model.dto.pedido;

import java.time.LocalDateTime;

public record DetalhePedidoDTO(Long id, Long idCliente, Long idPaciente, LocalDateTime data) {
}
