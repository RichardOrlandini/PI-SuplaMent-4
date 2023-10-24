package com.br.SuplaMent.domain.venda.client;

import com.br.SuplaMent.domain.venda.dto.SalesProductResponse;
import com.br.SuplaMent.services.strategies.webClient.WebClientDefaultConfig;
import com.br.SuplaMent.services.strategies.webClient.WebClientOptions;
import com.br.SuplaMent.utils.navigation.BusinessCase;
import com.br.SuplaMent.utils.navigation.BusinessCaseBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class SalesService {
    private final WebClient webClient;

    public SalesService() {
        this.webClient = WebClient.create("http://localhost:8080/api/orders");
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


    public WebClient configureWebClient() {
        BusinessCase<WebClientOptions> configWebClientCase = new BusinessCaseBuilder<WebClientOptions>()
                .withName("CONFIGURE_DEFAULT_WEB_CLIENT");

        navigator.run(new WebClientDefaultConfig(), configWebClientCase);

        final Optional<WebClient> webClientOptional = configWebClientCase.getResult().getEntity();
        return webClientOptional.orElse(null);
    }
}
