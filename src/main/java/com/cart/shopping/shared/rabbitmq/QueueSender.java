package com.cart.shopping.shared.rabbitmq;

import com.cart.shopping.customer.interfaceAdapters.dtos.response.CustomerResDto;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class QueueSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * Envia a mensagem para o rabbitMQ
     * @param queueName -> Nome da fila
     * @param msg -> Mensagem a ser enviada
     */
    public void send(String queueName, Object msg) {
        rabbitTemplate.convertAndSend(queueName, msg);
    }
}
