package com.br.SuplaMent.controller;

import com.br.SuplaMent.dto.CategoriaRequest;
import com.br.SuplaMent.dto.CategoriaResponse;
import com.br.SuplaMent.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public CategoriaResponse save(@RequestBody CategoriaRequest request) {
        return categoriaService.save(request);
    }
}
