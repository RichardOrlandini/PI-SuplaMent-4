package com.br.SuplaMent.domain.venda.client;

import com.br.SuplaMent.domain.venda.dto.SalesProductResponse;
import com.br.SuplaMent.services.strategies.webClient.WebClientDefaultConfig;
import com.br.SuplaMent.services.strategies.webClient.WebClientOptions;
import com.br.SuplaMent.utils.navigation.core.BusinessCase;
import com.br.SuplaMent.utils.navigation.core.BusinessCaseBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class SalesService {



    private final WebClient webClient;

    public SalesService() {
        this.webClient = WebClient.create("http://localhost:8082/api/orders");
    }

    public Optional<SalesProductResponse> findSalesByProductId(Long productId, String authorization, String transactionId) {

        return webClient.get()
                .uri("order/product/{productId}", productId)
                .header(HttpHeaders.AUTHORIZATION, authorization)
                .header("transactionId", transactionId)
                .retrieve()
                .bodyToMono(SalesProductResponse.class)
                .blockOptional();

    }
}
