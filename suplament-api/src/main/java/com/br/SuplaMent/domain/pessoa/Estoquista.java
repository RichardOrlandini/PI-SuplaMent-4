package com.br.SuplaMent.domain.pessoa;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "Estoquista")
@Table(name = "estoquista")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estoquista extends Pessoa {

    private UserRole role;
    private boolean ativo;

    public boolean isAtivo() {
        return this.ativo;
    }
}
