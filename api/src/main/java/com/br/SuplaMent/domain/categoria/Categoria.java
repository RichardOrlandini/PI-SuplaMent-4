package com.br.SuplaMent.domain.categoria;

import com.br.SuplaMent.domain.produto.Produto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Entity(name = "Categoria")
@Table(name = "categoria")
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String nome;

    @OneToMany
    List<Produto> produtos;
}
