package com.br.SuplaMent.controller;

import com.br.SuplaMent.domain.categoria.dto.CategoriaRequest;
import com.br.SuplaMent.domain.categoria.dto.CategoriaResponse;
import com.br.SuplaMent.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaResponse> save(@RequestBody CategoriaRequest request) {
        return ResponseEntity.ok(categoriaService.save(request));
    }

    @GetMapping
    public ResponseEntity<Page<CategoriaResponse>> findAll(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = categoriaService.findAll(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoriaResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.findByIdResponse(id));
    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<Page<CategoriaResponse>> findByDescricao(@PageableDefault(size = 10, sort = {"descricao"}) Pageable pag, @PathVariable String descricao) {
        var page = categoriaService.findByDescricao(pag, descricao);
        return ResponseEntity.ok(page);
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoriaResponse> update(@RequestBody CategoriaRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.update(request, id));
    }
}
