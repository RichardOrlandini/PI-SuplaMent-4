package com.br.SuplaMent.services;

import com.br.SuplaMent.domain.pessoa.Cliente;
import com.br.SuplaMent.domain.pessoa.ClienteRepository;
import com.br.SuplaMent.domain.pessoa.Usuario;
import com.br.SuplaMent.infra.exception.ValidationExcepetion;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente findByEmail(String email) {
        Optional<Cliente> existe = clienteRepository.findByEmail(email);
        if (existe.isPresent()) {
            return  existe.get();
        }
        throw new ValidationExcepetion("Cliente não encontrado!");
    }

//    public Cliente cadastrarCliente(CadastroClienteDTO dto ) {
//        if (clienteRepository.findByEmail(dto.email()) != null) {
//            throw new IllegalArgumentException("O email já existe");
//        }
//        Cliente cliente = new Cliente(dto);
//        return clienteRepository.save(cliente);
//    }



}
