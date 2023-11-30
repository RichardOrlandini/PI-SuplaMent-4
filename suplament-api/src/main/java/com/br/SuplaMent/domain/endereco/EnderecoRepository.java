package com.br.SuplaMent.domain.endereco;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EnderecoRepository  extends JpaRepository<Endereco, Long> {
    List<Endereco> findByClienteId(Long clienteId);
    Page<Endereco> findAllByActiveTrue(Pageable paginacao);
    Page<Endereco> findByLogradouroContaining(String logradouro, Pageable paginacao);
}
