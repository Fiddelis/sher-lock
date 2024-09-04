package com.sherlock.apiservice.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sherlock.apiservice.model.RegisterDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MailProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void send(RegisterDTO mailDTO) throws JsonProcessingException {
        amqpTemplate.convertAndSend(
                "mail-request-exchanges",
                "mail-request-rout-key",
                objectMapper.writeValueAsString(mailDTO)
        );
    }
}
