package com.br.SuplaMent.domain.venda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesProductResponse {

    private List<String> salesId;

}
