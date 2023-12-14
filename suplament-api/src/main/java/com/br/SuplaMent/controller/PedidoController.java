package com.br.SuplaMent.controller;

import com.br.SuplaMent.domain.pedido.dto.DetalhamentoPedidoDTO;
import com.br.SuplaMent.domain.pedido.dto.DetalhamentoPedidoDTO;
import com.br.SuplaMent.services.PedidoService;
import com.br.SuplaMent.domain.pedido.dto.AvisoRetornoPedidoDTO;
import com.br.SuplaMent.domain.pedido.dto.CreatePedidoDTO;
import com.br.SuplaMent.domain.pedido.dto.ListagemPedidosDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("pedido")
public class PedidoController {

    @Autowired
    private final PedidoService pedidoService;

    @PostMapping
    @Transactional
    public void save(@RequestBody CreatePedidoDTO createPedidoDTO){
        pedidoService.save(createPedidoDTO);
    }
    @GetMapping("{id}")            //mandar id do cliente
    public List<ListagemPedidosDTO> findAll(@PathVariable Long id){
        return pedidoService.buscarPedidosDoCliente(id);
    }
    @GetMapping("detalhe/{id}")     //mandar id/numero do pedido
    public DetalhamentoPedidoDTO buscarResumoPedido(@PathVariable Long id){
        return pedidoService.buscarResumoPedido(id);
    }
}
