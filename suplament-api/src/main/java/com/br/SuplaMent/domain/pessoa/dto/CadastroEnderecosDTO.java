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
        @NotNull Long clienteId
) {

    private static boolean isPrincipal;

    public boolean isPrincipal() {
        return this.isPrincipal;
    }

    public void setPrincipal(boolean isPrincipal) {
        this.isPrincipal = isPrincipal;
    }
}
