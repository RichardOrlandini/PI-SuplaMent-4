package com.br.SuplaMent.domain.Imagem;

import com.br.SuplaMent.domain.produto.Produto;
import com.br.SuplaMent.domain.produto.ProdutoServiceInter;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class ImagemService {
   // @Value("${upload.path}") // Caminho do diretório de upload definido no application.properties

    private String uploadPath;
    private  ProdutoServiceInter produtoService;

    public void uploadImage(MultipartFile file, Produto produto, boolean principal) {
        try {
            String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadPath).resolve(filename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            Imagem imagemProduto = new Imagem();
            imagemProduto.setImagePath(filename);
            imagemProduto.setProduto(produto);
            imagemProduto.setDefault(principal);


            if (principal) {
                produto.getImagens().forEach(image -> image.setDefault(false));
                imagemProduto.setDefault(true);
            }


            produto.getImagens().add(imagemProduto);
            produtoService.saveProduto(produto);
        } catch (IOException e) {

        }
    }
}
