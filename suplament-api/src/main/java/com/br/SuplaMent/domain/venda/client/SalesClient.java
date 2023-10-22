package com.br.SuplaMent.domain.venda.client;

import com.br.SuplaMent.domain.venda.dto.SalesProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;


@FeignClient(
        name = "salesClient",
        contextId = "salesClient",
        url = "${services.sales}"
)
public interface SalesClient {
    @GetMapping("api/orders/product/productId")
    Optional<SalesProductResponse> findSalesByProductId(@PathVariable Long productId);
}
