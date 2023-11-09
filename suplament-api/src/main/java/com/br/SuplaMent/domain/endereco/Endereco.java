package com.br.SuplaMent.domain.endereco;

import com.br.SuplaMent.domain.endereco.dto.CadastroEnderecoDTO;
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
<<<<<<< Updated upstream:api/src/main/java/com/br/SuplaMent/domain/endereco/Endereco.java

=======
    private String cidade;
    @Size(min = 2, max = 2)
    private String uf;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
//dfds
>>>>>>> Stashed changes:suplament-api/src/main/java/com/br/SuplaMent/domain/endereco/Endereco.java
    public void atualizarInformacoes(CadastroEnderecoDTO dto) {
        if (dto.complemento() != null) {
            this.complemento = dto.complemento();
        }
        if (dto.numero() != null) {
            this.numero = dto.numero();
        }
        if (dto.logradouro() != null) {
            this.logradouro = dto.logradouro();
        }
        if (dto.bairro() != null) {
            this.bairro = dto.bairro();
        }
        if (dto.cep() != null) {
            this.cep = dto.cep();
        }
    }
}
