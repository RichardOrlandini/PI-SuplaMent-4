package com.br.SuplaMent.controller;

import com.br.SuplaMent.domain.produto.dto.*;
import com.br.SuplaMent.services.ProdutoService;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("produto")
public class ProdutoController {

    private static String pathImages = "src/main/resources/imagens/";

    private final ProdutoService produtoService;

    @PostMapping
    public ProdutoResponseToSalesDTO save(
            @RequestParam("imagem") MultipartFile file,
            @RequestParam("json") String json) {

        ObjectMapper objectMapper = new ObjectMapper();
        ProdutoCreateToSalesDTO request = null;
        try {
            request = objectMapper.readValue(json, ProdutoCreateToSalesDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            if (!file.isEmpty()) {
                byte[] imagemEmBytes = file.getBytes();
                Path caminhoCompleto = Paths.get(pathImages + String.valueOf(request.getNomeImagem()) + file.getOriginalFilename());
                Files.write(caminhoCompleto, imagemEmBytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return produtoService.delete(id);
    }


    //   TODO rodrigo faz ai mano:
//    @PostMapping("inativar/{id}")
//    public ResponseEntity inativar(@PathVariable Long id) {
//        return produtoService.inativar(id);
//    }

}

//
//    @GetMapping("/busca")
//    public ResponseEntity<Page<ListagemProdutoDTO>> listar(@RequestParam String nome, @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
//        var page = repository.findByNomeContaining(nome, paginacao).map(ListagemProdutoDTO::new);
//        return ResponseEntity.ok(page);
//    }