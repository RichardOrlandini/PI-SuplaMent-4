package com.br.SuplaMent.domain.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long >{
    Optional<Cliente> findByEmail(String email);
    Page<Cliente> findAll(Pageable paginacao);
    Page<Cliente> findAllByActiveTrue(Pageable paginacao);
    Page<Cliente> findByNomeContaining(String nome, Pageable paginacao);

    boolean findByCpf(String cpf);
}
