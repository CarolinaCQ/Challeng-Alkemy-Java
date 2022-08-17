package edu.challengAlkemy.javaSpringBoot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    
    private final JavaMailSender javaMailSender;
    
    private final String SUBJECT = "Email de bienvenida";
    private final String MESSAGE = "Su cuenta en la API de Disney se ha creado con éxito!";
    private final String EMAIL_FROM = "carolinaquevedo01@gmail.com";

    public void sendMail(String toEmail) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(toEmail);
        mailMessage.setSubject(SUBJECT);
        mailMessage.setText(MESSAGE);

        mailMessage.setFrom(EMAIL_FROM);

        javaMailSender.send(mailMessage);
    }
    
}
