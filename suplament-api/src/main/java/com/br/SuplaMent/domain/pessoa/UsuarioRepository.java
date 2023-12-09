package com.br.SuplaMent.domain.pessoa;


import com.br.SuplaMent.domain.produto.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
 Optional<Usuario> findByEmail(String email);
 Page<Usuario> findAll(Pageable paginacao);
 List<Usuario> findByNome(String nome);

   Page<Usuario> findAllByActiveTrue(Pageable paginacao);
    Page<Usuario> findByNomeContaining(String nome, Pageable paginacao);


}
