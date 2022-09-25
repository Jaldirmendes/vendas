package com.example.vendas.rest.controllers;

import com.example.vendas.domain.models.Produto;
import com.example.vendas.domain.repositories.Produtos;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private Produtos produtosrepository;

    public ProdutoController(Produtos produtos) {
        this.produtosrepository = produtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody Produto produto) {
        return produtosrepository.save(produto);
    }


    @GetMapping("/{id}")
    public Produto getProdutoById ( @PathVariable Integer id ) {
        return produtosrepository.findById(id)
                .orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id) {
        produtosrepository.findById(id)
                .map( produto -> {
                    produtosrepository.delete(produto);
                    return produto;
                })
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrado"));
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer id,
                                  @RequestBody Produto produto ) {
         produtosrepository
                .findById(id)
                .map( produtoExistente -> {
                    produto.setId(produtoExistente.getId());
                    produtosrepository.save(produto);
                    return produto;
                }).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND,
                         "Produto não encontrado"));
    }

    @GetMapping()
    public List<Produto> find( Produto filtro ) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        return produtosrepository.findAll(example);
    }

}
