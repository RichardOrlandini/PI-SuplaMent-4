package com.br.SuplaMent.domain.usuario.dto;

import com.br.SuplaMent.domain.usuario.UserRole;
import com.br.SuplaMent.domain.usuario.Usuario;

public record ListagemUsuarioDTO(Long id, Boolean ativo, String nome, String email, UserRole role ) {

    public ListagemUsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getActive() ,usuario.getNome(), usuario.getEmail(), usuario.getRole());
    }
}



