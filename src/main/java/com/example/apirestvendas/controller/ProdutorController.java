package com.example.apirestvendas.controller;

import com.example.apirestvendas.model.entity.Produto;
import com.example.apirestvendas.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/produto")
@AllArgsConstructor // cria o construtor para o ProdutoRepository ou usa o Autowired na classe que for ser instaciada
public class ProdutorController {
    //@Autowired //passa para o Spring a obrigação de instanciar o objeto
    private final ProdutoRepository produtoRepository;

    @GetMapping("/{id}")
    public Produto findById(@PathVariable final Long id) {
        return produtoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
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
    public long insert(@RequestBody Produto produto){
        return produtoRepository.save(produto).getId();

    }

    @PutMapping("/{id}")
    public void update (@PathVariable long id, @RequestBody Produto produto){
        System.out.println(produto);
        produtoRepository.save(produto);
    }

    @DeleteMapping("/{id}")
    public void  delete(@PathVariable final long id){
        produtoRepository.deleteById(id);
    }


}
