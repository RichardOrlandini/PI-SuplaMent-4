package com.br.SuplaMent.controller;

import com.br.SuplaMent.domain.produto.dto.*;
import com.br.SuplaMent.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    private static String caminhoImagens = "..api/src/main/resources/imagens";

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ProdutoResponseToSalesDTO save(@RequestBody ProdutoCreateToSalesDTO request) {
        return produtoService.save(request);
    }

    @PostMapping("check-stock")
    public ResponseEntity checkProdutoStoque(@RequestBody ProdutoCheckStoqueRequest request) {
        return  produtoService.checkProdutosStoque(request);
    }

    @GetMapping("index")
    public ResponseEntity<Page<ListagemProdutoDTO>> findAll(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = produtoService.findAll(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("{id}")
    public ProdutoResponseToSalesDTO findById(@PathVariable Long id) {
        return produtoService.findByIdResponse(id);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Page<ListagemProdutoDTO>> findByNome(@PageableDefault(size = 10, sort = {"nome"}) Pageable pag, @PathVariable String nome) {
        var page = produtoService.findByNome(pag, nome);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<Page<ListagemProdutoDTO>> findByCategoriasId(@PageableDefault(size = 10, sort = {"nome"}) Pageable pag, @PathVariable Long categoriaId) {
        var page = produtoService.findByCategorias(pag, categoriaId);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/fornecedor/{fornecedorId}")
    public ResponseEntity<Page<ListagemProdutoDTO>> findByFornecedoresId(@PageableDefault(size = 10, sort = {"nome"}) Pageable pag, @PathVariable Long fornecedorId) {
        var page = produtoService.findByFornecedores(pag, fornecedorId);
        return ResponseEntity.ok(page);
    }

    @GetMapping(name = "{id}/sales")
    public ProdutoSalesResponse findProductSales(@PathVariable Long id) {
        return produtoService.findProductsSales(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProdutoResponseToSalesDTO> update(@RequestBody ProdutoCreateToSalesDTO request, @PathVariable Long id) {
        return ResponseEntity.ok(produtoService.update(request, id));
    }
}

//
//    @GetMapping("/busca")
//    public ResponseEntity<Page<ListagemProdutoDTO>> listar(@RequestParam String nome, @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
//        var page = repository.findByNomeContaining(nome, paginacao).map(ListagemProdutoDTO::new);
//        return ResponseEntity.ok(page);
//    }