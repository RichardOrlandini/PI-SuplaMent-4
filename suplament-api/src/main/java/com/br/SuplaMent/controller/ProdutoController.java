package com.br.SuplaMent.controller;

import com.br.SuplaMent.domain.produto.dto.*;
import com.br.SuplaMent.services.ProdutoService;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RestController
@AllArgsConstructor
@RequestMapping("produto")
public class ProdutoController {

    private static String pathImages = "client/public/images/";

    private final ProdutoService produtoService;
    @PostMapping
    public ProdutoResponseToSalesDTO save(
            @RequestParam("imagem") MultipartFile file,
            @RequestParam("json") String json) {

        ObjectMapper objectMapper = new ObjectMapper();
        ProdutoCreateDTO request = null;
        try {
            request = objectMapper.readValue(json, ProdutoCreateDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            if (!file.isEmpty()) {
                byte[] imagemEmBytes = file.getBytes();
                String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                String nomeImagem = String.valueOf(request.getNomeImagem()) + timeStamp+ "_" + file.getOriginalFilename();
                nomeImagem = nomeImagem.replace(" ", "");
                Path caminhoCompleto = Paths.get(pathImages + nomeImagem);
                Files.createDirectories(caminhoCompleto.getParent());
                Files.write(caminhoCompleto, imagemEmBytes);
                request.setNomeImagem(nomeImagem);
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
    public ResponseEntity<ProdutoResponseToSalesDTO> update(@RequestBody ProdutoCreateDTO request, @PathVariable Long id) {
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