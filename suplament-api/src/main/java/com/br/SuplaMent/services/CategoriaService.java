package com.br.SuplaMent.services;

import com.br.SuplaMent.domain.categoria.Categoria;
import com.br.SuplaMent.domain.categoria.CategoriaRepository;
import com.br.SuplaMent.dto.CategoriaRequest;
import com.br.SuplaMent.dto.CategoriaResponse;
import com.br.SuplaMent.infra.exception.ValidationExcepetion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;


@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public CategoriaResponse save(CategoriaRequest req) {
        this.validacaoCategoriaNomeInformado(req);
        var categoria = categoriaRepository.save(Categoria.of(req));
        return CategoriaResponse.of(categoria);
    }

    private void validacaoCategoriaNomeInformado(CategoriaRequest request) {
        if (isEmpty(request.getDescricao())){
            throw new ValidationExcepetion("A descrição da categoria não foi informada!");
        }
    }
}
