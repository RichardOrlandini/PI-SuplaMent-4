package com.br.SuplaMent.domain.produto.rabbitmq;

import com.br.SuplaMent.domain.produto.dto.ProdutoStoqueDTO;
import com.br.SuplaMent.services.ProdutoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProdutoStoqueListener {
    @Autowired
    private ProdutoService produtoService;

    @RabbitListener(queues = "${rabbit.queue.product-stock}")
    public void recebeMensagemProdutoEstoque(ProdutoStoqueDTO produtoStoqueDTO) throws JsonProcessingException {
        log.info("Recebendo mensagem: {}", new ObjectMapper().writeValueAsString(produtoStoqueDTO));
        produtoService.updateProdutoStoque(produtoStoqueDTO);

    }
}
