package com.br.SuplaMent.controller;

import com.br.SuplaMent.domain.usuario.Usuario;
import com.br.SuplaMent.domain.usuario.UsuarioRepository;
import com.br.SuplaMent.domain.usuario.dto.AtualizarUsuarioDTO;
import com.br.SuplaMent.domain.usuario.dto.CadastroUsuarioDTO;
import com.br.SuplaMent.domain.usuario.dto.DetalhamentoUsuarioDTO;
import com.br.SuplaMent.domain.usuario.dto.ListagemUsuarioDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroUsuarioDTO dto, UriComponentsBuilder uriBuilder) {
        if (this.repository.findByEmail(dto.email()) != null) return ResponseEntity.badRequest().build();
        String senhaEncriptada = new BCryptPasswordEncoder().encode(dto.senha());
        var usuario = new Usuario(dto.nome(), dto.email(), dto.senha(), dto.role());
        repository.save(usuario);
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhamentoUsuarioDTO(usuario));
    }
    @GetMapping
    public ResponseEntity<Page<ListagemUsuarioDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByActiveTrue(paginacao).map(ListagemUsuarioDTO::new);
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
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var usuario = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhamentoUsuarioDTO(usuario));
    }
}
