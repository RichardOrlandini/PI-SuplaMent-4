package com.br.SuplaMent.controller;

import com.br.SuplaMent.domain.pessoa.dto.DetalhamentoUsuarioDTO;
import com.br.SuplaMent.domain.pessoa.dto.LoginDto;
import com.br.SuplaMent.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final UsuarioService service;

    //TODO login de admin implementar validação de senha
    @PostMapping("usuario")
    public ResponseEntity detalhar(@RequestBody LoginDto dto ) {
        var usuario = service.findByEmail(dto.email());
        if (usuario == null ){
            return  ResponseEntity.ok("nenhum usuario encontrado");
        }
        return ResponseEntity.ok(new DetalhamentoUsuarioDTO(usuario));
    }

    //TODO login de CLient

}
