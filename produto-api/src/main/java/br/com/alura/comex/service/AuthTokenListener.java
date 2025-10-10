package br.com.alura.comex.service;

import java.nio.charset.Charset;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenListener {
    @RabbitListener(queues = "api.token.info")
    public void recebeMensagem(Message mensagem) {
        //System.out.println("Recebida mensagem " + new String(mensagem.getBody()));
        System.out.println("Recebida mensagem " + new String(mensagem.getBody(), Charset.forName("UTF-8")));
    }
}
