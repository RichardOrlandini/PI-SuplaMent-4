package com.br.SuplaMent.model.dto.usuario;


import com.br.SuplaMent.utils.UserRole;
import com.br.SuplaMent.model.Usuario;
// TODO: adicionar todos os campos do usuario para ter a função de cadastrar dele
//  bem feita no front, adicionar o endereço, adicionar o telefone e etc
public record UsuarioDTO(Long id, String nome, String email, UserRole role) {

    public UsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getRole());
    }
}
