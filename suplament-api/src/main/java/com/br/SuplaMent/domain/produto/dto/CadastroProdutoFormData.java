package com.br.SuplaMent.domain.produto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

public record CadastroProdutoFormData (
        @NotBlank String nome,
        @NotBlank String descri,
        @NotNull double valor,
        @NotNull int qtd,
        @NotNull MultipartFile arquivo
) {
    public CadastroProdutoDTO toDTO() {
        return new CadastroProdutoDTO(nome, descri, valor, qtd, arquivo.getOriginalFilename());
    }
}