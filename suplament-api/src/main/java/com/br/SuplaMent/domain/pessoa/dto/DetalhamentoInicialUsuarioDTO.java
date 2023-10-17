package com.br.SuplaMent.domain.pessoa.dto;

import com.br.SuplaMent.domain.pessoa.Usuario;

public record DetalhamentoInicialUsuarioDTO(Long id, String email) {
    public DetalhamentoInicialUsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getEmail());
    }
}
