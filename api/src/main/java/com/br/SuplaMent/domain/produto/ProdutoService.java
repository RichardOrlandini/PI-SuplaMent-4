package com.br.SuplaMent.domain.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService  implements ProdutoServiceInter {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    public Produto getProductById(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public void updateProdutoQuantity(Long id, int newQtd) {
        Produto product = getProductById(id);
        if (product != null) {
            product.setQtd(newQtd);
            produtoRepository.save(product);
        }
    }

    public Produto getProdutoById(Long produtoId) {
        return null;

    }



    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto saveProduto(Produto produto) {
        return produtoRepository.save(produto);
    }
}