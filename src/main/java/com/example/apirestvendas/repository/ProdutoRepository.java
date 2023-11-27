package com.example.apirestvendas.repository;

import com.example.apirestvendas.model.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findByEan (String ean);

    Optional<Produto> findByEanAndLoja (String loja, String ean );

}
