package br.com.alura.comex.infra.exception;

import java.sql.SQLException;
import java.util.List;

import org.postgresql.util.PSQLException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({PSQLException.class, SQLException.class})
    public ResponseEntity<String> tratarErroSQL(SQLException ex) {
        ex.printStackTrace();
        return ResponseEntity.internalServerError().body(ex.toString());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
