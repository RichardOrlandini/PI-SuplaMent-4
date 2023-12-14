package com.br.SuplaMent.controller;


import com.br.SuplaMent.domain.endereco.Endereco;
import com.br.SuplaMent.domain.pessoa.Cliente;
import com.br.SuplaMent.domain.pessoa.ClienteRepository;
import com.br.SuplaMent.domain.pessoa.dto.*;
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

import java.util.Arrays;
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
    public ResponseEntity<DetalhamentoClienteDTO> cadastrar(@RequestBody CadastroDataCliente dto, UriComponentsBuilder uriBuilder) {
        try {
            if (dto == null || dto.client() == null) {
                throw new IllegalArgumentException("Dados do cliente não podem ser null");
            }
            Cliente clienteSalvo = clienteService.cadastrar(dto);
            CadastroEnderecosDTO dtoEndereco = dto.enderecos()[0];

            // Validar o CEP
            String cep = String.valueOf(dtoEndereco);
            System.out.println("Validating CEP: " + cep);
            if (!CepService.fazValidaCep(cep)) {
                throw new IllegalArgumentException("CEP inválido: " + cep);
            }

            Endereco enderecoSalvo = (Endereco) CepService.BuscaCepDetalhes(cep);
            enderecoSalvo.setCliente(clienteSalvo);
            enderecoService.save(Arrays.asList(dto.enderecos()));

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

    @GetMapping("/cliente/{id}")
    public ResponseEntity<DetalhamentoClienteDTO> getCliente(@PathVariable Long id) {
        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.ok(new DetalhamentoClienteDTO(cliente));
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

}

