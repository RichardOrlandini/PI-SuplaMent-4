package com.br.SuplaMent.domain.pessoa;

import com.br.SuplaMent.domain.endereco.Endereco;
import com.br.SuplaMent.domain.pessoa.dto.AtualizarUsuarioDTO;
import com.br.SuplaMent.domain.pessoa.dto.CadastroUsuarioDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "Cliente")
@Table(name = "cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends Pessoa {

//gfd
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Endereco> enderecos = new ArrayList<>();

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
