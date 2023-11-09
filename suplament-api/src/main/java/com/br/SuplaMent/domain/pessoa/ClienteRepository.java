package com.br.SuplaMent.domain.cliente;

import com.br.SuplaMent.domain.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long >{
<<<<<<< Updated upstream:api/src/main/java/com/br/SuplaMent/domain/cliente/ClienteRepository.java

<<<<<<< Updated upstream:api/src/main/java/com/br/SuplaMent/domain/cliente/ClienteRepository.java
    UserDetails findByEmail(String email);
=======
//dsfds
//   Optional<Cliente> findByEmail(String email);
>>>>>>> Stashed changes:suplament-api/src/main/java/com/br/SuplaMent/domain/pessoa/ClienteRepository.java
=======
  Optional<Cliente> findByEmail(String email);
>>>>>>> Stashed changes:suplament-api/src/main/java/com/br/SuplaMent/domain/pessoa/ClienteRepository.java
    Page<Cliente> findAll(Pageable paginacao);
    Page<Cliente> findAllByActiveTrue(Pageable paginacao);
    Page<Cliente> findByNomeContaining(String nome, Pageable paginacao);

<<<<<<< Updated upstream:api/src/main/java/com/br/SuplaMent/domain/cliente/ClienteRepository.java
  //Optional<Cliente> findByEmail(String email);
=======

>>>>>>> Stashed changes:suplament-api/src/main/java/com/br/SuplaMent/domain/pessoa/ClienteRepository.java


  Optional<Cliente> findByEmail(String email);

}
