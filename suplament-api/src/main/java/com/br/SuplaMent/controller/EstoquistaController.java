package com.br.SuplaMent.controller;

import com.br.SuplaMent.domain.pedido.dto.AtualizaStatusPedidoDTO;
import com.br.SuplaMent.domain.pessoa.dto.ListagemUsuarioDTO;
import com.br.SuplaMent.services.EstoquistaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("estoquista")
@AllArgsConstructor
public class EstoquistaController {

    @Autowired
    private EstoquistaService estoquistaService;

    @GetMapping
    public ResponseEntity<Page<ListagemUsuarioDTO>> listarTodosPedidos(@PageableDefault(size = 10, sort = {"dataPedido.desc"}) Pageable paginacao) {
        var page = estoquistaService.listarTodosPedidos(paginacao);
        return ResponseEntity.ok(page);
    }
    @PutMapping
    public String atualizarStatusPedido(@RequestBody AtualizaStatusPedidoDTO atualizaStatusPedidoDTO){
        return estoquistaService.atualizaStatusPedido(atualizaStatusPedidoDTO);
    }
}
