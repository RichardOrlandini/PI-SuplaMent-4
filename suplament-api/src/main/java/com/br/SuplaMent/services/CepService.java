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

    //"https://viacep.com.br/ws/" + cep + "/json"

    public static boolean fazValidaCep(String cep) {
        // Verifique se o CEP é válido
        if (!cep.matches("\\d{5}-\\d{3}")) {
            throw new IllegalArgumentException("CEP inválido: " + cep);
        }

        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromUriString(API_URL)
                .buildAndExpand(cep)
                .toUriString();
        try {
            restTemplate.getForObject(url, String.class);
            return true;
        } catch (Exception e) {
            System.out.println("Error validating CEP: " + e.getMessage());
            e.printStackTrace();

            return false;
        }
    }



    public static Endereco BuscaCepDetalhes(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromUriString(API_URL)
                .buildAndExpand(cep)
                .toUriString();
        try {
            Endereco endereco = restTemplate.getForObject(url, Endereco.class);
            return endereco;
        } catch (Exception e) {
            // trate a exceção
            return null;
        }
    }
}


