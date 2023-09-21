package com.br.SuplaMent.domain.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Produto findByNome(String nome);
    Page<Produto> findAllByActiveTrue(Pageable paginacao);

    Page<Produto> findAllByOrderByInsertionDateDesc(Pageable paginacao);

    // List<Produto> findAllByOrderByDataInsercaoDesc();
    // List<Produto> findByNomeContainingIgnoreCase(String nome);
    // List<Produto> findByNomeContainingIgnoreCaseOrderByDataInsercaoDesc(String nome);
}
