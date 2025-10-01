package br.com.alura.comex.service;

import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.repository.ClienteRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public void cadastrar(Cliente cliente) {
        if (cliente == null) return;
        repository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return repository.findAll();
    }
}
