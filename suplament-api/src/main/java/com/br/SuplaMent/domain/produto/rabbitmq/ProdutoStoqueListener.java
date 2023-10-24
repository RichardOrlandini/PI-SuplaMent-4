package com.br.SuplaMent.domain.produto.rabbitmq;

import com.br.SuplaMent.domain.produto.dto.ProdutoStoqueDTO;
import com.br.SuplaMent.services.ProdutoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProdutoStoqueListener {

    private final ProdutoService produtoService;

    @RabbitListener(queues = "${rabbit.queue.product-stock}")
    public void recebeMensagemProdutoEstoque(ProdutoStoqueDTO produtoStoqueDTO) throws JsonProcessingException {
        log.info("Recebendo mensagem com data: {} e TransactionId: {}",
                new ObjectMapper().writeValueAsString(produtoStoqueDTO),
                produtoStoqueDTO.getTransactionid());
        produtoService.updateProdutoStoque(produtoStoqueDTO);
    }
}
