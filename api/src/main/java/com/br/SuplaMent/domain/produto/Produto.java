package com.br.SuplaMent.domain.produto;

import ch.qos.logback.core.model.Model;
import com.br.SuplaMent.domain.Imagem.Imagem;
import com.br.SuplaMent.domain.produto.dto.AtualizarProdutoDTO;
import com.br.SuplaMent.domain.produto.dto.CadastroProdutoDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micrometer.common.lang.Nullable;
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
    private float avaliacao;
    private Boolean active;
    private String nome;
    private String descri;
    private double valor;
    private int qtd;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imagem> imagens;

    //private Categorias categoria;

    public Produto(CadastroProdutoDTO dto) {
        this.active = true;
        this.nome = dto.nome();
        this.descri = dto.descri();
        this.valor = dto.valor();
        this.qtd = dto.qtd();
       // this.categoria = dto.categoria();
        imagens = new ArrayList<>();
    }

    public Produto(String nome, String descri, double valor, int qtd/*Categorias categoria*/) {
        this.nome = nome;
        this.descri = descri;
        this.valor = valor;
        this.qtd = qtd;
        //this.categoria = categoria;
        imagens = new ArrayList<>();
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

    public void setQtd(int newQtd) {
    }





   /* public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    } */


}


