package com.br.SuplaMent.services;

import com.br.SuplaMent.domain.pedido.Pedido;
import com.br.SuplaMent.domain.pedido.PedidoRepository;
import com.br.SuplaMent.domain.pedido.dto.AvisoRetornoPedidoDTO;
import com.br.SuplaMent.domain.pedido.dto.CreatePedidoDTO;
import com.br.SuplaMent.domain.pedido.dto.ListagemPedidosDTO;
import com.br.SuplaMent.domain.pessoa.Cliente;
import com.br.SuplaMent.domain.pessoa.ClienteRepository;
import com.br.SuplaMent.infra.exception.ValidationExcepetion;
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

    @Autowired
    private ClienteRepository clienteRepository;
    public AvisoRetornoPedidoDTO save(CreatePedidoDTO createPedidoDTO) {
        FormaPagamento formaPagamento;
        String mensagem = null;
        try {
            formaPagamento = createPedidoDTO.formaPagamento();
        } catch (IllegalArgumentException e) {
            formaPagamento = null;
            mensagem = "\nA FORMA DE PAGAMENTO INFORMADA NÃO EXISTE!\n";
            return new AvisoRetornoPedidoDTO(ThreadLocalRandom.current().nextLong(), createPedidoDTO.valorTotal(), mensagem);
        }
        Cliente cliente = this.validateClient(createPedidoDTO.idCliente());
        //BUSCAR O CLIENTE PELO ID QUE ESTA VINDO PELO DTO, E MANDAR O CLIENTE NESSE PARÂMETRO
        Pedido pedido = Pedido.of(createPedidoDTO, cliente, formaPagamento);
        pedidoRepository.save(pedido);
        return new AvisoRetornoPedidoDTO(pedido.getId(), pedido.getValorTotal(), mensagem);
    }

    private Cliente validateClient(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
        if (cliente == null) {
            new ValidationExcepetion("O client informado no pedido não existe!");
        }
        return cliente;
    }

    public List<ListagemPedidosDTO> buscarPedidosDoCliente(Long clienteId) {
        List<Pedido> listaPedidos = pedidoRepository.findByClienteId(clienteId);
        List<ListagemPedidosDTO> listaPedidosDTO = listaPedidos.stream()
                .map(ListagemPedidosDTO::new)
                .collect(Collectors.toList());

        return listaPedidosDTO;
    }
}
