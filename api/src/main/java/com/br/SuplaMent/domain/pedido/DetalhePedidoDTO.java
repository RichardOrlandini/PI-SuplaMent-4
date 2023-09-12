package com.br.SuplaMent.domain.pedido;

import java.time.LocalDateTime;

public record DetalhePedidoDTO(Long id, Long idCliente, Long idPaciente, LocalDateTime data) {
}
