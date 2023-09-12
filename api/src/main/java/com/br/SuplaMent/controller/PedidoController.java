package com.br.SuplaMent.controller;

import com.br.SuplaMent.domain.pedido.CreatePedidoDTO;
import com.br.SuplaMent.domain.pedido.DetalhePedidoDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pedidos")
public class PedidoController {


    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid CreatePedidoDTO dados) {
        System.out.println(dados);
        return ResponseEntity.ok(new DetalhePedidoDTO(null, null, null, null));
    }
}
