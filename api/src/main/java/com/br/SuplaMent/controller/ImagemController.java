package com.br.SuplaMent.controller;

import com.br.SuplaMent.domain.Imagem.ImagemRepository;
import com.br.SuplaMent.domain.produto.Produto;
import com.br.SuplaMent.domain.produto.ProdutoRepository;
import com.br.SuplaMent.domain.produto.dto.CadastroProdutoDTO;
import com.br.SuplaMent.domain.produto.dto.DetalhamentoProdutoDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/imagem")
public class ImagemController {

    private ImagemRepository imagemRepository;
    private ProdutoRepository produtoRepository;

   /* public ResponseEntity inserirImagem(@RequestBody @Valid long idProduto, MultipartFile file) {
        Produto produto = produtoRepository.findAllById(idProduto).get();
        if (this.repository.findByName(dto.nome()) != null) return ResponseEntity.badRequest().build();
        var produto = new Produto(dto.nome(), dto.descri(), dto.valor(), dto.qtd());
        repository.save(produto);
        var uri = uriBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhamentoProdutoDTO(produto));
    } */

}
