package net.javaguides.springboot.publisher;

import net.javaguides.springboot.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJSONProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.json.routing.key}")
    private String jsonRoutingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJSONProducer.class);

    private RabbitTemplate rabbitTemplate;

    public RabbitMQJSONProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(User user){
        LOGGER.info(String.format("JSON Message sent -> %s", user.gettAll()));
        rabbitTemplate.convertAndSend(exchange, jsonRoutingKey, user);
    }
}
