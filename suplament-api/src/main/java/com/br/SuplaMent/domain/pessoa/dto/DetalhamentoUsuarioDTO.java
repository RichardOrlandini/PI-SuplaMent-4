package com.br.SuplaMent.domain.pessoa.dto;


import com.br.SuplaMent.domain.pessoa.UserRole;
import com.br.SuplaMent.domain.pessoa.Usuario;
// TODO: adicionar todos os campos do usuario para ter a função de cadastrar dele
//  bem feita no front, adicionar o endereço, adicionar o telefone e etc
public record DetalhamentoUsuarioDTO(Long id, String nome, String email, UserRole role) {

    public DetalhamentoUsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getRole());
    }
}

