package com.br.SuplaMent.services;

import com.br.SuplaMent.domain.endereco.Endereco;
import com.br.SuplaMent.domain.endereco.EnderecoRepository;
import com.br.SuplaMent.domain.pessoa.dto.CadastroEnderecosDTO;
import com.br.SuplaMent.infra.exception.ValidationExcepetion;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class EnderecoService {

    final EnderecoRepository enderecoRepository;
    final CepService cepService;

    public void save(List<CadastroEnderecosDTO> enderecosDTOS) {

        List<Endereco> newAndress = new ArrayList<>();

        for (CadastroEnderecosDTO endereco: enderecosDTOS) {
            boolean isCep = cepService.fazValidaCep(endereco.cep());
            if (!isCep) {
                throw new ValidationExcepetion("Endereço com o cep invalido: " + endereco.cep());
            }

            //TODO rodrigo, garantir que tenha apenas 1 endereço principal!
            newAndress.add(Endereco.of(endereco));
        }
        enderecoRepository.saveAll(newAndress);
    }
}
