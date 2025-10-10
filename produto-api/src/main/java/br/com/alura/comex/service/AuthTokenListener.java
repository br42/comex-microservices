package br.com.alura.comex.service;

import java.nio.charset.Charset;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alura.comex.dto.DadosValidarToken;
import br.com.alura.comex.model.TokenValidado;
import br.com.alura.comex.repository.TokenValidadoRepository;

@Service
public class AuthTokenListener {
    @Autowired
    TokenValidadoRepository repository;
    
    @RabbitListener(queues = "api.token.info")
    public void recebeMensagem(Message mensagem) {
        //System.out.println("Recebida mensagem " + new String(mensagem.getBody()));
        //System.out.println("Recebida mensagem " + new String(mensagem.getBody(), Charset.forName("UTF-8")));
        String mensagemRecebida = new String(mensagem.getBody(), Charset.forName("UTF-8"));
        try {
            ObjectMapper jsonMapper = new ObjectMapper();
            DadosValidarToken dadosToken = jsonMapper.readValue(mensagemRecebida, DadosValidarToken.class);
            repository.save(TokenValidado.fromDadosValidarToken(dadosToken));
            //System.out.printf("Dados do token recebidos: { nome=\"%s\"; ativo=\"%s\"; tipo=\"%s\"; token=\"%s\"; }.\n",
            //    dadosToken.nome(), Boolean.toString(dadosToken.ativo()), dadosToken.tipo().toString(), dadosToken.token()
            //);
        } catch (DataIntegrityViolationException e) {
            return;
        } catch (Exception e) {
            System.err.println("Erro ao processar a mensagem: \"" + mensagemRecebida + "\"");
            e.printStackTrace();
        }
    }
}
