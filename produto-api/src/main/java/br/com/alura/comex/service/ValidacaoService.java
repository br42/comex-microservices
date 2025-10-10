package br.com.alura.comex.service;

import org.springframework.stereotype.Service;

@Service
public class ValidacaoService {
    private final ValidacaoClient validacaoClient;
    
    public ValidacaoService(ValidacaoClient validacaoClient) {
        this.validacaoClient = validacaoClient;
    }
    
    public boolean validarToken(String token) {
        return validacaoClient.validarToken(token).getStatusCode().is2xxSuccessful();
    }
}
