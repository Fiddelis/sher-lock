package com.sherlock.mailservice.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.sherlock.mailservice.model.Client;
import com.sherlock.mailservice.model.Product;
import com.sherlock.mailservice.model.RegisterDTO;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailService {
    final Configuration configuration;
    final JavaMailSender javaMailSender;

    public MailService(Configuration configuration, JavaMailSender javaMailSender) {
        this.configuration = configuration;
        this.javaMailSender = javaMailSender;
    }

    public void sendEmailWithQRCode(RegisterDTO registerDTO) throws IOException, TemplateException, MessagingException, WriterException {
        Client client = registerDTO.getClient();
        Product product = registerDTO.getProduct();

        System.out.println(client);
        System.out.println(product);

        // Gera o QR code e obtém os bytes da imagem
        byte[] qrCodeDelivery = generateQRCodeImage(product.getDeliveryCode(), 200, 200);
        byte[] qrCodeWithdrawn = generateQRCodeImage(product.getWithdrawnCode(), 200, 200);

        ByteArrayResource byteDelivery = new ByteArrayResource(qrCodeDelivery);
        ByteArrayResource byteWithdrawn = new ByteArrayResource(qrCodeWithdrawn);

        // Criando a mensagem MimeMessage para suportar HTML
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");

        helper.setSubject("Sherlock - Entrega com QR Code");
        helper.setTo(client.getMail());
        helper.setFrom("contato@sherlog.shop");
        // Obtém o conteúdo HTML do template
        String emailContent = getEmailContent(client, product);

        // Adiciona o conteúdo HTML
        helper.setText(emailContent, true);

        helper.addInline("delivery", byteDelivery, "image/png");
        helper.addInline("withdrawn", byteWithdrawn, "image/png");

        // Envia o e-mail
        javaMailSender.send(mimeMessage);
        System.out.println("Email enviado para: " + client.getMail());
    }

    private byte[] generateQRCodeImage(String text, int width, int height) throws IOException, WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        return pngOutputStream.toByteArray();
    }

    String getEmailContent(Client client, Product product) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("product", product);
        model.put("client", client);
        model.put("delivery", "cid:delivery");
        model.put("withdrawn", "cid:withdrawn");

        configuration.getTemplate("mail.ftlh").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }
}
