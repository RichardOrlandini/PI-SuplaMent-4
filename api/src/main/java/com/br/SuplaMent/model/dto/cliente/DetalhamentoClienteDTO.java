package com.br.SuplaMent.model.dto.cliente;

import com.br.SuplaMent.model.Cliente;

public record DetalhamentoClienteDTO(Long id, String nome, String email) {

    public DetalhamentoClienteDTO(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getEmail());
    }
}
