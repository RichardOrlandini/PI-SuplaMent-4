package com.br.SuplaMent.domain.pedidoProduto;

import com.br.SuplaMent.domain.pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto, Long> {

    List<PedidoProduto> findAllByPedido_Id(long idDoPedido);
}
