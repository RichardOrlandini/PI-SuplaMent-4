package com.br.SuplaMent.services;

import com.br.SuplaMent.domain.usuario.Usuario;
import com.br.SuplaMent.domain.usuario.UsuarioRepository;
import com.br.SuplaMent.domain.usuario.dto.CadastroUsuarioDTO;
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
        Usuario usuario = new Usuario(dto);
        return repository.save(usuario);
    }

}

