package com.br.SuplaMent.services;

import com.br.SuplaMent.domain.categoria.Categoria;
import com.br.SuplaMent.domain.categoria.CategoriaRepository;
import com.br.SuplaMent.domain.categoria.dto.CategoriaRequest;
import com.br.SuplaMent.domain.categoria.dto.CategoriaResponse;
import com.br.SuplaMent.domain.fornecedor.Fornecedor;
import com.br.SuplaMent.domain.fornecedor.dto.FornecedorCreateDTO;
import com.br.SuplaMent.domain.fornecedor.dto.FornecedorResponseDTO;
import com.br.SuplaMent.infra.exception.ValidationExcepetion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;


@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public CategoriaResponse findByIdResponse(Long id) {
        return CategoriaResponse.of(this.findById(id));
    }

    public Page findAll(Pageable pageable) {
        return  categoriaRepository.findAll(pageable).map(CategoriaResponse::of);
    }

    public CategoriaResponse update(CategoriaRequest request, Long id) {
        this.validacaoCategoriaNomeInformado(request);
        this.validacaoIdInformado(id);
        var categoria = Categoria.of(request);
        categoria.setId(id);
        categoriaRepository.save(categoria); // quando o id ja existia ele sabe que é uma atualização
        return CategoriaResponse.of(categoria);
    }
    public Page findByDescricao(Pageable pageable, String descricao) {
        if (isEmpty(descricao)) {
            throw new ValidationExcepetion("A descrição da categoria não foi informada");
        }
        return  categoriaRepository
                .findByDescricaoIgnoreCaseContaining(pageable, descricao)
                .map(CategoriaResponse::of);
    }
    public Categoria findById(Long id) {
        this.validacaoIdInformado(id);
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ValidationExcepetion("Nenhuma Categoria encontrada com o id informado!"));
    }

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

    private void validacaoIdInformado(Long id) {
        if (isEmpty(id)){
            throw new ValidationExcepetion("O id da categoria precisa ser informado");
        }
    }
}
