package com.aston.notificationservice.controller;

import com.aston.notificationservice.dto.MailRequest;
import com.aston.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<?> sendCustomMail(@RequestBody MailRequest request) {
        emailService.send(request.getEmail(), "Сообщение от сервиса", request.getMessage());
        return ResponseEntity.ok("Отправлено");
    }
}