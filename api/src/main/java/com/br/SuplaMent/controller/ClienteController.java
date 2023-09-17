package com.br.SuplaMent.controller;

import com.br.SuplaMent.domain.cliente.Cliente;
import com.br.SuplaMent.domain.cliente.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping()
    public ResponseEntity<List<Cliente>> listar() {
        return new ResponseEntity<>(clienteService.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> ler(@PathVariable("id") Long id) {
        return new ResponseEntity<>(clienteService.getCliente(id), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
        clienteService.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}