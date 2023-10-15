package com.br.SuplaMent.domain.cliente.dto;

import com.br.SuplaMent.domain.cliente.Cliente;
import com.br.SuplaMent.domain.usuario.UserRole;


public record ListagemClienteDTO(Long id, Boolean ativo, String nome, String email) {

        public ListagemClienteDTO(Cliente cliente) {
            this(cliente.getId(), cliente.getActive() , cliente.getNome(), cliente.getEmail());
        }
    }
