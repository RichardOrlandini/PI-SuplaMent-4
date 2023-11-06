package com.br.SuplaMent.controller;

import com.br.SuplaMent.domain.pessoa.dto.DetalhamentoClienteDTO;
import com.br.SuplaMent.domain.pessoa.dto.DetalhamentoUsuarioDTO;
import com.br.SuplaMent.domain.pessoa.dto.LoginDTO;
import com.br.SuplaMent.services.ClienteService;
import com.br.SuplaMent.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/login") // ta com barra aqui
public class LoginController {

    private final UsuarioService usuarioService;
    private final ClienteService clienteService;

    //TODO login de admin implementar validação de senha
    @PostMapping("usuario")
    public ResponseEntity detalharUsuario(@RequestBody LoginDTO dto ) {
        var usuario = usuarioService.findByEmail(dto.email());
        if (usuario == null ){
            return  ResponseEntity.ok("nenhum usuario encontrado");
        }
        return ResponseEntity.ok(new DetalhamentoUsuarioDTO(usuario));
    }

    @PostMapping("cliente")
    public ResponseEntity detalharClient(@RequestBody LoginDTO dto ) {
        var client = clienteService.findByEmail(dto.email());
        if (client == null ){
            return  ResponseEntity.ok("nenhum usuario encontrado");
        }
        return ResponseEntity.ok(new DetalhamentoClienteDTO(client));
    }
}
