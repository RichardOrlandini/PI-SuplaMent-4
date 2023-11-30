package com.br.SuplaMent.domain.pessoa;

import com.br.SuplaMent.utils.aEntity.DomainEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class Pessoa extends DomainEntity {
    private static final long serialVersionUID = 1L;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "senha")
    private String senha;
}
