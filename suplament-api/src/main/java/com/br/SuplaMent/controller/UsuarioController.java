package com.br.SuplaMent.controller;

import com.br.SuplaMent.domain.produto.dto.ListagemProdutoDTO;
import com.br.SuplaMent.domain.usuario.Usuario;
import com.br.SuplaMent.domain.usuario.UsuarioRepository;
import com.br.SuplaMent.domain.usuario.dto.AtualizarUsuarioDTO;
import com.br.SuplaMent.domain.usuario.dto.CadastroUsuarioDTO;
import com.br.SuplaMent.domain.usuario.dto.DetalhamentoUsuarioDTO;
import com.br.SuplaMent.domain.usuario.dto.ListagemUsuarioDTO;
import com.br.SuplaMent.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("http://localhost:5173/**")
@Service
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private UsuarioService service;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroUsuarioDTO dto, UriComponentsBuilder uriBuilder) {
        try {
            Usuario usuario = service.cadastrar(dto);
            var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
            return ResponseEntity.created(uri).body(new DetalhamentoUsuarioDTO(usuario));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @GetMapping
    public ResponseEntity<Page<ListagemUsuarioDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(ListagemUsuarioDTO::new);
        return ResponseEntity.ok(page);
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid AtualizarUsuarioDTO dto) {
        Usuario usuario = repository.getReferenceById(dto.id());
        usuario.atualizarInformacoes(dto);
        return ResponseEntity.ok(new DetalhamentoUsuarioDTO(usuario));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var usuario = repository.getReferenceById(id);
        usuario.excluir();
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity ativa(@PathVariable Long id) {
        var usuario = repository.getReferenceById(id);
        usuario.ativa();
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var usuario = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhamentoUsuarioDTO(usuario));
    }

    @GetMapping("/busca")
    @ResponseBody
    public ResponseEntity<Page<ListagemUsuarioDTO>> listar(@RequestParam String nome, @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findByNomeContaining(nome, paginacao).map(ListagemUsuarioDTO::new);
        return ResponseEntity.ok(page);
    }
    @GetMapping("/busca/ativos")
    @ResponseBody
    public ResponseEntity<Page<ListagemUsuarioDTO>> listarAtivos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByActiveTrue(paginacao).map(ListagemUsuarioDTO::new);
        return ResponseEntity.ok(page);
    }
}