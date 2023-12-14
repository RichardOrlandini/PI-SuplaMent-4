package com.br.SuplaMent.domain.pessoa;

import com.br.SuplaMent.domain.endereco.Endereco;
import com.br.SuplaMent.domain.pessoa.dto.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import java.util.*;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos;

    public Cliente(CadastroDataCliente dto) {
        this.setNome(dto.client().nome());
        this.setEmail(dto.client().email());
        this.setSenha(dto.client().senha());
        this.setGenero(dto.client().genero());
        this.setRole(dto.client().role());
        this.setCpf(dto.client().cpf());
        this.setDataNascimento(dto.client().dataNascimento());
        this.setEnderecos(Arrays.asList(dto.enderecos()).stream()
                .map(enderecoDTO -> new Endereco(enderecoDTO))
                .collect(Collectors.toList()));
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
        //TODO ver se não tem mais metedos para colocar aqui
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
