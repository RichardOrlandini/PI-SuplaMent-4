package com.br.SuplaMent.controller;

import com.br.SuplaMent.model.Cliente;
import com.br.SuplaMent.model.repository.ClienteRepository;
import com.br.SuplaMent.model.dto.cliente.AtualizarClienteDTO;
import com.br.SuplaMent.model.dto.cliente.DetalhamentoClienteDTO;
import com.br.SuplaMent.model.dto.cliente.ListagemClienteDTO;
import com.br.SuplaMent.services.ClienteService;
import com.br.SuplaMent.model.dto.cliente.CadastroClienteDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/clientes")
@CrossOrigin("http://localhost:5173/**")
public class ClienteController {

        @Autowired
        private ClienteRepository repository;
        @Autowired
        private ClienteService service;
        @PostMapping
        @Transactional
        public ResponseEntity cadastrarCliente(@RequestBody @Valid CadastroClienteDTO dto, UriComponentsBuilder uriBuilder) {
            try {
                Cliente cliente = service.cadastrarCliente(dto);
                var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
                return ResponseEntity.created(uri).body(new DetalhamentoClienteDTO(cliente));
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());

            }
        }


        @GetMapping //("/busca/todos")
        public ResponseEntity<Page<ListagemClienteDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
            var page = repository.findAll(paginacao).map(ListagemClienteDTO::new);
            return ResponseEntity.ok(page);
        }
        @PutMapping
        @Transactional
        public ResponseEntity atualizar(@RequestBody @Valid AtualizarClienteDTO dto) {
            Cliente cliente = repository.getReferenceById(dto.id());
            cliente.atualizarInformacoesCliente(dto);
            return ResponseEntity.ok(new DetalhamentoClienteDTO(cliente));
        }
        @DeleteMapping("/{id}")
        @Transactional
        public ResponseEntity excluir(@PathVariable Long id) {
            var cliente = repository.getReferenceById(id);
            cliente.excluir();
            return ResponseEntity.noContent().build();
        }
        @PutMapping("/{id}")
        @Transactional
        public ResponseEntity ativa(@PathVariable Long id) {
            var cliente = repository.getReferenceById(id);
            cliente.ativa();
            return ResponseEntity.noContent().build();
        }
        @GetMapping("/{id}")
        public ResponseEntity detalhar(@PathVariable Long id) {
            var cliente = repository.getReferenceById(id);
            return ResponseEntity.ok(new DetalhamentoClienteDTO(cliente));
        }

        @GetMapping("/busca")
        @ResponseBody
        public ResponseEntity<Page<ListagemClienteDTO>> listar(@RequestParam String nome, @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
            var page = repository.findByNomeContaining(nome, paginacao).map(ListagemClienteDTO::new);
            return ResponseEntity.ok(page);
        }
        @GetMapping("/busca/ativos")
        @ResponseBody
        public ResponseEntity<Page<ListagemClienteDTO>> listarAtivos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
            var page = repository.findAllByActiveTrue(paginacao).map(ListagemClienteDTO::new);
            return ResponseEntity.ok(page);
        }
    }
