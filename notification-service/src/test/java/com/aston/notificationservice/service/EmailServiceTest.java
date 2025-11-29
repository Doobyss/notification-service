package com.aston.notificationservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmailServiceTest {

    @Test
    void testSendAccountCreatedEmail() {
        JavaMailSender mailSender = mock(JavaMailSender.class);
        EmailService service = new EmailService(mailSender);

        service.sendAccountCreatedEmail("test@mail.com");

        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    void testSendAccountDeletedEmail() {
        JavaMailSender mailSender = mock(JavaMailSender.class);
        EmailService service = new EmailService(mailSender);

        service.sendAccountDeletedEmail("test@mail.com");

        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}