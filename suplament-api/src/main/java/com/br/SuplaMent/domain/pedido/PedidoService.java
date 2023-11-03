package com.br.SuplaMent.domain.pedido;

import com.br.SuplaMent.domain.pedido.dto.AvisoRetornoPedidoDTO;
import com.br.SuplaMent.domain.pedido.dto.CreatePedidoDTO;
import com.br.SuplaMent.domain.pedido.dto.ListagemPedidosDTO;
import com.br.SuplaMent.domain.pessoa.Cliente;
import com.br.SuplaMent.utils.enums.FormaPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    public AvisoRetornoPedidoDTO save(CreatePedidoDTO createPedidoDTO) {
        FormaPagamento formaPagamento;
        String mensagem = null;
        try {
            formaPagamento = FormaPagamento.valueOf(createPedidoDTO.formaPagamento());
        } catch (IllegalArgumentException e) {
            formaPagamento = null;
            mensagem = "\nA FORMA DE PAGAMENTO INFORMADA NÃO EXISTE!\n";
            return new AvisoRetornoPedidoDTO(ThreadLocalRandom.current().nextLong(), createPedidoDTO.valorTotal(), mensagem);
        }
        //BUSCAR O CLIENTE PELO ID QUE ESTA VINDO PELO DTO, E MANDAR O CLIENTE NESSE PARÂMETRO
        Pedido pedido = Pedido.of(createPedidoDTO, new Cliente(), formaPagamento);
        pedidoRepository.save(pedido);
        return new AvisoRetornoPedidoDTO(pedido.getId(), pedido.getValorTotal(), mensagem);
    }

    public List<ListagemPedidosDTO> buscarPedidosDoCliente(Long clienteId) {
        List<Pedido> listaPedidos = pedidoRepository.findByClienteId(clienteId);
        List<ListagemPedidosDTO> listaPedidosDTO = listaPedidos.stream()
                .map(ListagemPedidosDTO::new)
                .collect(Collectors.toList());

        return listaPedidosDTO;
    }
}
