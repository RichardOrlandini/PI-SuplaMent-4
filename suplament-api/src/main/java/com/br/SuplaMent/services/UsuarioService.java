package com.br.SuplaMent.services;

import com.br.SuplaMent.domain.pessoa.Cliente;
import com.br.SuplaMent.domain.pessoa.Usuario;
import com.br.SuplaMent.domain.pessoa.UsuarioRepository;
import com.br.SuplaMent.domain.pessoa.dto.CadastroInicialDTO;
import com.br.SuplaMent.domain.pessoa.dto.ListagemUsuarioDTO;
import com.br.SuplaMent.domain.produto.Produto;
import com.br.SuplaMent.infra.exception.ValidationExcepetion;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public Usuario create(CadastroInicialDTO dto) {
        ////        String passwordEncoder = new BCryptPasswordEncoder().encode(dto.senha());
        this.validaIdExistente(Long.valueOf(dto.id()));
        this.validaEmailxistente(dto.email());
        var usuario = new Usuario();
        usuario.setId(dto.id());
        usuario.setEmail(dto.email());
        return repository.save(usuario);
    }

    public Usuario findByEmail(String email) {
        Optional<Usuario> existe = repository.findByEmail(email);
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

    public Page findAll(Pageable paginacao) {
        return repository.findAll(paginacao).map(ListagemUsuarioDTO::new);
    }

    public Usuario ativarDesativarUsuario(Long id) {
        Usuario usuario = repository.findById(id).orElse(null);
        if (usuario == null) {
            return null;
        }
        usuario.setAtivo(!usuario.isAtivo());
        repository.save(usuario);
        return usuario;
    }

    public List<Usuario> buscarUsuariosPorNome(String nome) {
        return repository.findByNome(nome);
    }
}

