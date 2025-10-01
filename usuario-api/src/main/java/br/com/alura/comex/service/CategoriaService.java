package br.com.alura.comex.service;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.repository.CategoriaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public void cadastrar(Categoria categoria) {
        if (categoria == null) return;
        repository.save(categoria);
    }

    public List<Categoria> listarTodas() {
        return repository.findAll();
    }

    public Categoria getCategoriaByNome(String nome) {
        return repository.findByNome(nome);
    }

    public Categoria getCategoriaById(long id) {
        return repository.findById(id);
    }
}
