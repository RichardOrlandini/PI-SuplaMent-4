package com.br.SuplaMent.model.dto.cliente;

import com.br.SuplaMent.model.Cliente;


public record ListagemClienteDTO(Long id, Boolean ativo, String nome, String email) {

        public ListagemClienteDTO(Cliente cliente) {
            this(cliente.getId(), cliente.getActive() , cliente.getNome(), cliente.getEmail());
        }
    }
