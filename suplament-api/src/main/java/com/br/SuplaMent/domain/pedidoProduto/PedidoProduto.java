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

    @ManyToOne // Alteração para @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne // Alteração para @ManyToOne
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
