package com.br.SuplaMent.domain.cliente.dto;

import com.br.SuplaMent.domain.usuario.UserRole;
import com.br.SuplaMent.domain.cliente.Cliente;

public record DetalhamentoClienteDTO(Long id, String nome, String email, UserRole role) {

    public DetalhamentoClienteDTO(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getRole());
    }
}
