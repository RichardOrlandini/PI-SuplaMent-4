package com.br.SuplaMent.controller;

import com.br.SuplaMent.domain.pessoa.Cliente;
import com.br.SuplaMent.domain.pessoa.dto.CadastroEnderecosDTO;
import com.br.SuplaMent.services.CepService;
import com.br.SuplaMent.services.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<CadastroEnderecosDTO>> save(@RequestBody List<CadastroEnderecosDTO> enderecosDTOS, UriComponentsBuilder uriBuilder) {
       // Endereco enderecoSalvo = CepService.BuscaCepDetalhes(dto);
        enderecoService.save(enderecosDTOS);
        var uri = uriBuilder.path("/cliente/{id}").buildAndExpand(enderecoSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhamentoClienteDTO(enderecoSalvo));
    }


    @GetMapping("/consulta")
    public  ResponseEntity consultaEndereco(@RequestBody Cliente clienteEndereco){
        return ResponseEntity.ok(cepService.fazValidaCep(String.valueOf(clienteEndereco)));

    }

//    @GetMapping("/status")
//    public String get(){
//        return "endereço atual" + enderecoService.getStatus();
//    }
//
//    @GetMapping("/cep/{cep}")
//    public Address getByCep(@PathVariable("cep") String cep) throws  NotReadyException{
//        return  this.enderecoService.getByCep(cep);
//
//    }

}
