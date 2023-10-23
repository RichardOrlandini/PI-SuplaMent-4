package com.br.SuplaMent.services;

import com.br.SuplaMent.domain.categoria.Categoria;
import com.br.SuplaMent.domain.categoria.CategoriaRepository;
import com.br.SuplaMent.domain.categoria.dto.CategoriaRequest;
import com.br.SuplaMent.domain.categoria.dto.CategoriaResponse;
import com.br.SuplaMent.infra.exception.ValidationExcepetion;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;


@Service
@AllArgsConstructor(onConstructor_ = {@Lazy})
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Lazy
    private final ProdutoService produtoService;

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

    public ResponseEntity delete(Long id) {
        this.validacaoIdInformado(id);
        if (produtoService.existsByCategoriaId(id)) {
            throw new ValidationExcepetion("You cannot delete this category because it's already defined by a product.");
        }
        categoriaRepository.deleteById(id);
        return ResponseEntity.ok("A categoria foi deletada.");
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
