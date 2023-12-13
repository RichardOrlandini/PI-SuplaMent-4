package com.br.SuplaMent.services;

import com.br.SuplaMent.domain.pedido.Pedido;
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

        //Buscar o cliente que está fazendo o pedido
        Cliente cliente = this.validateClient(createPedidoDTO.idCliente());
        //Criar o pedido sem a lista dos produtos
        Pedido pedido = Pedido.of(createPedidoDTO, cliente, formaPagamento);
        pedido = pedidoRepository.save(pedido);
        //Criar a lista de produtos que terá no pedido(informa quantidade de cada produto no pedido)
        List<PedidoProduto> pedidoProdutos = criarPedidoProdutos(createPedidoDTO.produtos(), pedido);
        //Setar a lista dos produtos com suas quantidades no pedido e atualiza na base
        pedido.setProdutos(pedidoProdutos);
        pedidoRepository.save(pedido);

        pedidoProdutoRepository.saveAll(pedidoProdutos);
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

    public List<PedidoProduto> criarPedidoProdutos(List<ProdutoNoCarrinho> produtos, Pedido pedido){
        List<PedidoProduto> pedidoProdutos = new ArrayList<>();
        for (ProdutoNoCarrinho produtoNoCarrinho : produtos) {
            Optional<Produto> produtoOptional = produtoRepository.findByNome(produtoNoCarrinho.nome()).stream().findFirst();
            if (produtoOptional.isPresent()) {
                Produto produto = produtoOptional.get();
                pedidoProdutos.add(PedidoProduto.of(pedido, produto, produtoNoCarrinho.quantidade()));
            }
        }
        return pedidoProdutos;
    }
}
