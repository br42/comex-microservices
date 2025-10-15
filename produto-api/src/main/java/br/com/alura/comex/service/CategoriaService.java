package br.com.alura.comex.service;

import br.com.alura.comex.infra.kafka.KafkaProperties;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.repository.CategoriaRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public void cadastrar(Categoria categoria) {
        if (categoria == null) return;
        
        if (repository.exists(Example.of(categoria)) || repository.findByNome(categoria.getNome()) != null) {
            throw new DataIntegrityViolationException("Categoria a ser criada já existe com o mesmo nome: '" + 
                Optional.ofNullable(categoria.getNome()).orElse("(null)") +
            "'");
        } else {
            repository.save(categoria);
            
            KafkaProducer<String, String> producer = null;
            try {
                producer = new KafkaProducer<String, String>(KafkaProperties.getProperties());
                ObjectMapper jsonMapper = new ObjectMapper();
                //CategoriaNomePayload categoriaNome = new CategoriaNomePayload(categoria.getNome());
                Map<String, Object> categoriaNome = new HashMap<String, Object>();
                categoriaNome.put("categoria", new HashMap<String, String>(Map.of("nome", categoria.getNome())));
                String value = jsonMapper.writeValueAsString(categoriaNome);
                ProducerRecord<String, String> record = new ProducerRecord<String, String>("comex-categorias", value);
                producer.send(record);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (producer != null) {
                    producer.close();
                }
            }
        }
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
