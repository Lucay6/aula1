package com.example.demo.service;

import com.example.demo.exception.ProdutoNaoEncontradoException;
import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarTodos(){
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Integer id){

        return produtoRepository.findById(id);//.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com o ID: " + id));
    }

    public Produto salvar(Produto produto){
        return produtoRepository.save(produto);
    }

    public Produto buscarPorIdOuFalhar(Integer id) {
        if (id <= 0){
            throw new ProdutoNaoEncontradoException("Produto com ID inválido" +id + " (ID deve ser maior que zero");
        }
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com o ID: " + id));
    }

    public void deletar(Integer id) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado!");
        }
        produtoRepository.deleteById(id);
    }
}
