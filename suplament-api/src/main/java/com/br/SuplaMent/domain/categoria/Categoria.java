package com.br.SuplaMent.domain.categoria;

import com.br.SuplaMent.domain.produto.Produto;
import com.br.SuplaMent.dto.CategoriaRequest;
import com.br.SuplaMent.dto.CategoriaResponse;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Entity(name = "Categoria")
@Table(name = "categoria")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;


    public static Categoria of(CategoriaRequest request) {
        Categoria categoria = new Categoria();
        BeanUtils.copyProperties(request, categoria);
        return categoria;
    }
//    @OneToMany
//    List<Produto> produtos;
}
