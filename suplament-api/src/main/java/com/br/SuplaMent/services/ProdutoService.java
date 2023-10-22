package com.br.SuplaMent.services;

    import com.br.SuplaMent.domain.produto.Produto;
import com.br.SuplaMent.domain.produto.ProdutoRepository;
import com.br.SuplaMent.domain.produto.dto.*;
import com.br.SuplaMent.domain.venda.client.SalesClient;
import com.br.SuplaMent.domain.venda.dto.SalesConfirmationDTO;
import com.br.SuplaMent.domain.venda.enums.SalesStatus;
import com.br.SuplaMent.domain.venda.rabiitmq.SalesConfirmadaSender;
import com.br.SuplaMent.infra.exception.ValidationExcepetion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
@Service
public class ProdutoService {

    private static final Integer ZERO = 0;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private FornecedorService fornecedorService;
    @Autowired
    private SalesConfirmadaSender salesConfirmadaSender;

    private SalesClient salesClient;

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

    public ProdutoSalesResponse findProductsSales(Long id) {
        var produto = this.findById(id);
        try {
            var sales = salesClient
                    .findSalesByProductId(produto.getId())
                    .orElseThrow(
                            () -> new ValidationExcepetion("A venda não tem existe com o produto informado"));

            return ProdutoSalesResponse.of(produto, sales.getSalesId());
        } catch (Exception e) {
            throw new ValidationExcepetion("Ocorreu um erro ao tentar recuperar as vendas do produto");
        }
    }

    public ResponseEntity checkProdutosStoque(ProdutoCheckStoqueRequest request) {
        if (isEmpty(request) || isEmpty(request.getProdutos())){
            throw new ValidationExcepetion("Nenhuma data informada na request.");
        }
        request
                .getProdutos()
                .forEach(this::validateStoque);
        return ResponseEntity.ok("O estoque está ok");
    }

    private void validateStoque(ProdutoQuantidadeDTO p) {
        if (isEmpty(p.getProdutoId()) || isEmpty(p.getQtd())) {
            throw new ValidationExcepetion("Produto id ou quantidade não informados");
        }
        var produto = findById(p.getProdutoId());
        if (p.getQtd() > produto.getQtd()) {
            throw new ValidationExcepetion(String.format("O produto %s is out of stock", produto.getQtd()));
        }
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

    public void updateProdutoStoque(ProdutoStoqueDTO dto) {
        try {
            this.validaStoqueDadosAtualizada(dto);
            this.atualizaStock(dto);
        } catch (Exception ex) {
            log.error("Erro na tentativa de atualizar o stoque do produto, erro {}", ex.getMessage(), ex);
            var rejectedMessage = new SalesConfirmationDTO(dto.getSalesId(), SalesStatus.REJEITADA);
            salesConfirmadaSender.sendSalesConfirmationMessage(rejectedMessage);
        }
    }

    private void atualizaStock(ProdutoStoqueDTO dto) {

        var produtosParaAtualizarStoque = new ArrayList<Produto>();

        dto.getProdutos()
                .forEach(vendaProduto -> {
                    var existeProduto = findById(vendaProduto.getProdutoId());
                    this.validaQtdNoStoque(vendaProduto, existeProduto);

                    existeProduto.updateStock(vendaProduto.getQtd());
                    produtosParaAtualizarStoque.add(existeProduto);


                });

        if (!isEmpty(produtosParaAtualizarStoque)) {
            produtoRepository.saveAll(produtosParaAtualizarStoque);
            var mensagemAprovada = new SalesConfirmationDTO(dto.getSalesId(), SalesStatus.APROVADA);
            salesConfirmadaSender.sendSalesConfirmationMessage(mensagemAprovada);
        }
    }
    @Transactional
    private void validaStoqueDadosAtualizada(ProdutoStoqueDTO dto) {

        if (isEmpty(dto) || isEmpty(dto.getSalesId())) {
            throw new ValidationExcepetion("O produto e a venda id não pode ser vazia");
        }
        if (isEmpty(dto.getProdutos())) {
            throw new ValidationExcepetion("Nenhum produto cadastrado na venda");
        }

        dto.getProdutos()
                .forEach(vendaProduto -> {
                    if (isEmpty(vendaProduto.getQtd()) || isEmpty(vendaProduto.getProdutoId())) {
                        throw new ValidationExcepetion("O produto id e a quantidade não foi informada");
                    }
                });

    }

    private void validaQtdNoStoque(ProdutoQuantidadeDTO dto, Produto exisetProduto) {
        if (dto.getQtd() > exisetProduto.getQtd()) {
            throw new ValidationExcepetion(
                    String.format("O produto %s está fora de estoque.", exisetProduto.getId()));
        }
    }

}
