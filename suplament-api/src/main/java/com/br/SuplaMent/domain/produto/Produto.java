package com.br.SuplaMent.domain.produto;

import com.br.SuplaMent.domain.categoria.Categoria;
import com.br.SuplaMent.domain.fornecedor.Fornecedor;

import com.br.SuplaMent.domain.produto.dto.ProdutoCreateToSalesDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;


@Entity(name = "Produto")
@Table(name = "produto")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "INSERTION_DATE", nullable = false, updatable = false)
    private LocalDateTime insertionDate;

    private Boolean active;

    private String nome;

    @Column(name = "qtd", nullable = false)
    private Integer qtd;

    private String nomeImagem;

    @ManyToOne
    @JoinColumn(name = "FK_FORNECEDOR", nullable = false)
    private Fornecedor fornecedor;

    @ManyToOne
    @JoinColumn(name = "FK_CATEGORIA", nullable = false)
    private Categoria categoria;


    // 1 fornecedor e 1 categoria ManyToOne O produto pode ter varios fornecedores
    // mais apenas a referecia de 1 produto pra cada linha


//    public Produto(CadastroProdutoDTO dto) {
//        this.active = true;
//        this.nome = dto.nome();
//        this.descri = dto.descri();
//        this.valor = dto.valor();
//        this.qtd = dto.qtd();
//        this.nomeImagem = dto.nomeImagem();
//    }

//    public Produto(String nome, String descri, double valor, int qtd, String nomeImagem) {
//        this.nome = nome;
//        this.descri = descri;
//        this.valor = valor;
//        this.qtd = qtd;
//        this.nomeImagem = nomeImagem;
//    }

//    public void atualizarProduto(AtualizarProdutoDTO dto) {
//        if (dto.nome() != null) {
//            this.nome = dto.nome();
//        }
//        if (dto.descri() != null) {
//            this.descri = dto.descri();
//        }
//        if (dto.valor() != 0f) {
//            this.valor = dto.valor();
//        }
//        if (dto.qtd() != 0l) {
//            this.qtd = dto.qtd();
//        }
//    }

    public void deleta() {
        this.active = false;
    }
    public void ativa() {
        this.active = true;
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

    @PrePersist
    public void prePersist() {
        this.insertionDate = LocalDateTime.now();
    }
}


