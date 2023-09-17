package com.br.SuplaMent.domain.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByNome(String nome);
  //  List<Produto> findByCategoria(Categorias categoria);

    Page<Produto> findAllByActiveTrue(Pageable paginacao);

   // List<Produto> findByNomeContaining(String nome);
   // List<Produto> findAllByOrderByDataInsercaoDesc();
   // List<Produto> findByNomeContainingIgnoreCase(String nome);
//    List<Produto> findByNomeContainingIgnoreCaseOrderByDataInsercaoDesc(String nome);
}
