package com.br.SuplaMent.services;

import com.br.SuplaMent.domain.fornecedor.Fornecedor;
import com.br.SuplaMent.domain.fornecedor.FornecedorRepository;
import com.br.SuplaMent.domain.fornecedor.dto.FornecedorCreateDTO;
import com.br.SuplaMent.domain.fornecedor.dto.FornecedorResponseDTO;
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
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    @Lazy
    private final ProdutoService produtoService;

    public Page findAll(Pageable pageable) {
        return  fornecedorRepository.findAll(pageable).map(FornecedorResponseDTO::of);
    }

    public FornecedorResponseDTO update(FornecedorCreateDTO request, Long id) {
        this.validaFornecedorComNomeInformado(request);
        var fornecedor = Fornecedor.of(request);
        fornecedor.setId(id);
        fornecedorRepository.save(fornecedor);
        return FornecedorResponseDTO.of(fornecedor);
    }
    public Page findByNome(Pageable pageable, String nome) {
        if (isEmpty(nome)) {
            throw new ValidationExcepetion("O nome do fornecedor não foi informada");
        }
        return  fornecedorRepository
                .findByNomeIgnoreCaseContaining(pageable, nome)
                .map(FornecedorResponseDTO::of);
    }

    public FornecedorResponseDTO findByIdResponse(Long id) {
        return FornecedorResponseDTO.of(this.findById(id));
    }
    public Fornecedor findById(Long id) {

        if (isEmpty(id)) {
            throw new ValidationExcepetion("O id do fornecedor não pode ser nullo");
        }

        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new ValidationExcepetion("Nenhum fornecedor encontrado com o id informado!"));
    }

    public FornecedorResponseDTO save(FornecedorCreateDTO request) {
        this.validaFornecedorComNomeInformado(request);
        var fornecedor = fornecedorRepository.save(Fornecedor.of(request));
        return FornecedorResponseDTO.of(fornecedor);
    }

    private void validaFornecedorComNomeInformado(FornecedorCreateDTO request) {
        if(isEmpty(request.getNome())) {
            throw new ValidationExcepetion("O nome do fornecedor precisa ser informador");
        }
    }

    private void validaIdInformado(Long id) {
        if (isEmpty(id)) {
            throw new ValidationExcepetion("O fornecedor id não foi informado.");
        }
    }

    public ResponseEntity delete(Long id) {
        this.validaIdInformado(id);
        if (produtoService.existsByFornecedorId(id)) {
            throw new ValidationExcepetion("You cannot delete this supplier because it's already defined by a product.");
        }
        fornecedorRepository.deleteById(id);
        return ResponseEntity.ok("O fornecedor foi apagado.");
    }
}
