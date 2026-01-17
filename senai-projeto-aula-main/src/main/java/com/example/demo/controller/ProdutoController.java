package com.example.demo.controller;


import com.example.demo.model.Produto;
import com.example.demo.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<Produto> listarTodosProdutos() {
        return produtoService.listarTodos();
    }

   // @GetMapping("/{id}")
    /*public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Integer id) {
        return produtoService.buscarPorId(id)
               .map(produto -> ResponseEntity.ok(produto))
                .orElse(ResponseEntity.notFound().build());
    }*/
    @GetMapping("/{id}")
    public Produto getProduto(@PathVariable Integer id) {
        return produtoService.buscarPorIdOuFalhar(id);
    }

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        Produto produtoSalvo = produtoService.salvar(produto);
        return new ResponseEntity<>(produtoSalvo, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Integer id) {
        try {
            produtoService.deletar(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
/*
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Integer id, @RequestBody Produto produtoDetalhes) {
        return produtoService.buscarPorId(id)
                .map(produtoExistente -> {
                    produtoExistente.setNome(produtoDetalhes.getNome());
                    produtoExistente.setCategoria(produtoDetalhes.getCategoria());
                    produtoExistente.setValor(produtoDetalhes.getValor());
                    produtoExistente.setQuantidadeEstoque(produtoDetalhes.getQuantidadeEstoque());
                    Produto produtoAtualizado = produtoService.salvar(produtoExistente);
                    return ResponseEntity.ok(produtoAtualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
 */




    @PutMapping("/{id}")
    public Produto atualizarProdutoNovo(@PathVariable Integer id, @RequestBody Produto produtoDetalhes) {
        Produto produtoExistente = produtoService.buscarPorIdOuFalhar(id);
        produtoExistente.setNome(produtoDetalhes.getNome());
        produtoExistente.setCategoria(produtoDetalhes.getCategoria());
        produtoExistente.setValor(produtoDetalhes.getValor());
        produtoExistente.setQuantidadeEstoque(produtoDetalhes.getQuantidadeEstoque());
        return produtoService.salvar(produtoExistente);
    }
}
