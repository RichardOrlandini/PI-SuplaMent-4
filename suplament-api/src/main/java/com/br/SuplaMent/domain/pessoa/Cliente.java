package com.br.SuplaMent.domain.pessoa;

import com.br.SuplaMent.domain.endereco.Endereco;
import com.br.SuplaMent.domain.pessoa.dto.AtualizarClienteDTO;
import com.br.SuplaMent.domain.pessoa.dto.AtualizarUsuarioDTO;
import com.br.SuplaMent.domain.pessoa.dto.CadastroClienteDTO;
import com.br.SuplaMent.domain.pessoa.dto.CadastroUsuarioDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "Cliente")
@Table(name = "cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends Pessoa {

    private UserRole role;
    private String genero;
    private String cpf;
    private Date dataNascimento;

    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos;
    public Cliente(CadastroClienteDTO dto) {
        this.setNome(dto.nome());
        this.setEmail(dto.email());
        this.setSenha(dto.senha());
        this.setGenero(dto.genero());
        this.setDataNascimento(dto.dataNascimento());
       //this.setEndereco(dto.endereco());

    }

    public void atualizarInformacoesCliente(AtualizarClienteDTO dto) {
        if (dto.senha() != null) {
            this.setSenha(dto.senha());
        }
        if (dto.nome() != null) {
            this.setNome(dto.nome());
        }
        if (dto.genero() != null) {
            this.setGenero(dto.genero());
        }
        if (dto.dataNascimento() != null) {
            this.setDataNascimento(dto.dataNascimento());
        }

    }

//    public boolean isNomeValido() {  // ver como fazer ja q ta passando o parametro nome por pessoa
//        if (nome == null) {
//            return false;
//        }
//
//        // Divida o nome em palavras
//        String[] palavras = nome.split(" ");
//
//        // Verifique se existem duas palavras e se cada uma tem pelo menos 3 letras
//        if (palavras.length == 2) {
//            for (String palavra : palavras) {
//                if (palavra.length() < 3) {
//                    return false;
//                }
//            }
//            return true;
//        }
//
//        return false;
//    }
    public void excluir() {
        this.setActive(false);
    }
    public void ativa() {
        this.setActive(true);
    }
}
