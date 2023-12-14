package com.br.SuplaMent.services;

import com.br.SuplaMent.domain.pedido.Pedido;
import com.br.SuplaMent.domain.pedido.PedidoRepository;
import com.br.SuplaMent.domain.pedido.dto.AtualizaStatusPedidoDTO;
import com.br.SuplaMent.domain.pedido.dto.ListagemPedidosDTO;
import com.br.SuplaMent.domain.pessoa.EstoquistaRepository;
import com.br.SuplaMent.utils.enums.StatusPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstoquistaService {
    @Autowired
    private PedidoRepository pedidoRepository;
    public Page listarTodosPedidos(Pageable paginacao){
        return pedidoRepository.findAll(paginacao).map(ListagemPedidosDTO::new);
    }
    public String atualizaStatusPedido(AtualizaStatusPedidoDTO atualizaStatusPedidoDTO){
        Pedido pedido = pedidoRepository.findById(atualizaStatusPedidoDTO.id()).get();
        pedido.setStatusPedido(verificarStatusPedido(atualizaStatusPedidoDTO));
        if(pedido.getStatusPedido() == null){
            return "ERRO";
        }else{
            pedidoRepository.save(pedido);
            return atualizaStatusPedidoDTO.statusPedido();
        }
    }

    public static StatusPedido verificarStatusPedido(AtualizaStatusPedidoDTO atualizaStatusPedidoDTO){
        if(atualizaStatusPedidoDTO.statusPedido().equals("AGUARDANDO PAGAMENTO")){
            return StatusPedido.AGUARDANDO_PAGAMENTO;
        }
        if(atualizaStatusPedidoDTO.statusPedido().equals("PAGAMENTO REJEITADO")){
            return StatusPedido.PAGAMENTO_REJEITADO;
        }
        if(atualizaStatusPedidoDTO.statusPedido().equals("PAGAMENTO COM SUCESSO")){
            return StatusPedido.PAGAMENTO_COM_SUCESSO;
        }
        if(atualizaStatusPedidoDTO.statusPedido().equals("AGUARDANDO RETIRADA")){
            return StatusPedido.AGUARDANDO_RETIRADA;
        }
        if(atualizaStatusPedidoDTO.statusPedido().equals("EM TRANSITO")){
            return StatusPedido.EM_TRANSITO;
        }
        if(atualizaStatusPedidoDTO.statusPedido().equals("ENTREGUE")){
            return StatusPedido.ENTREGUE;
        }
        else{
            return null;
        }
    }
}
