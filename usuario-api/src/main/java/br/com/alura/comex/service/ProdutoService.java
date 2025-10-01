package br.com.alura.comex.service;

import br.com.alura.comex.model.Produto;
import br.com.alura.comex.repository.ProdutoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public void cadastrar(Produto produto) {
        if (produto == null) return;
        repository.save(produto);
    }

    public List<Produto> listarTodos() {
        return repository.findAll();
    }
}
