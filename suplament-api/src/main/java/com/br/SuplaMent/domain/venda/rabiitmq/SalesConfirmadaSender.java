package com.br.SuplaMent.domain.venda.rabiitmq;

import com.br.SuplaMent.domain.venda.dto.SalesConfirmationDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SalesConfirmadaSender {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbit.exchange.product}")
    private String productTopicExchange;

    @Value("${rabbit.routingKey.sales-confirmation}")
    private String salesConfirmadaKey;

    public void sendSalesConfirmationMessage(SalesConfirmationDTO message) {

        try {
            log.info("Mensagem enviada: {}" , new ObjectMapper().writeValueAsString(message));
            rabbitTemplate.convertAndSend(productTopicExchange, salesConfirmadaKey, message );
            log.info("Mensagem enviada com sucesso!");
        } catch (Exception e) {
            log.info("Erro enquanto tentava mandar a venda de confirmação da mensagem", e);
        }

    }
}
