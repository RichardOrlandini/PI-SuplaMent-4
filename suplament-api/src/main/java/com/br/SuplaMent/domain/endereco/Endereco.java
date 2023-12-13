package com.br.SuplaMent.domain.endereco;

import com.br.SuplaMent.domain.endereco.dto.CadastroEnderecoDTO;
import com.br.SuplaMent.domain.pessoa.Cliente;
import com.br.SuplaMent.domain.pessoa.dto.CadastroEnderecosDTO;
import com.br.SuplaMent.utils.aEntity.DomainEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity(name = "Endereco")
@Table(name = "endereco")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Endereco extends DomainEntity {


    private String complemento;
    private String numero;
    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    @Size(min = 2, max = 2)
    private String uf;
    private boolean principal;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;



    public static Endereco of (CadastroEnderecosDTO dto) {
        return Endereco
                .builder()
                .complemento(dto.complemento())
                .numero(dto.numero())
                .logradouro(dto.logradouro())
                .bairro(dto.bairro())
                .cep(dto.cep())
                .cidade(dto.cidade())
                .uf(dto.uf())
                .principal(dto.isPrincipal())
                .build();

    }
    public Endereco(CadastroEnderecosDTO dtoEndereco) {
        this.complemento = dtoEndereco.getComplemento();
        this.numero = dtoEndereco.getNumero();
        this.logradouro = dtoEndereco.getLogradouro();
        this.bairro = dtoEndereco.getBairro();
        this.cep = dtoEndereco.getCep();
        this.cidade = dtoEndereco.getCidade();
        this.uf = dtoEndereco.getUf();
       // this.clienteId = dtoEndereco.getClienteId();
    }


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
        if (dto.cidade() != null) {
            this.cidade = dto.cidade();
        }
        if (dto.uf() != null) {
            this.uf = dto.uf();
        }
    }
    public void excluir() {
        this.setActive(false);
    }

    private void setActive(boolean b) {
    }

    public void ativa() {
        this.setActive(true);
    }


}
