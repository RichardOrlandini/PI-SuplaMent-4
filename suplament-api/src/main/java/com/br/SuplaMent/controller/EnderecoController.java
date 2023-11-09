package com.br.SuplaMent.controller;

import com.br.SuplaMent.domain.endereco.dto.CadastroEnderecoDTO;
import com.br.SuplaMent.domain.pessoa.dto.DetalhamentoClienteDTO;
import com.br.SuplaMent.services.CepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

public class EnderecoController {

//    @PostMapping
//    public ResponseEntity<DetalhamentoEnderoDTO> cadastrar(@RequestBody CadastroEnderecoDTO dto, UriComponentsBuilder uriBuilder) {
//        Endereco enderecoSalvo = CepService.BuscaCepDetalhes(dto);
//
//        var uri = uriBuilder.path("/cliente/{id}").buildAndExpand(enderecoSalvo.getId()).toUri();
//        return ResponseEntity.created(uri).body(new DetalhamentoClienteDTO(enderecoSalvo));
//    }
}

