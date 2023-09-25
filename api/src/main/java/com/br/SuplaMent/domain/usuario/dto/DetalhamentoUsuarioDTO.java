package com.br.SuplaMent.domain.usuario.dto;

import com.br.SuplaMent.domain.endereco.Endereco;
import com.br.SuplaMent.domain.usuario.UserRole;
import com.br.SuplaMent.domain.usuario.Usuario;
// TODO: adicionar todos os campos do usuario para ter a função de cadastrar dele
//  bem feita no front, adicionar o endereço, adicionar o telefone e etc
public record DetalhamentoUsuarioDTO(Long id, String nome, String email, String senha,String confirm_password, String Cpf, String telefone, UserRole role, Endereco endereco) {

    public DetalhamentoUsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getPassword(), usuario.getCpf(), usuario.getTelefone(),usuario.getRole(), usuario.getEndereco());
    }
}
