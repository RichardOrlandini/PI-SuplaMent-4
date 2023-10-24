package com.br.SuplaMent.controller;

import com.br.SuplaMent.domain.categoria.dto.CategoriaRequest;
import com.br.SuplaMent.domain.categoria.dto.CategoriaResponse;
import com.br.SuplaMent.domain.fornecedor.dto.FornecedorCreateDTO;
import com.br.SuplaMent.domain.fornecedor.dto.FornecedorResponseDTO;
import com.br.SuplaMent.domain.produto.dto.ListagemProdutoDTO;
import com.br.SuplaMent.domain.produto.dto.ProdutoResponseToSalesDTO;
import com.br.SuplaMent.services.CategoriaService;
import com.br.SuplaMent.services.FornecedorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor

@RequestMapping("fornecedores")
public class FornecedorController {


    private final FornecedorService fornecedorService;

    @PostMapping
    public FornecedorResponseDTO save(@RequestBody FornecedorCreateDTO request) {
        return fornecedorService.save(request);
    }

    @GetMapping
    public ResponseEntity<Page<FornecedorResponseDTO>> findAll(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = fornecedorService.findAll(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("{id}")
    public FornecedorResponseDTO findById(@PathVariable Long id) {
        return fornecedorService.findByIdResponse(id);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Page<FornecedorResponseDTO>> findyByNome(@PageableDefault(size = 10, sort = {"nome"}) Pageable pag, @PathVariable String nome) {
        var page = fornecedorService.findByNome(pag, nome);
        return ResponseEntity.ok(page);
    }
    @PutMapping("{id}")
    public ResponseEntity<FornecedorResponseDTO> update(@RequestBody FornecedorCreateDTO request, @PathVariable Long id) {
        return ResponseEntity.ok(fornecedorService.update(request, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return ResponseEntity.ok(fornecedorService.delete(id));
    }
}
