package com.br.SuplaMent.model.dto.usuario;

import com.br.SuplaMent.utils.UserRole;
import com.br.SuplaMent.model.Usuario;

public record ListagemUsuarioDTO(Long id, Boolean ativo, String nome, String email, UserRole role ) {

    public ListagemUsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getActive() ,usuario.getNome(), usuario.getEmail(), usuario.getRole());
    }
}



