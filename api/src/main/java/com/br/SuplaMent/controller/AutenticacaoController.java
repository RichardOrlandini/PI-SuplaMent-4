package com.br.SuplaMent.controller;

import com.br.SuplaMent.domain.usuario.autenticacaoDTO;
import com.br.SuplaMent.domain.usuario.Usuario;
import com.br.SuplaMent.domain.usuario.UsuarioRepository;
import com.br.SuplaMent.domain.usuario.dto.detalhamentoUsuarioDTO;
import com.br.SuplaMent.domain.usuario.dto.loginDTO;
import com.br.SuplaMent.infra.security.tokenJwtDTO;
import com.br.SuplaMent.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:5173")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid autenticacaoDTO dados) {
        try {
            System.out.println(dados);
            var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
            var authentication = manager.authenticate(authenticationToken);
            var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

            Usuario user = (Usuario) repository.findByEmail(dados.email());
            if (user != null) {
                var response = new loginDTO(new tokenJwtDTO(tokenJWT),
                        new detalhamentoUsuarioDTO(user.getId(), user.getNome(), user.getEmail(), user.getGrupo()));
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body("Usuário não encontrado");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
