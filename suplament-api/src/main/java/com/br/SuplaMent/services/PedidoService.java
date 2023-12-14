package com.br.SuplaMent.services;
import com.br.SuplaMent.domain.pedido.Pedido;
import com.br.SuplaMent.domain.pedido.dto.DetalhamentoPedidoDTO;
import com.br.SuplaMent.domain.pedidoProduto.PedidoProduto;
import com.br.SuplaMent.domain.pedido.PedidoRepository;
import com.br.SuplaMent.domain.pedido.dto.AvisoRetornoPedidoDTO;
import com.br.SuplaMent.domain.pedido.dto.CreatePedidoDTO;
import com.br.SuplaMent.domain.pedido.dto.ListagemPedidosDTO;
import com.br.SuplaMent.domain.pedidoProduto.PedidoProdutoRepository;
import com.br.SuplaMent.domain.pessoa.Cliente;
import com.br.SuplaMent.domain.pessoa.ClienteRepository;
import com.br.SuplaMent.domain.produto.Produto;
import com.br.SuplaMent.domain.produto.ProdutoRepository;
import com.br.SuplaMent.domain.produto.dto.DetalhamentoProdutoDTO;
import com.br.SuplaMent.domain.produto.dto.ProdutoNoCarrinho;
import com.br.SuplaMent.infra.exception.ValidationExcepetion;
import com.br.SuplaMent.utils.enums.FormaPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private PedidoProdutoRepository pedidoProdutoRepository;

    @Autowired
    private ClienteRepository clienteRepository;
    public void save(CreatePedidoDTO createPedidoDTO) {
        FormaPagamento formaPagamento;
        String mensagem = null;
        try {
            formaPagamento = createPedidoDTO.formaPagamento();
        } catch (IllegalArgumentException e) {
            formaPagamento = null;
            mensagem = "\nA FORMA DE PAGAMENTO INFORMADA NÃO EXISTE!\n";
        }

        Cliente cliente = this.validateClient(createPedidoDTO.idCliente());
        Pedido pedido = Pedido.of(createPedidoDTO, cliente, formaPagamento);
        pedido = pedidoRepository.save(pedido);
        List<PedidoProduto> pedidoProdutos = criarPedidoProdutos(createPedidoDTO.produtos(), pedido);

        pedido.setProdutos(pedidoProdutos);

        pedidoRepository.save(pedido);
        pedidoProdutoRepository.saveAll(pedidoProdutos);
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

    public List<PedidoProduto> criarPedidoProdutos(List<ProdutoNoCarrinho> produtos, Pedido pedido){
        List<PedidoProduto> pedidoProdutos = new ArrayList<>();

        for (ProdutoNoCarrinho produtoNoCarrinho : produtos) {
            Produto produto = produtoRepository.findById(produtoNoCarrinho.id()).orElse(null);

            if (produto != null) {
                pedidoProdutos.add(PedidoProduto.of(pedido, produto, produtoNoCarrinho.quantidade()));
            } else {
                throw new ValidationExcepetion("O id do produto no pedido não foi encontrado!");
            }
        }
        return pedidoProdutos;
    }

    public DetalhamentoPedidoDTO buscarResumoPedido(Long idDoPedido){
        Pedido pedido = pedidoRepository.findById(idDoPedido).orElse(null);
        List<DetalhamentoProdutoDTO> detalhamentoProdutoDTOS = new ArrayList<>();
        if(pedido != null){
            List<PedidoProduto> pedidoProdutos = pedidoProdutoRepository.findAllByPedido_Id(idDoPedido);
            if(!pedidoProdutos.isEmpty()){
                for (PedidoProduto pedidoProduto : pedidoProdutos) {
                    Produto produto = produtoRepository.findById(pedidoProduto.getProduto().getId()).orElse(null);
                    detalhamentoProdutoDTOS.add(new DetalhamentoProdutoDTO(produto,
                                                                           calculaValorTotal(pedidoProduto, produto),
                                                                           pedidoProduto.getQuantidade()));
                }
            }
        }else{
            throw new ValidationExcepetion("O id do pedido não foi encontrado!");
        }
        return montaDetalhamentoPedidoDTO(detalhamentoProdutoDTOS, pedido);
    }

    public static double calculaValorTotal(PedidoProduto pedidoProduto, Produto produto){
        return pedidoProduto.getQuantidade() *  produto.getValor();
    }
    public static DetalhamentoPedidoDTO montaDetalhamentoPedidoDTO(List<DetalhamentoProdutoDTO> detalhamentoProdutoDTOS, Pedido pedido){
        return new DetalhamentoPedidoDTO(detalhamentoProdutoDTOS, pedido.getValorFrete(), pedido.getValorTotal(), pedido.getEnderecoEntrega(), pedido.getFormaPagamento().toString());

    }
}
