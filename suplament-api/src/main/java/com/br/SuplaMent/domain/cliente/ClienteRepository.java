package com.br.SuplaMent.domain.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long >{

  //  UserDetails findByEmail(String email);
    Page<Cliente> findAll(Pageable paginacao);
    Page<Cliente> findAllByActiveTrue(Pageable paginacao);
    Page<Cliente> findByNomeContaining(String nome, Pageable paginacao);
}
