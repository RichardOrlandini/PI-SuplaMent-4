package com.br.SuplaMent.controller;


import com.br.SuplaMent.model.Produto;
import com.br.SuplaMent.model.dto.produto.AtualizarProdutoDTO;
import com.br.SuplaMent.model.dto.produto.CadastroProdutoDTO;
import com.br.SuplaMent.model.dto.produto.DetalhamentoProdutoDTO;
import com.br.SuplaMent.model.dto.produto.ListagemProdutoDTO;
import com.br.SuplaMent.model.repository.ProdutoRepository;
import com.br.SuplaMent.services.ProdutoService;
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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    private static String caminhoImagens = "..api/src/main/resources/imagens";
    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private ProdutoService service;
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroProdutoDTO dto,
                                    @RequestParam("nomeImagem") MultipartFile arquivo,
                                    UriComponentsBuilder uriBuilder) {

        if (repository.findByNome(dto.nome()) != null)  {
            return ResponseEntity.badRequest().body("O nome do produto já existe");
        }
        var produto = new Produto(dto);
        repository.save(produto);
        produto = repository.findByNome(produto.getNome());
        try{
            if(!arquivo.isEmpty()){
                byte[] imagemEmBytes = arquivo.getBytes();
                Path caminhoCompleto = Paths.get(caminhoImagens + String.valueOf(produto.getId()) + arquivo.getOriginalFilename());
                Files.write(caminhoCompleto, imagemEmBytes);

                produto.setNomeImagem(String.valueOf(produto.getId()) + arquivo.getOriginalFilename());
            }
        }catch(IOException e){
            e.printStackTrace();
        }


        var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhamentoProdutoDTO(produto));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemProdutoDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByOrderByInsertionDateDesc(paginacao).map(ListagemProdutoDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/busca")
    public ResponseEntity<Page<ListagemProdutoDTO>> listar(@RequestParam String nome, @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findByNomeContaining(nome, paginacao).map(ListagemProdutoDTO::new);
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
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity ativa(@PathVariable Long id) {
        var produto = repository.getReferenceById(id);
        produto.ativa();
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var produto = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhamentoProdutoDTO(produto));
    }

}