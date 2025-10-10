package br.com.alura.comex.infra.amqp;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioApiAMQPConfiguration {
    @Bean
    Queue criarFila () {
        //return new Queue("api.token.info", false);
        return QueueBuilder.nonDurable("api.token.info").build();
    }
    
    @Bean
    RabbitAdmin criaRabbitAdmin(ConnectionFactory conn) {
        return new RabbitAdmin(conn);
    }
    
    @Bean
    ApplicationListener<ApplicationReadyEvent> inicializaAdmin(RabbitAdmin rabbitAdmin) {
        return (event) -> rabbitAdmin.initialize();
    }
}
