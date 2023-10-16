package com.br.SuplaMent.domain.fornecedor.dto;

import com.br.SuplaMent.domain.categoria.Categoria;
import com.br.SuplaMent.domain.categoria.dto.CategoriaResponse;
import com.br.SuplaMent.domain.fornecedor.Fornecedor;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class FornecedorResponseDTO {

    private  Long id;
    private String nome;

    public static FornecedorResponseDTO of(Fornecedor fornecedor) {
        FornecedorResponseDTO response = new FornecedorResponseDTO();
        BeanUtils.copyProperties(fornecedor, response);
        return response;
    }

}
