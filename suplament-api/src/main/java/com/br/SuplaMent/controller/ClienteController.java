package com.br.SuplaMent.controller;

//import com.br.SuplaMent.domain.cliente.Cliente;
import com.br.SuplaMent.domain.pessoa.Cliente;
//import com.br.SuplaMent.domain.pessoa.dto.DetalhamentoClienteDTO;
//import com.br.SuplaMent.domain.pessoa.dto.ListagemClienteDTO;
import com.br.SuplaMent.domain.pessoa.dto.CadastroDataCliente;
import com.br.SuplaMent.domain.pessoa.dto.DetalhamentoClienteDTO;
import com.br.SuplaMent.services.ClienteService;
//import com.br.SuplaMent.domain.usuario.Usuario;
//import com.br.SuplaMent.domain.usuario.dto.AtualizarUsuarioDTO;
//import com.br.SuplaMent.domain.usuario.dto.DetalhamentoUsuarioDTO;
//import com.br.SuplaMent.domain.usuario.dto.ListagemUsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<DetalhamentoClienteDTO> cadastrar(@RequestBody CadastroDataCliente dto,  UriComponentsBuilder uriBuilder) {
        Cliente clienteSalvo = clienteService.cadastrar(dto);

        var uri = uriBuilder.path("/cliente/{id}").buildAndExpand(clienteSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhamentoClienteDTO(clienteSalvo));
    }
}


//        @GetMapping //("/busca/todos")
//        public ResponseEntity<Page<ListagemClienteDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
//            var page = repository.findAll(paginacao).map(ListagemClienteDTO::new);
//            return ResponseEntity.ok(page);
//        }
//        @PutMapping
//        @Transactional
//        public ResponseEntity atualizar(@RequestBody @Valid AtualizarClienteDTO dto) {
//            Cliente cliente = repository.getReferenceById(dto.id());
//            cliente.atualizarInformacoesCliente(dto);
//            return ResponseEntity.ok(new DetalhamentoClienteDTO(cliente));
//        }
//        @DeleteMapping("/{id}")
//        @Transactional
//        public ResponseEntity excluir(@PathVariable Long id) {
//            var cliente = repository.getReferenceById(id);
//            cliente.excluir();
//            return ResponseEntity.noContent().build();
//        }
//        @PutMapping("/{id}")
//        @Transactional
//        public ResponseEntity ativa(@PathVariable Long id) {
//            var cliente = repository.getReferenceById(id);
//            cliente.ativa();
//            return ResponseEntity.noContent().build();
//        }
//        @GetMapping("/{id}")
//        public ResponseEntity detalhar(@PathVariable Long id) {
//            var cliente = repository.getReferenceById(id);
//            return ResponseEntity.ok(new DetalhamentoClienteDTO(cliente));
//        }
//
//        @GetMapping("/busca")
//        @ResponseBody
//        public ResponseEntity<Page<ListagemClienteDTO>> listar(@RequestParam String nome, @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
//            var page = repository.findByNomeContaining(nome, paginacao).map(ListagemClienteDTO::new);
//            return ResponseEntity.ok(page);
//        }
//        @GetMapping("/busca/ativos")
//        @ResponseBody
//        public ResponseEntity<Page<ListagemClienteDTO>> listarAtivos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
//            var page = repository.findAllByActiveTrue(paginacao).map(ListagemClienteDTO::new);
//            return ResponseEntity.ok(page);
//        }
//    }
