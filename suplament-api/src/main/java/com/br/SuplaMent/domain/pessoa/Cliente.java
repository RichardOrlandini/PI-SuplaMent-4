package com.br.SuplaMent.domain.pessoa;

import com.br.SuplaMent.domain.pessoa.dto.AtualizarUsuarioDTO;
import com.br.SuplaMent.domain.pessoa.dto.CadastroUsuarioDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "Cliente")
@Table(name = "cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends Pessoa {

    private String cpf;
    public Cliente(CadastroUsuarioDTO dto) {
        this.setNome(dto.nome());
        this.setEmail(dto.email());
        this.setSenha(dto.senha());
    }

    public void atualizarInformacoes(AtualizarUsuarioDTO dto) {
        if (dto.senha() != null) {
            this.setSenha(dto.senha());
        }
        if (dto.nome() != null) {
            this.setNome(dto.nome());
        }

    }

    public void excluir() {
        this.setActive(false);
    }
    public void ativa() {
        this.setActive(true);
    }
}
