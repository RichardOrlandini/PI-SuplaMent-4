package com.br.SuplaMent.domain.fornecedor;

import com.br.SuplaMent.domain.fornecedor.dto.FornecedorCreateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Entity(name = "Fornecedor")
@Table(name = "fornecedor")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;


    public static Fornecedor of(FornecedorCreateDTO request) {
        var fornecedor = new Fornecedor();
        BeanUtils.copyProperties(request, fornecedor);
        return fornecedor;
    }
}
