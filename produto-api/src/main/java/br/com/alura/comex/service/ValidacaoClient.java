package br.com.alura.comex.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("USUARIO-API")
public interface ValidacaoClient {
    //#@RequestMapping(method = RequestMethod.POST, value = "/login/validar")
    @PostMapping("/login/validar")
    boolean validarToken(String token);
}
