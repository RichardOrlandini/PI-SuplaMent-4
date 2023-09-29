package com.br.SuplaMent.services;

import com.br.SuplaMent.model.Usuario;
import com.br.SuplaMent.model.repository.UsuarioRepository;
import com.br.SuplaMent.model.dto.usuario.CadastroUsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario cadastrar(CadastroUsuarioDTO dto) {
        if (repository.findByEmail(dto.email()) != null) {
            throw new IllegalArgumentException("O email já existe");
        }
        String passwordEncoder = new BCryptPasswordEncoder().encode(dto.senha());
        CadastroUsuarioDTO novoDto = new CadastroUsuarioDTO(
                dto.nome(),
                dto.email(),
                passwordEncoder,
                dto.cpf(),
                dto.role());

        Usuario usuario = new Usuario(novoDto);
        return repository.save(usuario);
    }

}

