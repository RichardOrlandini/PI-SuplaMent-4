package com.br.SuplaMent.domain.produto;

import com.br.SuplaMent.domain.produto.dto.AtualizarProdutoDTO;
import com.br.SuplaMent.domain.produto.dto.CadastroProdutoDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Calendar;
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




    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private Calendar insertionDate;
    private double avaliacao;
    private Boolean active;
    private String nome;
    private String descri;
    private double valor;
    private int qtd;
    private String nomeImagem;


    //private Categorias categoria;

    public Produto(CadastroProdutoDTO dto) {
        this.active = true;
        this.nome = dto.nome();
        this.descri = dto.descri();
        this.valor = dto.valor();
        this.qtd = dto.qtd();
        this.nomeImagem = dto.nomeImagem();
    }

    public Produto(String nome, String descri, double valor, int qtd, String nomeImagem) {
        this.nome = nome;
        this.descri = descri;
        this.valor = valor;
        this.qtd = qtd;
        this.nomeImagem = nomeImagem;
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

    public void deleta() {
        this.active = false;
    }
    public void ativa() {
        this.active = true;
    }

    public void setQtd(int newQtd) {
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    public void setInsertionDate(Calendar insertionDate) {
        this.insertionDate = insertionDate;
    }
    public Calendar getInstance() {
        return insertionDate;
    }
}


