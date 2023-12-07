package com.br.SuplaMent.controller;

import com.br.SuplaMent.domain.endereco.Endereco;
import com.br.SuplaMent.domain.pessoa.Cliente;
import com.br.SuplaMent.domain.pessoa.dto.CadastroEnderecosDTO;
import com.br.SuplaMent.domain.pessoa.dto.DetalhamentoClienteDTO;
import com.br.SuplaMent.services.CepService;
import com.br.SuplaMent.services.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("endereco")
public class EnderecoController {

    final EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<DetalhamentoClienteDTO> save(@RequestBody List<CadastroEnderecosDTO> enderecosDTOS, UriComponentsBuilder uriBuilder) {
        CadastroEnderecosDTO dto = enderecosDTOS.get(0);
        Endereco enderecoSalvo = (Endereco) CepService.BuscaCepDetalhes(String.valueOf(dto));
        enderecoService.save(enderecosDTOS);
        var uri = uriBuilder.path("/cliente/{id}").buildAndExpand(enderecoSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhamentoClienteDTO(enderecoSalvo.getCliente()));
    }

    @GetMapping("/consulta")
    public  ResponseEntity consultaEndereco(@RequestBody Cliente clienteEndereco){
        return ResponseEntity.ok(CepService.fazValidaCep(String.valueOf(clienteEndereco)));

    }
    @PutMapping("/enderecos/principal")
    public ResponseEntity<Endereco> setPrincipalAddress(@RequestBody Long addId) {
        enderecoService.setPrincipalAddress(addId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
