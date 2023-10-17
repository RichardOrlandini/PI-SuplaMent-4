package com.br.SuplaMent.domain.aEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractDomainEntityRepository<T extends DomainEntity> extends JpaRepository<T, Long> {
}
