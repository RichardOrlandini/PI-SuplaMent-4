package com.br.SuplaMent.controller;

import com.br.SuplaMent.domain.pedido.PedidoService;
import com.br.SuplaMent.domain.pedido.dto.AvisoRetornoPedidoDTO;
import com.br.SuplaMent.domain.pedido.dto.CreatePedidoDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("pedido")
public class PedidoController {

    @Autowired
    private final PedidoService pedidoService;

    @PostMapping
    public AvisoRetornoPedidoDTO save(@RequestBody CreatePedidoDTO createPedidoDTO ){
        AvisoRetornoPedidoDTO avisoRetornoPedidoDTO = pedidoService.save(createPedidoDTO);
        return avisoRetornoPedidoDTO;
    }
}
