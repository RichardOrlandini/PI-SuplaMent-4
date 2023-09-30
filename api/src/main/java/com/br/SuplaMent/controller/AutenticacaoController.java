package com.br.SuplaMent.controller;
import com.br.SuplaMent.model.dto.usuario.AutenticacaoDTO;
import com.br.SuplaMent.model.dto.usuario.UsuarioDTO;
import com.br.SuplaMent.model.dto.usuario.LoginDTO;
import com.br.SuplaMent.services.AutenticacaoService;
import com.br.SuplaMent.services.UsuarioService;
import com.br.SuplaMent.utils.security.tokenJwtDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:5173")
public class AutenticacaoController {
    @Autowired
    private AutenticacaoService autenticacaoService;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid AutenticacaoDTO autenticacaoDTO) {
        try {
            String token = autenticacaoService.gerarToken(autenticacaoDTO);
            UsuarioDTO usuarioDTO = usuarioService.buscarUsuarioPorEmail(autenticacaoDTO.email());
            if (usuarioDTO != null) {
                var response = new LoginDTO(new tokenJwtDTO(token), usuarioDTO);
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
