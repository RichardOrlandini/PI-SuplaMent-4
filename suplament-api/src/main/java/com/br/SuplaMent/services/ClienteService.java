package com.br.SuplaMent.services;

import com.br.SuplaMent.domain.endereco.Endereco;
import com.br.SuplaMent.domain.pessoa.Cliente;
import com.br.SuplaMent.domain.pessoa.ClienteRepository;
import com.br.SuplaMent.domain.pessoa.Usuario;
import com.br.SuplaMent.domain.pessoa.dto.CadastroInicialDTO;
import com.br.SuplaMent.infra.exception.ValidationExcepetion;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Cliente cadastrar(Cliente cliente) {
        validarDadosCliente(cliente);
//        cliente.setSenha(bCryptPasswordEncoder.encode(cliente.getSenha()));
//        cliente.setPassword(encryptPassword(cliente.getPassword()));
        return clienteRepository.save(cliente);
    }

    private void validarDadosCliente(Cliente cliente) {
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        if (clienteRepository.existsByCpf(cliente.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }
        // if (!cliente.isNomeValido()) {
        //  throw new NomeInvalidoException("O nome do cliente deve ter duas palavras com pelo menos 3 letras cada.");
        //   }
    }

    // caso a outra opçao de verificar os caracteres do nome n for viavel
//    public boolean isNomeValido(String nome) { // O nome do cliente tem que ter 2 palavras e no mínimo 3 letras em cada palavra.
//        return nome.matches("^([A-Za-z]{3,} ){1,}[A-Za-z]{3,}$");
//    }

//
//    teria q passasr no react assimm se n me engano
//    class RegisterForm extends React.Component {
//        state = {
//            email: '',
//                    cpf: '',
//                    name: '',
//                    birthDate: '',
//                    gender: '',
//                    cep: '',
//                    logradouro: '',
//                    numero: '',
//                    complemento: '',
//                    bairro: '',
//                    cidade: '',
//                    uf: '',
//                    enderecos: [],
//            password: ''
//        }
//
//        handleSubmit = async event => {
//            event.preventDefault();
//        const response = await axios.post('/api/clientes/register', this.state);
    //        se cadastrar correto viria pra ca
//            if (response.status === 200) {
//                this.props.history.push('/login');
//            }
//        }
//
//        render() {
//
//        }
//    }
}
