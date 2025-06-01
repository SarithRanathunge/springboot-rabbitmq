package net.javaguides.springboot.controller;

import net.javaguides.springboot.dto.User;
import net.javaguides.springboot.publisher.RabbitMQJSONProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MessageJSONController {

    private RabbitMQJSONProducer rabbitMQJSONProducer;

    public MessageJSONController(RabbitMQJSONProducer rabbitMQJSONProducer) {
        this.rabbitMQJSONProducer = rabbitMQJSONProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendJSONMessage(@RequestBody User user){
        rabbitMQJSONProducer.sendJsonMessage(user);
        return ResponseEntity.ok("JSON message sent to RabbitMQ...");
    }
}
