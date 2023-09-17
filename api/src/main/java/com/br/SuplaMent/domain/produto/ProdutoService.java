package com.br.SuplaMent.domain.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository productRepository;

    public List<Produto> getAllProducts() {
        return productRepository.findAll();
    }

    public Produto getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void updateProductQuantity(Long id, int newQtd) {
        Produto product = getProductById(id);
        if (product != null) {
            product.setQtd(newQtd);
            productRepository.save(product);
        }
    }
}