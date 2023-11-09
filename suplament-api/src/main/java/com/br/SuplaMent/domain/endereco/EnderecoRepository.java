package com.br.SuplaMent.domain.endereco;


import com.br.SuplaMent.domain.pessoa.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EnderecoRepository  extends JpaRepository<Endereco, Long> {

    List<Endereco> findByClienteId(Long clienteId);

    Endereco save(Endereco adiciona);
}
