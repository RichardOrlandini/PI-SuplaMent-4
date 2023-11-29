package com.br.SuplaMent.services;

import com.br.SuplaMent.domain.endereco.Endereco;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@AllArgsConstructor
public class CepService {
    private static final String API_URL = "https://viacep.com.br/ws/{cep}/json";

    public boolean fazValidaCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromUriString(API_URL)
                .buildAndExpand(cep)
                .toUriString();

        try {
            restTemplate.getForObject(url, String.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void validarDadosEndereco(Endereco endereco) {
        Boolean cepDetalhes = fazValidaCep(endereco.getCep());
//        endereco.setLogradouro(cepDetalhes.getLogradouro());
//        endereco.setBairro(cepDetalhes.getBairro());
//        endereco.setCidade(cepDetalhes.getCidade());
//        endereco.setUf(cepDetalhes.getUf());
    }
//    private Endereco BuscaCepDetalhes(String cep) {
//        // colocar a validaçao do cep usando uma api externa
//        return  null;
//
//        //dfds
//    }
}
