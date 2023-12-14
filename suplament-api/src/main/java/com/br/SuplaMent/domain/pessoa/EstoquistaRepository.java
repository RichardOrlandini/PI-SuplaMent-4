package com.br.SuplaMent.domain.pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoquistaRepository extends JpaRepository<Estoquista, Long> {
}
