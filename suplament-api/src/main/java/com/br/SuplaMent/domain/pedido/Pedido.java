package com.br.SuplaMent.domain.pedido;

import com.br.SuplaMent.domain.pessoa.Cliente;
import com.br.SuplaMent.domain.produto.Produto;
import com.br.SuplaMent.utils.aEntity.DomainEntity;
import com.br.SuplaMent.utils.enums.FormaPagamento;
import com.br.SuplaMent.utils.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pedido")
@NoArgsConstructor
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pedido extends DomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private Double valor;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    @Column
    private String enderecoEntrega;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtEntrega;

    @Column
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;
}

