package com.br.SuplaMent.controller;

import com.br.SuplaMent.domain.usuario.Usuario;
import com.br.SuplaMent.domain.usuario.UsuarioRepository;
import com.br.SuplaMent.domain.usuario.dto.DtoAtualizarUsuario;
import com.br.SuplaMent.domain.usuario.dto.DtoCadastroUsuario;
import com.br.SuplaMent.domain.usuario.dto.DtoDetalhamentoUsuario;
import com.br.SuplaMent.domain.usuario.dto.DtoListagemUsuario;
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
@RequestMapping("usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DtoCadastroUsuario dto, UriComponentsBuilder uriBuilder) {
        var usuario = new Usuario(dto);
        repository.save(usuario);
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DtoDetalhamentoUsuario(usuario));
    }
    @GetMapping
    public ResponseEntity<Page<DtoListagemUsuario>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByActiveTrue(paginacao).map(DtoListagemUsuario::new);
        return ResponseEntity.ok(page);
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DtoAtualizarUsuario dto) {
        var usuario = repository.getReferenceById(dto.id());
        usuario.atualizarInformacoes(dto);
        return ResponseEntity.ok(new DtoDetalhamentoUsuario(usuario));
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
        return ResponseEntity.ok(new DtoDetalhamentoUsuario(usuario));
    }
}
