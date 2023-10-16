package com.br.SuplaMent.services;

import com.br.SuplaMent.domain.categoria.Categoria;
import com.br.SuplaMent.domain.categoria.dto.CategoriaRequest;
import com.br.SuplaMent.domain.categoria.dto.CategoriaResponse;
import com.br.SuplaMent.domain.fornecedor.Fornecedor;
import com.br.SuplaMent.domain.produto.Produto;
import com.br.SuplaMent.domain.produto.ProdutoRepository;
import com.br.SuplaMent.domain.produto.dto.ListagemProdutoDTO;
import com.br.SuplaMent.domain.produto.dto.ProdutoCreateToSalesDTO;
import com.br.SuplaMent.domain.produto.dto.ProdutoResponseToSalesDTO;
import com.br.SuplaMent.infra.exception.ValidationExcepetion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;


@Service
public class ProdutoService {

    private static final Integer ZERO = 0;

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private FornecedorService fornecedorService;

    public Page findByCategorias(Pageable pageable, Long categoriaId) {
        if (isEmpty(categoriaId)) {
            throw new ValidationExcepetion("A categoria id não pode ser vazio");
        }
        return produtoRepository.findByCategoriaId(pageable, categoriaId).map(ListagemProdutoDTO::new);
    }
    public Page findByFornecedores(Pageable pageable, Long fornecedorId) {
        if (isEmpty(fornecedorId)) {
            throw new ValidationExcepetion("O fornecedor id não pode ser vazio");
        }
        return produtoRepository.findByFornecedorId(pageable, fornecedorId).map(ListagemProdutoDTO::new);
    }
    public ProdutoResponseToSalesDTO findByIdResponse(Long id) {
        this.validaIdInformado(id);
        return ProdutoResponseToSalesDTO.of(findById(id));
    }
    public Page findByNome(Pageable pageable,String  nome) {
        if (isEmpty(nome)) {
            throw new ValidationExcepetion("O nome do produto precisa ser informado!");
        }
        return produtoRepository.findByNomeIgnoreCaseContaining(pageable, nome).map(ListagemProdutoDTO::new);
    }

    public Page<ListagemProdutoDTO> findAll(Pageable pageable) {
        return produtoRepository.findAll(pageable).map(ListagemProdutoDTO::new);
    }

    public Produto findById(Long id) {

        if (isEmpty(id)) {
            throw new ValidationExcepetion("O id do produto não pode ser nullo");
        }

        return produtoRepository.findById(id)
                .orElseThrow(() -> new ValidationExcepetion("Nenhum Produto encontrado com o id informado!"));
    }
    public ProdutoResponseToSalesDTO save(ProdutoCreateToSalesDTO req) {
        this.validacaoProdutoDataInformado(req);
        this.validacaoCategoriaEFornecedorInformados(req);

        var categoria = categoriaService.findById(req.getCategoriaId());
        var fornecedor = fornecedorService.findById(req.getFornecedorId());

        var produto = produtoRepository.save(Produto.of(req, fornecedor, categoria));
        return ProdutoResponseToSalesDTO.of(produto);
    }

    public ProdutoResponseToSalesDTO update(ProdutoCreateToSalesDTO req, Long id) {
        this.validacaoProdutoDataInformado(req);
        this.validaIdInformado(id);
        this.validacaoCategoriaEFornecedorInformados(req);

        var categoria = categoriaService.findById(req.getCategoriaId());
        var fornecedor = fornecedorService.findById(req.getFornecedorId());
        var produto = Produto.of(req, fornecedor, categoria);
        produto.setId(id);
        produtoRepository.save(produto);
        return ProdutoResponseToSalesDTO.of(produto);
    }

    private void validacaoProdutoDataInformado(ProdutoCreateToSalesDTO request) {
        if (isEmpty(request.getNome())){
            throw new ValidationExcepetion("O nome do produto não foi informada!");
        }
        if (isEmpty(request.getQtd())){
            throw new ValidationExcepetion("A quantidade do produto não foi informada!");
        }
        if (request.getQtd() <= ZERO){
            throw new ValidationExcepetion("A quantidade do produto não pode ser 0!");
        }
    }

    private void validacaoCategoriaEFornecedorInformados(ProdutoCreateToSalesDTO request) {
        if (isEmpty(request.getCategoriaId())){
            throw new ValidationExcepetion("A categoria id, não foi informada!");
        }
        if (isEmpty(request.getFornecedorId())){
            throw new ValidationExcepetion("O Fornecedor id, não foi informado!");
        }
    }

    private void validaIdInformado(Long id) {
        if (isEmpty(id)){
            throw new ValidationExcepetion("O id informado não pode ser vazio!");
        }
    }

}
