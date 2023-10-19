package com.br.SuplaMent.controller;


import com.br.SuplaMent.domain.categoria.dto.CategoriaResponse;
import com.br.SuplaMent.domain.fornecedor.dto.FornecedorCreateDTO;
import com.br.SuplaMent.domain.fornecedor.dto.FornecedorResponseDTO;
import com.br.SuplaMent.domain.produto.Produto;
import com.br.SuplaMent.domain.produto.ProdutoRepository;
import com.br.SuplaMent.domain.produto.dto.*;
import com.br.SuplaMent.services.CategoriaService;
import com.br.SuplaMent.services.ProdutoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@RestController
@RequestMapping("produto")
public class ProdutoController {

    private static String pathImages = "src/main/resources/imagens/";

    @Autowired
    private ProdutoService produtoService;

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

    @GetMapping
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

    @PutMapping("{id}")
    public ResponseEntity<ProdutoResponseToSalesDTO> update(@RequestBody ProdutoCreateToSalesDTO request, @PathVariable Long id) {
        return ResponseEntity.ok(produtoService.update(request, id));
    }


//    @PostMapping
//    @Transactional
//    public ResponseEntity cadastrar(@RequestBody @Valid CadastroProdutoDTO dto,
//                                    @RequestParam("nomeImagem") MultipartFile arquivo,
//                                    UriComponentsBuilder uriBuilder) {
//
//        if (repository.findByNome(dto.nome()) != null)  {
//            return ResponseEntity.badRequest().body("O nome do produto já existe");
//        }
//        var produto = new Produto(dto);
//        repository.save(produto);
//        produto = repository.findByNome(produto.getNome());
//        try{
//            if(!arquivo.isEmpty()){
//                byte[] imagemEmBytes = arquivo.getBytes();
//                Path caminhoCompleto = Paths.get(caminhoImagens + String.valueOf(produto.getId()) + arquivo.getOriginalFilename());
//                Files.write(caminhoCompleto, imagemEmBytes);
//
//                produto.setNomeImagem(String.valueOf(produto.getId()) + arquivo.getOriginalFilename());
//            }
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//
//
//        var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
//        return ResponseEntity.created(uri).body(new DetalhamentoProdutoDTO(produto));
//    }

//    @GetMapping
//    public ResponseEntity<Page<ListagemProdutoDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
//        var page = repository.findAllByOrderByInsertionDateDesc(paginacao).map(ListagemProdutoDTO::new);
//        return ResponseEntity.ok(page);
//    }
//
//    @GetMapping("/busca")
//    public ResponseEntity<Page<ListagemProdutoDTO>> listar(@RequestParam String nome, @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
//        var page = repository.findByNomeContaining(nome, paginacao).map(ListagemProdutoDTO::new);
//        return ResponseEntity.ok(page);
//    }

//    @PutMapping
//    @Transactional
//    public ResponseEntity atualizar(@RequestBody @Valid AtualizarProdutoDTO dto) {
//        Produto produto = repository.getReferenceById(dto.id());
//        produto.atualizarProduto(dto);
//        return ResponseEntity.ok(new DetalhamentoProdutoDTO(produto));
//    }
//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity deleta(@PathVariable Long id) {
//        var produto = repository.getReferenceById(id);
//        produto.deleta();
//        return ResponseEntity.noContent().build();
//    }
//    @PutMapping("/{id}")
//    @Transactional
//    public ResponseEntity ativa(@PathVariable Long id) {
//        var produto = repository.getReferenceById(id);
//        produto.ativa();
//        return ResponseEntity.noContent().build();
//    }
//    @GetMapping("/{id}")
//    public ResponseEntity detalhar(@PathVariable Long id) {
//        var produto = repository.getReferenceById(id);
//        return ResponseEntity.ok(new DetalhamentoProdutoDTO(produto));
//    }

}