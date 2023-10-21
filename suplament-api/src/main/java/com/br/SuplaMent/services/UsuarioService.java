package com.br.SuplaMent.services;

import com.br.SuplaMent.domain.pessoa.Usuario;
import com.br.SuplaMent.domain.pessoa.UsuarioRepository;
import com.br.SuplaMent.domain.pessoa.dto.CadastroInicialDTO;
import com.br.SuplaMent.infra.exception.ValidationExcepetion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public Usuario create(CadastroInicialDTO dto) {
        this.validaIdExistente(Long.valueOf(dto.id()));
        this.validaEmailxistente(dto.email());
        var usuario = new Usuario();
        usuario.setId(dto.id());
        usuario.setEmail(dto.email());
        return repository.save(usuario);
    }

    public Usuario findByEmail(String email) {
        Optional<Usuario> existe = repository.findByEmail((email));
        if (existe.isPresent()) {
            return  existe.get();
        }
        throw new ValidationExcepetion("Usuário não encontrado!");
    }

    private void validaIdExistente(Long id) {
        Optional<Usuario> existe = repository.findById((id));
        if (existe.isPresent()) {
           Long idEncontrado = existe.get().getId();
           if (idEncontrado.equals(id)) {
               throw new ValidationExcepetion("Já existe um Usuário com o id informado!");
           }
        }
    }

    private void validaEmailxistente(String email) {
        Optional<Usuario> existe = repository.findByEmail((email));
        if (existe.isPresent()) {
            String emailEncontrado = existe.get().getEmail();
            if (emailEncontrado.equals(email)) {
                throw new ValidationExcepetion("Já existe um Usuário com o email informado!");
            }
        }
    }




    //    public Usuario cadastrar(CadastroUsuarioDTO dto) {
//        if (repository.findByEmail(dto.email()) != null) {
//            throw new IllegalArgumentException("O email já existe");
//        }
//        String passwordEncoder = new BCryptPasswordEncoder().encode(dto.senha());
//        CadastroUsuarioDTO novoDto = new CadastroUsuarioDTO(
//                dto.nome(),
//                dto.email(),
//                passwordEncoder,
//                dto.cpf(),
//                dto.role());
//
//        Usuario usuario = new Usuario(novoDto);
//        return repository.save(usuario);
//    }

}

