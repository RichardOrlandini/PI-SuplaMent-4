package com.br.SuplaMent.controller;


import com.br.SuplaMent.domain.pessoa.Cliente;
import com.br.SuplaMent.domain.pessoa.ClienteRepository;
import com.br.SuplaMent.domain.pessoa.dto.AtualizarClienteDTO;
import com.br.SuplaMent.domain.pessoa.dto.CadastroDataCliente;
import com.br.SuplaMent.domain.pessoa.dto.DetalhamentoClienteDTO;
import com.br.SuplaMent.domain.produto.Produto;
import com.br.SuplaMent.services.ClienteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("cliente")
@AllArgsConstructor
public class ClienteController {

    private final  ClienteService clienteService;
    private final ClienteRepository repository;

    @PostMapping
    public ResponseEntity<DetalhamentoClienteDTO> cadastrar(@RequestBody CadastroDataCliente dto,  UriComponentsBuilder uriBuilder) {
        Cliente clienteSalvo = clienteService.cadastrar(dto);

        var uri = uriBuilder.path("/cliente/{id}").buildAndExpand(clienteSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhamentoClienteDTO(clienteSalvo));
    }


//        @GetMapping //("/busca/todos")
//        public ResponseEntity<Page<ListagemClienteDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
//            var page = repository.findAll(paginacao).map(ListagemClienteDTO::new);
//            return ResponseEntity.ok(page);
//        }
@PutMapping
@Transactional
public ResponseEntity atualizar(@RequestBody @Valid AtualizarClienteDTO dto) {
    Cliente cliente = repository.getReferenceById(dto.id());
    cliente.atualizarInformacoesCliente(dto);
    return ResponseEntity.ok(new DetalhamentoClienteDTO(cliente));
}

    @PutMapping("/{id}/ativarDesativar")
    public ResponseEntity<Cliente> ativarDesativarCliente(@PathVariable Long id) {
        Cliente cliente = clienteService.ativarDesativarCliente(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }
//        @GetMapping("/{id}")
//        public ResponseEntity detalhar(@PathVariable Long id) {
//            var cliente = repository.getReferenceById(id);
//            return ResponseEntity.ok(new DetalhamentoClienteDTO(cliente));
//        }
}
