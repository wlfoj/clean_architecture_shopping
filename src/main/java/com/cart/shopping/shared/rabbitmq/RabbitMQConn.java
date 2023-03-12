package com.cart.shopping.shared.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class RabbitMQConn {
    private final String exchangeName = "amq.direct";
    private AmqpAdmin amqpAdmin;

    public RabbitMQConn(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }
    //@Bean
    public Queue queue(String QueueName){
        return new Queue(QueueName, false);
    }

    private DirectExchange exchange(String exchangeName){
        return new DirectExchange(exchangeName);
    }

    private Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queue.getName());
    }

    // Anotaçao introduzida para executar o método após instânciar a classe
    @PostConstruct
    private void setup(){
        // Inicializando as filas, exchanges e relacionamentos
        Queue queueWelcome = this.queue(QueueNames.WELCOME.name());
        DirectExchange exchange = this.exchange(this.exchangeName);
        Binding bindingWelcome = this.binding(queueWelcome, exchange);
        // Criando no broker
        this.amqpAdmin.declareQueue(queueWelcome);
        this.amqpAdmin.declareExchange(exchange);
        this.amqpAdmin.declareBinding(bindingWelcome);
    }
}
