package com.br.SuplaMent.domain.endereco;

import com.br.SuplaMent.domain.pedido.Pedido;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EnderecoRepository {

    List<Endereco> findByClienteId(Long clienteId);

    Endereco save(Endereco adiciona);
}
