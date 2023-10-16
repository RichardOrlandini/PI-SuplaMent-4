package com.br.SuplaMent.domain.categoria.dto;

import com.br.SuplaMent.domain.categoria.Categoria;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class CategoriaResponse {

    private Long id;
    private String descricao;

    public static CategoriaResponse of(Categoria categoria) {
        CategoriaResponse response = new CategoriaResponse();
        BeanUtils.copyProperties(categoria, response);
        return response;
    }
}
