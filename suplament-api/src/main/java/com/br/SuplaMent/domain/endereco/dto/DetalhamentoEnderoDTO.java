package com.br.SuplaMent.domain.endereco.dto;


import com.br.SuplaMent.domain.endereco.Endereco;
import com.br.SuplaMent.domain.pessoa.Cliente;


public record DetalhamentoEnderoDTO (Long id, String complemento, String numero, String logradouro, String bairro, String cep, String cidade, String uf,Cliente cliente) {

    public DetalhamentoEnderoDTO (Endereco endereco) {
        this(endereco.getId(), endereco.getComplemento(), endereco.getNumero(), endereco.getLogradouro(), endereco.getBairro(), endereco.getCep(), endereco.getCidade(), endereco.getUf(),endereco.getCliente());
    }
}
