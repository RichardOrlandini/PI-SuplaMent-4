package com.br.SuplaMent.domain.usuario.dto;

import com.br.SuplaMent.domain.usuario.Usuario;

public record listagemUsuarioDTO(Long id, Boolean ativo, String nome, String email, Boolean grupo ) {

    public listagemUsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getActive() ,usuario.getNome(), usuario.getEmail(), usuario.getGrupo());
    }
}



