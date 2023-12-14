package com.br.SuplaMent.domain.pessoa.dto;



import com.br.SuplaMent.domain.endereco.Endereco;
import com.br.SuplaMent.domain.pessoa.Cliente;

import java.util.Date;
import java.util.List;

public record DetalhamentoClienteDTO(Long id, String nome, String email, String senha, String Cpf, String genero, Date dataNascimento, List<Endereco> enderecos) {

    public DetalhamentoClienteDTO(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getSenha(), cliente.getCpf(), cliente.getGenero(), cliente.getDataNascimento(), cliente.getEnderecos());
    }
}
