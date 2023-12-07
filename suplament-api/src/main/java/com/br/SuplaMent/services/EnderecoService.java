package com.br.SuplaMent.services;

import com.br.SuplaMent.domain.endereco.Endereco;
import com.br.SuplaMent.domain.endereco.EnderecoRepository;
import com.br.SuplaMent.domain.pessoa.dto.CadastroEnderecosDTO;
import com.br.SuplaMent.infra.exception.ValidationExcepetion;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor
public class EnderecoService {

    final EnderecoRepository enderecoRepository;
    final CepService cepService;

    public void save(List<CadastroEnderecosDTO> enderecosDTOS) {

        List<Endereco> newAndress = new ArrayList<>();
        boolean isPrincipalEndereco = false;

        for (CadastroEnderecosDTO endereco: enderecosDTOS) {
            boolean isCep = cepService.fazValidaCep(endereco.cep());
            if (!isCep) {
                throw new ValidationExcepetion("Endereço com o cep invalido: " + endereco.cep());
            }
            if (endereco.isPrincipal()) {
                if (isPrincipalEndereco) {
                    throw new ValidationExcepetion("Já existe um endereço principal!");
                }
                isPrincipalEndereco = true;
            }

            newAndress.add(Endereco.of(endereco));
        }
        if (!isPrincipalEndereco) {
            throw new ValidationExcepetion("Nenhum endereço principal foi definido!");
        }

        enderecoRepository.saveAll(newAndress);
    }


    public void setPrincipalAddress(Long addId) {
        List<Endereco> enderecos = enderecoRepository.findAll();
        for (Endereco endereco : enderecos) {
            if (Objects.equals(endereco.getId(), addId)) {
                endereco.setPrincipal(true);
            } else {
                endereco.setPrincipal(false);
            }
        }
        enderecoRepository.saveAll(enderecos);
    }


}
