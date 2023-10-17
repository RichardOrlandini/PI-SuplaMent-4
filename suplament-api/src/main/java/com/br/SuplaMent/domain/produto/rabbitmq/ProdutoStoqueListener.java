package com.br.SuplaMent.domain.produto.rabbitmq;

import com.br.SuplaMent.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoStoqueListener {

    @Autowired
    private ProdutoService produtoService;
    public void updateProdutoStoque() {

    }
}
