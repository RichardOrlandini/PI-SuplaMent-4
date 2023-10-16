package com.br.SuplaMent.domain.categoria;

import com.br.SuplaMent.domain.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Page<Categoria> findAll(Pageable pageable);
    Page<Categoria> findByDescricaoIgnoreCaseContaining(Pageable pageable, String descricao);
}
