package com.example.apirestvendas.controller;

import com.example.apirestvendas.model.entity.Produto;
import com.example.apirestvendas.repository.ProdutoRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produto")
@AllArgsConstructor // cria o construtor para o ProdutoRepository ou usa o Autowired na classe que for ser instaciada
public class ProdutorController {
    //@Autowired //passa para o Spring a obrigação de instanciar o objeto
    private final ProdutoRepository produtoRepository;

    /**
     * ResponseEntity significa "entidade de Resposat".
     * Entidade é um objeto. Mais isto nada mais é  do que
     * um tipo que indica uma resposta de uma API HTTP (REST)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable final Long id) {
        return produtoRepository.findById(id).map(ResponseEntity::ok)
                //map responseEntity::ok so chama o mpa se o produto foi localizado
                .orElseGet(() -> ResponseEntity.notFound().build()); //senão, executa o orelseGet
        //return produtoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/ean/{ean}")
    public Produto findByIdEAN(@PathVariable final String ean) {
        return produtoRepository.findByEan(ean).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{loja}/{ean}")
    public Produto findByEanAndLoja(@PathVariable final String ean, @PathVariable final String loja) {
        return produtoRepository.findByEanAndLoja(loja, ean).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping()
    public List<Produto> findByAll() {
        return produtoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Produto> insert(@Valid @RequestBody Produto produto) {
        produto = produtoRepository.save(produto);
        URI location = URI.create("/produto/"+produto.getId());
        return ResponseEntity.created(location).body(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable long id, @Valid @RequestBody Produto produto) {
        if (id == produto.getId()) {
            return ResponseEntity.accepted().body(produtoRepository.save(produto));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final long id) {
        produtoRepository.deleteById(id);
    }


}
