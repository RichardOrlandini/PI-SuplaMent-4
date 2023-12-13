package com.br.SuplaMent.domain.pessoa.dto;

import com.br.SuplaMent.domain.pessoa.UserRole;
import com.br.SuplaMent.domain.pessoa.Usuario;

public record DetalhamentoInicialUsuarioDTO(Long id, String email, String nome, UserRole role) {
    public DetalhamentoInicialUsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getEmail(), usuario.getNome(), usuario.getRole());
    }
}
