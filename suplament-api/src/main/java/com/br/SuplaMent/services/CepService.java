package com.br.SuplaMent.services;

import com.br.SuplaMent.domain.endereco.Endereco;
import com.br.SuplaMent.domain.pessoa.Cliente;
import com.br.SuplaMent.domain.pessoa.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
@AllArgsConstructor
public class CepService {
    private static final String VIA_CEP_URL = "https://viacep.com.br/ws/%s/json";

    public void validarCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(VIA_CEP_URL, cep);
        restTemplate.getForObject(url, Object.class);
    }
    private void validarDadosEndereco(Endereco endereco) {
        Endereco cepDetalhes = BuscaCepDetalhes(endereco.getCep());
        endereco.setLogradouro(cepDetalhes.getLogradouro());
        endereco.setBairro(cepDetalhes.getBairro());
        endereco.setCidade(cepDetalhes.getCidade());
        endereco.setUf(cepDetalhes.getUf());
    }
    private Endereco BuscaCepDetalhes(String cep) {
        // colocar a validaçao do cep usando uma api externa
        return  null;

        //dfds
    }
}
