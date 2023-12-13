package com.br.SuplaMent.domain.pedidoProduto;

import com.br.SuplaMent.domain.pedido.Pedido;
import com.br.SuplaMent.domain.produto.Produto;
import com.br.SuplaMent.utils.aEntity.DomainEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedido_produto")
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class PedidoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Produto produto;

    @Column
    private Integer quantidade;

    public static PedidoProduto of(Pedido pedido, Produto produto, Integer quantidade) {
        return PedidoProduto.builder()
                .pedido(pedido)
                .produto(produto)
                .quantidade(quantidade)
                .build();
    }
}
