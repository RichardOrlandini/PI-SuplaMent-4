package com.br.SuplaMent.domain.endereco.dto;


import com.br.SuplaMent.domain.pessoa.Cliente;


public record DetalhamentoEnderoDTO (Long id, String complemento, String numero, String logradouro, String bairro, String cep, String cidade, String uf,Cliente cliente) {

//    public DetalhamentoEnderoDTO (Endereco endereco) {
//        this(endereco.getComplemento());
//    }
}
