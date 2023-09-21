package com.br.SuplaMent.controller;


import com.br.SuplaMent.domain.produto.Produto;
import com.br.SuplaMent.domain.produto.ProdutoRepository;
import com.br.SuplaMent.domain.produto.dto.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
@RestController
@RequestMapping("produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroProdutoDTO dto, UriComponentsBuilder uriBuilder) {
        if (repository.findByNome(dto.nome()) != null)  {
            return ResponseEntity.badRequest().body("O nome do produto já existe");
        }
        var produto = new Produto(dto);
        repository.save(produto);
        var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhamentoProdutoDTO(produto));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemProdutoDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByOrderByInsertionDateDesc(paginacao).map(ListagemProdutoDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid AtualizarProdutoDTO dto) {
        Produto produto = repository.getReferenceById(dto.id());
        produto.atualizarProduto(dto);
        return ResponseEntity.ok(new DetalhamentoProdutoDTO(produto));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleta(@PathVariable Long id) {
        var produto = repository.getReferenceById(id);
        produto.deleta();
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var produto = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhamentoProdutoDTO(produto));
    }
}

