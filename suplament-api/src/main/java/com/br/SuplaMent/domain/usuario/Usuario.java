package com.br.SuplaMent.domain.usuario;

import com.br.SuplaMent.domain.endereco.Endereco;
import com.br.SuplaMent.domain.usuario.dto.AtualizarUsuarioDTO;
import com.br.SuplaMent.domain.usuario.dto.CadastroUsuarioDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity(name = "Usuario")
@Table(name = "usuario")
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean active;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String telefone;
    private UserRole role;
    @OneToOne
    private Endereco endereco;


    public Usuario () {

    }
    public Usuario(CadastroUsuarioDTO dto) {
        this.active = true;
        this.nome = dto.nome();
        this.email = dto.email();
        this.senha = dto.senha();
        this.role = dto.role();
        this.cpf = dto.cpf();
        this.telefone = null;
        this.endereco = null;
    }

    public Usuario(String nome, String email, String senha, UserRole role) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
    }


    public void atualizarInformacoes(AtualizarUsuarioDTO dto) {

        this.active = dto.active();

        if (dto.senha() != null) {
            this.senha = dto.senha();
        }
        if (dto.nome() != null) {
            this.nome = dto.nome();
        }
        if (dto.telefone() != null) {
            this.telefone = dto.telefone();
        }
        if (dto.role() != null) {
            this.role = dto.role();
        }
        if (dto.cpf() != null) {
            this.cpf = dto.cpf();
        }
//        if (dto.endereco() != null) {
//            this.endereco.atualizarInformacoes(dto.endereco());
//        }
    }

    public void excluir() {
        this.active = false;
    }
    public void ativa() {
        this.active = true;
    }





}
