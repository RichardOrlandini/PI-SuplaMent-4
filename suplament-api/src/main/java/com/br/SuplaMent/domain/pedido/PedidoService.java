package com.br.SuplaMent.domain.pedido;

import com.br.SuplaMent.domain.pedido.dto.AvisoRetornoPedidoDTO;
import com.br.SuplaMent.domain.pedido.dto.CreatePedidoDTO;
import com.br.SuplaMent.domain.pessoa.Cliente;
import com.br.SuplaMent.utils.enums.FormaPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
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
            //Gerando qualquer ID do pedido, porque esse pedido deu erro na forma do pagamento.
            //Mostrar a mensagem procliente e o calor total como o professor pediu.
            return new AvisoRetornoPedidoDTO(ThreadLocalRandom.current().nextLong(), createPedidoDTO.valorTotal(), mensagem);
        }
                                               //BUSCAR O CLIENTE PELO ID QUE ESTA VINDO PELO DTO, E MANDAR O CLIENTE NESSE PARÂMETRO
        Pedido pedido = Pedido.of(createPedidoDTO, new Cliente(), formaPagamento);
        pedidoRepository.save(pedido);
        return new AvisoRetornoPedidoDTO(pedido.getId(), pedido.getValorTotal(), mensagem);
    }
}
