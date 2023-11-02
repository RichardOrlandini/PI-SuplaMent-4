package com.br.SuplaMent.controller;

import com.br.SuplaMent.domain.pessoa.Usuario;
import com.br.SuplaMent.domain.pessoa.dto.CadastroInicialDTO;
import com.br.SuplaMent.domain.pessoa.dto.DetalhamentoInicialUsuarioDTO;
import com.br.SuplaMent.domain.pessoa.dto.DetalhamentoUsuarioDTO;
import com.br.SuplaMent.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@AllArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService service;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid CadastroInicialDTO dto, UriComponentsBuilder uriBuilder) {
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

//    @GetMapping
//    public ResponseEntity<Page<ListagemUsuarioDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
//        var page = repository.findAll(paginacao).map(ListagemUsuarioDTO::new);
//        return ResponseEntity.ok(page);
//    }
//    @PutMapping
//    @Transactional
//    public ResponseEntity atualizar(@RequestBody @Valid AtualizarUsuarioDTO dto) {
//        Usuario usuario = repository.getReferenceById(dto.id());
//        usuario.atualizarInformacoes(dto);
//        return ResponseEntity.ok(new DetalhamentoUsuarioDTO(usuario));
//    }
//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity excluir(@PathVariable Long id) {
//        var usuario = repository.getReferenceById(id);
//        usuario.excluir();
//        return ResponseEntity.noContent().build();
//    }
//    @PutMapping("/{id}")
//    @Transactional
//    public ResponseEntity ativa(@PathVariable Long id) {
//        var usuario = repository.getReferenceById(id);
//        usuario.ativa();
//        return ResponseEntity.noContent().build();
//    }
//    @GetMapping("/{id}")
//    public ResponseEntity detalhar(@PathVariable Long id) {
//        var usuario = repository.getReferenceById(id);
//        return ResponseEntity.ok(new DetalhamentoUsuarioDTO(usuario));
//    }
//
//    @GetMapping("/busca")
//    @ResponseBody
//    public ResponseEntity<Page<ListagemUsuarioDTO>> listar(@RequestParam String nome, @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
//        var page = repository.findByNomeContaining(nome, paginacao).map(ListagemUsuarioDTO::new);
//        return ResponseEntity.ok(page);
//    }
//    @GetMapping("/busca/ativos")
//    @ResponseBody
//    public ResponseEntity<Page<ListagemUsuarioDTO>> listarAtivos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
//        var page = repository.findAllByActiveTrue(paginacao).map(ListagemUsuarioDTO::new);
//        return ResponseEntity.ok(page);
//    }
}
