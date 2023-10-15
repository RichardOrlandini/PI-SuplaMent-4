package com.br.SuplaMent.domain.usuario;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String email);
    Page<Usuario> findAll(Pageable paginacao);
    Page<Usuario> findAllByActiveTrue(Pageable paginacao);
    Page<Usuario> findByNomeContaining(String nome, Pageable paginacao);
}
