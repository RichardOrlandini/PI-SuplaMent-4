package com.br.SuplaMent.domain.produto;

import com.br.SuplaMent.domain.categoria.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByNome(String nome);
    Page<Produto> findByCategoriaId(Pageable pageable, Long id);
    Page<Produto> findByFornecedorId(Pageable pageable, Long id);
    Page<Produto> findByNomeIgnoreCaseContaining(Pageable pageable, String nome);
    Boolean existsByCategoriaId(Long categoriaId);
    Boolean existsByFornecedorId(Long id);
}
