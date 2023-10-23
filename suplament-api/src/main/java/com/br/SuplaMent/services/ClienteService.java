package com.br.SuplaMent.services;

import com.br.SuplaMent.domain.pessoa.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClienteService {


    private final ClienteRepository clienteRepository;

//    public Cliente cadastrarCliente(CadastroClienteDTO dto ) {
//        if (clienteRepository.findByEmail(dto.email()) != null) {
//            throw new IllegalArgumentException("O email já existe");
//        }
//        Cliente cliente = new Cliente(dto);
//        return clienteRepository.save(cliente);
//    }



}
