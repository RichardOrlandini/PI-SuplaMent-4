package com.br.SuplaMent.domain.endereco;


import com.br.SuplaMent.domain.pessoa.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface EnderecoRepository  extends JpaRepository<Endereco, Long> {

    List<Endereco> findByClienteId(Long clienteId);
    Page<Endereco> findAllByActiveTrue(Pageable paginacao);
    Page<Endereco> findByNomeContaining(String logradouro, Pageable paginacao);
    Endereco save(Endereco adiciona);
}
