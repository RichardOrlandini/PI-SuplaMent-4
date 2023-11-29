package com.br.SuplaMent.services;

import com.br.SuplaMent.domain.endereco.Endereco;
import com.br.SuplaMent.domain.endereco.EnderecoRepository;
import com.br.SuplaMent.domain.pessoa.Cliente;
import com.br.SuplaMent.domain.pessoa.ClienteRepository;
import com.br.SuplaMent.domain.pessoa.dto.CadastroClienteDTO;
import com.br.SuplaMent.domain.pessoa.dto.CadastroDataCliente;
import com.br.SuplaMent.domain.pessoa.dto.CadastroEnderecosDTO;
import com.br.SuplaMent.infra.exception.ValidationExcepetion;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    @Autowired
    private  CepService cepService;

    public Cliente findByEmail(String email) {
        Cliente existe = clienteRepository.findByEmail(email).orElse(null);
        if (existe != null) {
            return  existe;
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

    public Cliente cadastrar(CadastroDataCliente dto) {
        if (!cepService.fazValidaCep(Arrays.toString(dto.enderecos()))) {}
        this.validarDadosCliente(dto.client());
        this.validarDadosEndereco(dto.enderecos());
        var client = this.encriptarSenha(dto.client());


        return clienteRepository.save(client);
    }

    private Cliente encriptarSenha(CadastroClienteDTO dto) {
        var client = new Cliente(dto);
        //logivca para encryptar e passar o objeto pro client.
             // client.setSenha(bCryptPasswordEncoder.encode(client.getSenha()));
        return client;
    }

    private void validarDadosEndereco(CadastroEnderecosDTO[] enderecos) {
        for (CadastroEnderecosDTO endereco: enderecos) {
            //logiaa de validação de cep e etc
            //se algo der errado lançar o erro e para, deixa as validção em metétod separados e lance
            //as exeções nesses métodos!

        }
    }

    private void validarDadosCliente(CadastroClienteDTO cliente) {
        this.findByEmail(cliente.email());

        if (clienteRepository.findByCpf(cliente.cpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }

        //..

        // if (!cliente.isNomeValido()) {
        //  throw new NomeInvalidoException("O nome do cliente deve ter duas palavras com pelo menos 3 letras cada.");
        //   }
    }
    public Cliente uptade(Long id, Cliente clienteDetalhes) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ValidationExcepetion("Cliente id" + id));

        cliente.setNome(clienteDetalhes.getNome());
        cliente.setDataNascimento(clienteDetalhes.getDataNascimento());
        cliente.setGenero(clienteDetalhes.getGenero());
        //cliente.setPassword(passwordEncoder.encode(clienteDetalhes.getPassword()));

        return clienteRepository.save(cliente);
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
