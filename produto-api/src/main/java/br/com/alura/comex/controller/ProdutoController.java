package br.com.alura.comex.controller;

import br.com.alura.comex.dto.RequestProduto;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.service.CategoriaService;
import br.com.alura.comex.service.ProdutoService;
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
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Object> cadastra(@RequestBody @Valid RequestProduto request, BindingResult result){

        if(result.hasErrors()) {
            String mensagem = "";
            String[] campos = {"nome", "preco", "descricao", "quantidade"};
            
            for (String campo: campos) {
                FieldError fieldError = result.getFieldError(campo);
                if (fieldError != null) {
                    mensagem += "Erro: [produto." + campo + "] " + fieldError.getDefaultMessage() + "\n";
                }
            }
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }

        //Categoria categoria = categoriaService.getCategoriaByNome(request.getCategoria());
        Categoria categoria = categoriaService.getCategoriaById(request.getCategoria());
        Produto produto = request.toProduto(categoria);
        produtoService.cadastrar(produto);

        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> listar(){
        List<Produto> produtos = produtoService.listarTodos(); //.toArray(new Produto[]{});

        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }
}
