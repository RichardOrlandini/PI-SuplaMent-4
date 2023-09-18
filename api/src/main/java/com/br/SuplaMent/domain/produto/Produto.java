package com.br.SuplaMent.domain.produto;

import ch.qos.logback.core.model.Model;
import com.br.SuplaMent.domain.produto.dto.AtualizarProdutoDTO;
import com.br.SuplaMent.domain.produto.dto.CadastroProdutoDTO;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;



@Entity(name = "Produto")
@Table(name = "produto")
@NoArgsConstructor
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean active;
    private String nome;
    private String descri;
    private double valor;
    private int qtd;

    //private Categorias categoria;

    public Produto(CadastroProdutoDTO dto) {
        this.active = true;
        this.nome = dto.nome();
        this.descri = dto.descri();
        this.valor = dto.valor();
        this.qtd = dto.qtd();
       // this.categoria = dto.categoria();
    }
    public Produto(String nome, String descri, double valor, int qtd/*Categorias categoria*/) {
        this.nome = nome;
        this.descri = descri;
        this.valor = valor;
        this.qtd = qtd;
        //this.categoria = categoria;
    }

    public void atualizarProduto(AtualizarProdutoDTO dto) {
        if (dto.nome() != null) {
            this.nome = dto.nome();
        }
        if (dto.descri() != null) {
            this.descri = dto.descri();
        }
        if (dto.valor() != 0f) {
            this.valor = dto.valor();
        }
        if (dto.qtd() != 0l) {
            this.qtd = dto.qtd();
        }
    }

    public void deleta() { // agr n tem q deletar somente ficar com status inativo e criar um modo de poder reativar
        this.active = false;

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

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }



   /* public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    } */


}


