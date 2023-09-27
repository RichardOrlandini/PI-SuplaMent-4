package com.br.SuplaMent.domain.cliente;

import com.br.SuplaMent.domain.cliente.dto.AtualizarClienteDTO;
import com.br.SuplaMent.domain.cliente.dto.CadastroClienteDTO;
import com.br.SuplaMent.domain.usuario.UserRole;
import com.br.SuplaMent.domain.usuario.dto.AtualizarUsuarioDTO;
import com.br.SuplaMent.domain.usuario.dto.CadastroUsuarioDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity(name = "Cliente")
@Table(name = "cliente")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean active;
    private String nome;
    private String email;
    private String senha;
    private UserRole role;



    public Cliente(CadastroClienteDTO dto) {
        this.active = true;
        this.nome = dto.nome();
        this.email = dto.email();
        this.senha = dto.senha();
        this.role = dto.role();

    }

    public Cliente(String nome, String email, String senha, UserRole role) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;

    }


    public void atualizarInformacoesCliente(AtualizarClienteDTO dto) {

        this.active = dto.active();

        if (dto.senha() != null) {
            this.senha = dto.senha();
        }
        if (dto.nome() != null) {
            this.nome = dto.nome();
        }
        if (dto.role() != null) {
            this.role = dto.role();
        }
    }


    public void excluir() {
        this.active = false;
    }
    public void ativa() {
        this.active = true;
    }









}
