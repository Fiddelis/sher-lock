package com.sherlock.mailservice.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.WriterException;
import com.sherlock.mailservice.model.Client;
import com.sherlock.mailservice.model.Product;
import com.sherlock.mailservice.model.RegisterDTO;
import com.sherlock.mailservice.service.MailService;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MailConsumer {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final MailService mailService;


    @Autowired
    public MailConsumer(MailService mailService) {
        this.mailService = mailService;
    }

    @RabbitListener(queues = "mail-request-queue")
    public void receiveMessage(String message) {
        try {
            RegisterDTO registerDTO = objectMapper.readValue(message, RegisterDTO.class);
            mailService.sendEmailWithQRCode(registerDTO);
        } catch (IOException | TemplateException | MessagingException | WriterException e) {
            e.printStackTrace();
        }
    }
}
