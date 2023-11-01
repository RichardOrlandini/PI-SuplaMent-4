package com.br.SuplaMent.domain.produto;

import com.br.SuplaMent.utils.aEntity.DomainEntity;
import com.br.SuplaMent.domain.categoria.Categoria;
import com.br.SuplaMent.domain.fornecedor.Fornecedor;

import com.br.SuplaMent.domain.produto.dto.ProdutoCreateToSalesDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Produto")
@Table(name = "produto")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Produto extends DomainEntity {

    @Column(name = "nome", nullable = true)
    private String nome;

    @Column(name = "qtd", nullable = false)
    private Integer qtd;

    @Column
    private String nomeImagem;

    @ManyToOne
    @JoinColumn(name = "FK_FORNECEDOR", nullable = false)
    private Fornecedor fornecedor;

    @ManyToOne
    @JoinColumn(name = "FK_CATEGORIA", nullable = false)
    private Categoria categoria;

    @Column
    private double valor;


    public void deleta() {
        this.setActive(false);
    }
    public void ativa() {
        this.setActive(true);
    }
    public static Produto of (ProdutoCreateToSalesDTO request, Fornecedor fornecedor, Categoria categoria) {
        return Produto
                .builder()
                .nome(request.getNome())
                .qtd(request.getQtd())
                .nomeImagem(request.getNomeImagem())
                .categoria(categoria)
                .fornecedor(fornecedor)
                .build();
    }

    public void updateStock(Integer qtd) {
        this.qtd -= qtd;
    }
}


