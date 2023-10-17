package com.br.SuplaMent.domain.pessoa;


import com.br.SuplaMent.domain.aEntity.AbstractDomainEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
 Optional<Usuario> findByEmail(String email);

  //  UserDetails findByEmail(String email);
    //Page<Usuario> findAll(Pageable paginacao);
//    Page<Usuario> findAllByActiveTrue(Pageable paginacao);
//    Page<Usuario> findByNomeContaining(String nome, Pageable paginacao);

// @Query("SELECT u FROM Usuario u WHERE u.email = :email")
// Optional<Usuario> findByEmail(@Param("email") String email);

}