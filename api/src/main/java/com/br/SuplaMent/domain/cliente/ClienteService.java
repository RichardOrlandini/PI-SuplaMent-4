package com.br.SuplaMent.domain.cliente;

import com.br.SuplaMent.domain.cliente.dto.ClienteNaoEncontradoException;
import com.br.SuplaMent.domain.cliente.dto.IncluirClienteRequest;
import com.br.SuplaMent.domain.cliente.dto.AtualizarClienteRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente getCliente(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado " + id));
    }

    public Cliente incluir(IncluirClienteRequest clienteRequest) {
        var cliente = new Cliente();
        BeanUtils.copyProperties(clienteRequest, cliente);
        clienteRepository.save(cliente);

        return cliente;
    }

    public Cliente atualizar(AtualizarClienteRequest atualizarClienteRequest) {
        var cliente = clienteRepository.findById(atualizarClienteRequest.getId()).get();

        BeanUtils.copyProperties(atualizarClienteRequest, cliente);
        clienteRepository.save(cliente);
        return cliente;
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}