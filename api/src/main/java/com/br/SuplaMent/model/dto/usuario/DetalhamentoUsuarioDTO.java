package com.br.SuplaMent.model.dto.usuario;


import com.br.SuplaMent.utils.UserRole;
import com.br.SuplaMent.model.Usuario;
// TODO: adicionar todos os campos do usuario para ter a função de cadastrar dele
//  bem feita no front, adicionar o endereço, adicionar o telefone e etc
public record DetalhamentoUsuarioDTO(Long id, String nome, String email, UserRole role) {

    public DetalhamentoUsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getRole());
    }
}


// usar no proximop detalhamento

//        public record DetalhamentoUsuarioDTO(Long id, String nome, String email, String senha, String Cpf, String telefone, UserRole role, Endereco endereco) {
//            public DetalhamentoUsuarioDTO(Usuario usuario) {
//                this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getCpf(), usuario.getTelefone(),usuario.getRole(), usuario.getEndereco());