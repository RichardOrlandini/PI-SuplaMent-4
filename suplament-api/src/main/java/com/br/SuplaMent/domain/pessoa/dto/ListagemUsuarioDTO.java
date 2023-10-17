package com.br.SuplaMent.domain.pessoa.dto;

import com.br.SuplaMent.domain.pessoa.UserRole;
import com.br.SuplaMent.domain.pessoa.Usuario;

public record ListagemUsuarioDTO(Long id, Boolean active, String nome, String email, UserRole role ) {

    public ListagemUsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getActive() , usuario.getNome(), usuario.getEmail(), usuario.getRole());
    }
}



