package com.br.SuplaMent.controller;


import com.br.SuplaMent.domain.endereco.Endereco;
import com.br.SuplaMent.domain.pessoa.Cliente;
import com.br.SuplaMent.domain.pessoa.ClienteRepository;
import com.br.SuplaMent.domain.pessoa.dto.AtualizarClienteDTO;
import com.br.SuplaMent.domain.pessoa.dto.CadastroDataCliente;
import com.br.SuplaMent.domain.pessoa.dto.CadastroEnderecosDTO;
import com.br.SuplaMent.domain.pessoa.dto.DetalhamentoClienteDTO;
import com.br.SuplaMent.services.CepService;
import com.br.SuplaMent.services.ClienteService;
import com.br.SuplaMent.services.EnderecoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("cliente")
@AllArgsConstructor
public class ClienteController {

    private final  ClienteService clienteService;
    private final ClienteRepository repository;
    final EnderecoService enderecoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhamentoClienteDTO> cadastrar(@RequestBody CadastroDataCliente dto,
                                                            @RequestBody List<CadastroEnderecosDTO> enderecosDTOS,
                                                            UriComponentsBuilder uriBuilder) {
        try {
            Cliente clienteSalvo = clienteService.cadastrar(dto,enderecosDTOS);
            CadastroEnderecosDTO dtoEndereco = enderecosDTOS.get(0);
            Endereco enderecoSalvo = (Endereco) CepService.BuscaCepDetalhes(String.valueOf(dtoEndereco));
            enderecoSalvo.setCliente(clienteSalvo);
            enderecoService.save((List<CadastroEnderecosDTO>) enderecoSalvo);

            var uri = uriBuilder.path("/cliente/{id}").buildAndExpand(clienteSalvo.getId()).toUri();
            return ResponseEntity.created(uri).body(new DetalhamentoClienteDTO(clienteSalvo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


//        @GetMapping //("/busca/todos")
//        public ResponseEntity<Page<ListagemClienteDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
//            var page = repository.findAll(paginacao).map(ListagemClienteDTO::new);
//            return ResponseEntity.ok(page);
//        }
    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid AtualizarClienteDTO dto) {
        DetalhamentoClienteDTO cliente = clienteService.uptade(dto);
         return ResponseEntity.ok(cliente);
    }


    // Nao lembro se cliente pode se desativar
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var cliente = repository.getReferenceById(id);
        cliente.excluir();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}/ativa")
    @Transactional
    public ResponseEntity ativa(@PathVariable Long id) {
        var cliente = repository.getReferenceById(id);
        cliente.ativa();
        return ResponseEntity.noContent().build();
    }
//        @GetMapping("/{id}")
//        public ResponseEntity detalhar(@PathVariable Long id) {
//            var cliente = repository.getReferenceById(id);
//            return ResponseEntity.ok(new DetalhamentoClienteDTO(cliente));
//        }
}
