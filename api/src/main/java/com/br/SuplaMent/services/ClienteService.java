package com.br.SuplaMent.services;

import com.br.SuplaMent.model.Cliente;
import com.br.SuplaMent.model.repository.ClienteRepository;
import com.br.SuplaMent.model.dto.cliente.CadastroClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente cadastrarCliente(CadastroClienteDTO dto ) {
        if (clienteRepository.findByEmail(dto.email()) != null) {
            throw new IllegalArgumentException("O email já existe");
        }
        Cliente cliente = new Cliente(dto);
        return clienteRepository.save(cliente);
    }



}
