package com.br.SuplaMent.domain.fornecedor;

import com.br.SuplaMent.domain.categoria.Categoria;
import com.br.SuplaMent.domain.fornecedor.Fornecedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    Page<Fornecedor> findByNomeIgnoreCaseContaining(Pageable pageable, String nome);

}
