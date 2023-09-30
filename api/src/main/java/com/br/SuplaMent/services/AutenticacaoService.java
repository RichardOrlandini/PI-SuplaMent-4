package com.br.SuplaMent.services;

import com.br.SuplaMent.model.Usuario;
import com.br.SuplaMent.model.dto.usuario.AutenticacaoDTO;
import com.br.SuplaMent.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email);
    }

    public String gerarToken(AutenticacaoDTO dadosLogin){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dadosLogin.email(), dadosLogin.senha());
        var authentication = manager.authenticate(authenticationToken);
        return tokenService.gerarToken((Usuario) authentication.getPrincipal());
    }
}
