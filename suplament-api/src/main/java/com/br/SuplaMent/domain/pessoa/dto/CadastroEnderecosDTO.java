package com.br.SuplaMent.domain.pessoa.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroEnderecosDTO(
        String complemento,
        @NotBlank String numero,
        @NotBlank String logradouro,
        @NotBlank String bairro,
        @NotBlank String cep,
        @NotBlank String cidade,
        @NotBlank String uf,
        @NotBlank Boolean principal,
        @NotNull Long clienteId
) {
    private static boolean isPrincipal;

    public boolean isPrincipal() {
        return this.isPrincipal;
    }

    public void setPrincipal(boolean isPrincipal) {
        this.isPrincipal = isPrincipal;
    }



    public String getComplemento() {
        return complemento;
    }

    public String getNumero() {
        return numero;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCep() {
        return cep;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public Object getClienteId() {
        return clienteId;
    }
}
