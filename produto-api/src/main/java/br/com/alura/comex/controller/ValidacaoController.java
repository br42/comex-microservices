package br.com.alura.comex.controller;

import br.com.alura.comex.service.ValidacaoService;
import java.security.InvalidKeyException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("validar")
public class ValidacaoController {

    @Autowired
    private ValidacaoService validacaoService;

    @PostMapping
    public ResponseEntity<Object> validarToken(@RequestBody String token){

        try {
            if (validacaoService.validarToken(token)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            
            throw new InvalidKeyException();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
