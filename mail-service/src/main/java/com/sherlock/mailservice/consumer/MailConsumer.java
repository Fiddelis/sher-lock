package com.sherlock.mailservice.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sherlock.mailservice.model.Client;
import com.sherlock.mailservice.model.Product;
import com.sherlock.mailservice.model.RegisterDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MailConsumer {

    Client client;
    Product product;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "mail-request-queue")
    public void receiveMessage(String message) {
        try {
            RegisterDTO registerDTO = objectMapper.readValue(message, RegisterDTO.class);
            processRegisterDTO(registerDTO);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void processRegisterDTO(RegisterDTO registerDTO) {
        System.out.println(registerDTO.getClient().toString());
        System.out.println(registerDTO.getProduct().toString());
    }
}
