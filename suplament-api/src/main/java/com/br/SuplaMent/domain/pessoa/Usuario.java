package com.br.SuplaMent.domain.pessoa;

import com.br.SuplaMent.domain.pessoa.dto.AtualizarUsuarioDTO;
import com.br.SuplaMent.domain.pessoa.dto.CadastroUsuarioDTO;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "Usuario")
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario extends Pessoa {

    @Column(name = "role", nullable = true)
    private UserRole role;
    public Usuario(CadastroUsuarioDTO dto) {
        this.setNome(dto.nome());
        this.setEmail(dto.email());
        this.setSenha(dto.senha());
        this.role = dto.role();
    }

    public void atualizarInformacoes(AtualizarUsuarioDTO dto) {
        if (dto.senha() != null) {
            this.setSenha(dto.senha());
        }
        if (dto.nome() != null) {
            this.setNome(dto.nome());
        }
        if (dto.role() != null) {
            this.role = dto.role();
        }
    }
}
