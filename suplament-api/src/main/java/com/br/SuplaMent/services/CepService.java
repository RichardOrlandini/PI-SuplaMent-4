package com.br.SuplaMent.services;


import com.br.SuplaMent.domain.endereco.Endereco;
import com.br.SuplaMent.domain.endereco.EnderecoRepository;
import com.br.SuplaMent.domain.pessoa.Cliente;
import com.br.SuplaMent.domain.pessoa.ClienteRepository;
import com.br.SuplaMent.domain.pessoa.Usuario;
import com.br.SuplaMent.domain.pessoa.dto.*;
import com.br.SuplaMent.infra.exception.ValidationExcepetion;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final  CepService cepService;
    final EnderecoService enderecoService;


    public boolean existsByEmail(String email) {
        return clienteRepository.existsByEmail(email);
    }
    public Optional<Cliente> findByEmail(String email) {
        try {
            return clienteRepository.findByEmail(email);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
    public boolean existsByCpf(String cpf) {
        return clienteRepository.existsByCpf(cpf);
    }
    public Cliente findById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public Cliente cadastrar(CadastroDataCliente dto) {
        try {
            if (dto == null || dto.client() == null) {
                throw new IllegalArgumentException("Dados do cliente não podem ser null");
            }
            Optional<Cliente> clienteExistente = clienteRepository.findByEmail(dto.client().email());
            if (clienteExistente.isPresent()) {
                throw new IllegalArgumentException("O email já existe");
            }
            this.validarDadosCliente(dto.client());
            this.encriptarSenha(dto.client().senha());

            Cliente cliente = new Cliente(dto);

            for (CadastroEnderecosDTO dtoEndereco : dto.enderecos()) {
                Endereco endereco = Endereco.of(dtoEndereco);
                endereco.setCliente(cliente);
                enderecoService.save(endereco);
            }

            return clienteRepository.save(cliente);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }




    //    public Cliente cadastrarCliente(CadastroDataCliente dto) {
//        if (clienteRepository.findByEmail(dto.client().email()) != null) {
//            throw new IllegalArgumentException("O email já existe");
//        }
//        Cliente cliente = new Cliente(dto);
//        return clienteRepository.save(cliente);
//    }
//    public Cliente cadastrar(CadastroClienteDTO client, CadastroEnderecosDTO[] enderecos) {
//        try {
//            this.validarDadosCliente(client);
//            this.encriptarSenha(client.senha());
//
//            Cliente cliente = new Cliente(client);
//
//            for (CadastroEnderecosDTO dtoEndereco : enderecos) {
//                Endereco endereco = new Endereco(dtoEndereco);
//                endereco.setCliente(cliente);
//                enderecoService.save(endereco);
//            }
//
//            return clienteRepository.save(cliente);
//
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//            throw e;
//        }
//    }
    private String encriptarSenha(String  senhaAntiga) {
             // client.setSenha(bCryptPasswordEncoder.encode(client.getSenha()));

        String senhaEncryptada = "";
        return senhaEncryptada;
    }


    private void validarDadosCliente(CadastroClienteDTO cliente) {
        Optional<Cliente> clienteExistente = clienteRepository.findByEmail(cliente.email());
        if (clienteExistente.isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        if (clienteRepository.existsByCpf(cliente.cpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }
    }


    public DetalhamentoClienteDTO uptade(AtualizarClienteDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.id()).orElseThrow(() -> new ValidationExcepetion("Cliente id" + dto.id()));
        cliente.atualizarInformacoesCliente(dto);
        //TODO ecriptar a senha



        //cliente.setPassword(passwordEncoder.encode(clienteDetalhes.getPassword()));

        return new DetalhamentoClienteDTO(clienteRepository.save(cliente));
    }
    public Cliente ativarDesativarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente == null) {
            return null;
        }

        cliente.setActive(!cliente.getActive());

        clienteRepository.save(cliente);
        return cliente;
    }


//    public Endereco adicionaEndereco(Long clienteId, Endereco adiciona) {
//        Cliente cliente = clienteRepository.findById(clienteId)
//                .orElseThrow(() -> new ValidationExcepetion("Nenhum client encontrado com o  id" + clienteId));
//        adiciona.set(cliente);
//        return enderecoRepository.save(adiciona);
//    }


    // caso a outra opçao de verificar os caracteres do nome n for viavel
//    public boolean isNomeValido(String nome) { // O nome do cliente tem que ter 2 palavras e no mínimo 3 letras em cada palavra.
//        return nome.matches("^([A-Za-z]{3,} ){1,}[A-Za-z]{3,}$");
//    }


}

