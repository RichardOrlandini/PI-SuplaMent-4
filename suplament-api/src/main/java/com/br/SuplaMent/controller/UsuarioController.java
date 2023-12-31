package com.br.SuplaMent.controller;

import com.br.SuplaMent.domain.pessoa.Usuario;
import com.br.SuplaMent.domain.pessoa.dto.*;
import com.br.SuplaMent.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("usuario")
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping()
    @Transactional
    public ResponseEntity createInitial(@RequestBody @Valid Usuario dto, UriComponentsBuilder uriBuilder) {
        try {
            Usuario usuario = service.create(dto);
            var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
            return ResponseEntity.created(uri).body(new DetalhamentoInicialUsuarioDTO(usuario));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("email/{email}")
    public ResponseEntity detalhar(@PathVariable String email) {
        var usuario = service.findByEmail(email);
        return ResponseEntity.ok(new DetalhamentoUsuarioDTO(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemUsuarioDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = service.findAll(paginacao);
        return ResponseEntity.ok(page);
    }
    @PutMapping("{id}/ativarDesativar")
    public ResponseEntity<Usuario> ativarDesativarUsuario(@PathVariable Long id) {
        Usuario usuario = service.ativarDesativarUsuario(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }
    @GetMapping("busca")
    public List<Usuario> buscarPorNome(@RequestParam String nome) {
        return service.buscarUsuariosPorNome(nome);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid AtualizarUsuarioDTO dto) {
        DetalhamentoUsuarioDTO usuario = service.update(dto);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        DetalhamentoUsuarioDTO usuario = service.details(id);
        return ResponseEntity.ok(usuario);
    }

}
