package com.br.SuplaMent.domain.usuario.dto;

import com.br.SuplaMent.domain.usuario.Usuario;

public record detalhamentoUsuarioDTO(Long id, String nome, String email, Boolean grupo) {

    public detalhamentoUsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getGrupo());
    }
}
