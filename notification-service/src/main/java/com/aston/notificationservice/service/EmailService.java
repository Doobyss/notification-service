package com.aston.notificationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendAccountCreatedEmail(String email) {
        send(email, "Аккаунт создан",
                "Здравствуйте! Ваш аккаунт на сайте был успешно создан.");
    }

    public void sendAccountDeletedEmail(String email) {
        send(email, "Аккаунт удалён",
                "Здравствуйте! Ваш аккаунт был удалён.");
    }

    public void send(String to, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(text);
        mailSender.send(msg);
    }
}