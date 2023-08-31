package com.br.SuplaMent.domain.endereco;

import com.br.SuplaMent.domain.endereco.dto.DtoEndereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Endereco")
@Table(name = "endereco")
@NoArgsConstructor
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String complemento;
    private String numero;
    private String logradouro;
    private String bairro;
    private String cep;

    public void atualizarInformacoes(DtoEndereco endereco) {
    }
}
