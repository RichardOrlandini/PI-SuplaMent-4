package com.br.SuplaMent.domain.pedido;

import com.br.SuplaMent.domain.pedido.dto.CreatePedidoDTO;
import com.br.SuplaMent.domain.pessoa.Cliente;
import com.br.SuplaMent.domain.produto.Produto;
import com.br.SuplaMent.utils.aEntity.DomainEntity;
import com.br.SuplaMent.utils.enums.FormaPagamento;
import com.br.SuplaMent.utils.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pedido")
@NoArgsConstructor
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Pedido extends DomainEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
            name = "pedido_produto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtos;

    @Column
    private Double valorTotal;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    @Column
    private String enderecoEntrega;

    @Column
    private LocalDateTime dataPedido;

    @Column
    private Double valorFrete;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEntrega;

    @Column
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    public static Pedido of (CreatePedidoDTO createPedidoDTO, Cliente cliente, FormaPagamento formaPagamento) {
        return Pedido
                .builder()
                .produtos(createPedidoDTO.produtos())
                .valorTotal(createPedidoDTO.valorTotal())
                .valorFrete(createPedidoDTO.valorFrete())
                .enderecoEntrega(createPedidoDTO.enderecoEntrega())
                .dataEntrega(createPedidoDTO.dataEntrega())
                .cliente(cliente)
                .dataPedido(LocalDateTime.now())
                .statusPedido(StatusPedido.AGUARDANDO_PAGAMENTO)
                .formaPagamento(formaPagamento)
                .build();
    }
}

