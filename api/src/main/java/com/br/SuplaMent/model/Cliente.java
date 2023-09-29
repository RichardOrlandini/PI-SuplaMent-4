package com.br.SuplaMent.model;

import com.br.SuplaMent.model.dto.cliente.AtualizarClienteDTO;
import com.br.SuplaMent.model.dto.cliente.CadastroClienteDTO;
import com.br.SuplaMent.utils.UserRole;
import jakarta.persistence.*;
import lombok.*;

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
        this.role = UserRole.valueOf("CLIENTE");

    }

    public Cliente(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;

    }


    public void atualizarInformacoesCliente(AtualizarClienteDTO dto) {

        this.active = dto.active();

        if (dto.senha() != null) {
            this.senha = dto.senha();
        }
        if (dto.nome() != null) {
            this.nome = dto.nome();
        }

    }


    public void excluir() {
        this.active = false;
    }
    public void ativa() {
        this.active = true;
    }









}
