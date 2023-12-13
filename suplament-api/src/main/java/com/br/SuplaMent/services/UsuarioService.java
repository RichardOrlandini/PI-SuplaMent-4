package com.br.SuplaMent.services;

import com.br.SuplaMent.domain.pessoa.Usuario;
import com.br.SuplaMent.domain.pessoa.UsuarioRepository;
import com.br.SuplaMent.domain.pessoa.dto.*;
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

    public Usuario create(Usuario dto) {
        this.validaEmailxistente(dto.getEmail());
        ////        String passwordEncoder = new BCryptPasswordEncoder().encode(dto.senha());
        return repository.save(dto);
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
        usuario.setActive(!usuario.getActive());
        repository.save(usuario);
        return usuario;
    }

    public List<Usuario> buscarUsuariosPorNome(String nome) {
        return repository.findByNome(nome);
    }

    public DetalhamentoUsuarioDTO update(AtualizarUsuarioDTO dto) {

        Usuario usuario = repository.getReferenceById(dto.id());
        usuario.atualizarInformacoes(dto);

        return new DetalhamentoUsuarioDTO(usuario);
    }

    public DetalhamentoUsuarioDTO details(Long id) {
        Usuario usuario = repository.getReferenceById(id);

        if (usuario != null) {
            return new DetalhamentoUsuarioDTO(usuario);
        }

        throw new ValidationExcepetion("Nenhum Usuario encontrado!");
    }
}

