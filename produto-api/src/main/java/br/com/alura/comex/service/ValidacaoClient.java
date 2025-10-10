package br.com.alura.comex.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("USUARIO-API")
public interface ValidacaoClient {
    //#@RequestMapping(method = RequestMethod.POST, value = "/login/validartokenfila")
    @PostMapping("/login/validartokenfila")
    ResponseEntity<Void> validarToken(@RequestBody String token);
}
