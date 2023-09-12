package com.br.SuplaMent.domain.usuario.dto;

import com.br.SuplaMent.domain.usuario.UserRole;
import com.br.SuplaMent.domain.usuario.Usuario;

public record DetalhamentoUsuarioDTO(Long id, String nome, String email, UserRole role) {

    public DetalhamentoUsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getRole());
    }
}
