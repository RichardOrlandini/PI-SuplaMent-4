package com.br.SuplaMent.domain.Imagem;

import com.br.SuplaMent.domain.produto.Produto;
import com.br.SuplaMent.domain.produto.dto.AtualizarProdutoDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.EqualsAndHashCode;
import jakarta.persistence.*;

@Entity(name = "Imagem")
@Table(name = "imagem")
@EqualsAndHashCode(of = "id")
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    private boolean principal;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    public Imagem(Long id, String nome, boolean principal, Produto produto) {
        this.id = id;
        this.nome = nome;
        this.principal = principal;
        this.produto = produto;
    }

    public Imagem() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }


    public void setImagePath(String filename) {
    }

    public void setDefault(boolean principal) {
    }
}

