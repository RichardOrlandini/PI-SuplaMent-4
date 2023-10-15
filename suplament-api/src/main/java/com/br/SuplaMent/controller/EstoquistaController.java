//package com.br.SuplaMent.controller;
//
//import ch.qos.logback.core.model.Model;
//import com.br.SuplaMent.domain.produto.Produto;
//import com.br.SuplaMent.domain.produto.ProdutoRepository;
//import com.br.SuplaMent.services.ProdutoService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//    @Controller
//    @RequestMapping("/estoquista")
//    public class EstoquistaController {
//        @Autowired
//        private ProdutoRepository produtoRepository;
//
//        /*
//        @GetMapping("/listar") // fazedno a lista de todos os produtos quando logar como estoquista
//        public List<Produto> listarProdutos() {
//            List<Produto> produto = produtoRepository.findAllByOrderByDataInsercaoDesc();
//            return produto;
//        }
//        */
//        @GetMapping("/listar") // melhorando a listagem
//        public ResponseEntity<List<Produto>> listarProdutos(@RequestParam(defaultValue = "0") int page) {
//            Pageable pageable = PageRequest.of(page, 10, Sort.by("dataInicial").descending());
//            Page<Produto> produtosPage = produtoRepository.findAll(pageable);
//            List<Produto> produto = produtosPage.getContent();
//            return ResponseEntity.ok(produto);
//        }
//
//        /*
//        @GetMapping("/buscar")// busca do estoquista
//        public List<Produto> buscarProdutos(@RequestParam("nome") String nome) {
//            List<Produto> produto = produtoRepository.findByNomeContainingIgnoreCaseOrderByDataInsercaoDesc(nome);
//            return produto;
//        } */
//        @GetMapping("/buscar")
//        public ResponseEntity<List<Produto>> buscarProdutos(@RequestParam("nome") String nome) {
//            List<Produto> produto = produtoRepository.findByNomeContainingIgnoreCase(nome);
//            return ResponseEntity.ok(produto);
//        }
//
//
//        @Autowired
//        private ProdutoService productService;
//
//        @GetMapping("/edit/{id}")
//        public String showEditProductForm(@PathVariable("id") Long id, Model model) {
//            Produto product = productService.getProductById(id);
//           // model.addAttribute("product", product);
//            return "editProduct";
//        }
//
//        @PostMapping("/edit/{id}")
//        public String updateProductQuantity(
//                @PathVariable("id") Long id,
//                @RequestParam("quantity") int newQuantity) {
//            productService.updateProductQuantity(id, newQuantity);
//            return "redirect:/products/edit/" + id;
//        }
//
//    }
//
//
