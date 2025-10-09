package br.com.alura.comex.controller;

import br.com.alura.comex.dto.RequestCategoria;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.service.CategoriaService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("categorias")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Object> cadastra(@RequestBody @Valid RequestCategoria request, BindingResult result){

        if(result.hasErrors()) {
            FieldError fieldError = result.getFieldError("nome");
            String mensagem = (fieldError != null) ? "Erro: [categoria.nome] " + fieldError.getDefaultMessage() : "Erro: No campo nome, getFieldError(\"nome\") retornou null";
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }

        Categoria categoria = request.toCategoria();
        categoriaService.cadastrar(categoria);

        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> listar(){
        List<Categoria> categorias = categoriaService.listarTodas(); //.toArray(new Categoria[]{});

        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }
}
