package com.br.SuplaMent.domain.cliente;

import com.br.SuplaMent.domain.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long >{

    UserDetails findByEmail(String email);
    Page<Cliente> findAll(Pageable paginacao);
    Page<Cliente> findAllByActiveTrue(Pageable paginacao);
    Page<Cliente> findByNomeContaining(String nome, Pageable paginacao);
}