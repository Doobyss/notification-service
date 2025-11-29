package com.aston.notificationservice.kafka;

import com.aston.notificationservice.dto.UserEvent;
import com.aston.notificationservice.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserKafkaListener {

    private final EmailService emailService;
    private final ObjectMapper mapper = new ObjectMapper();

    @KafkaListener(topics = "user-events")
    public void handleEvent(String message) throws Exception {
        UserEvent event = mapper.readValue(message, UserEvent.class);

        switch (event.getOperation()) {
            case "CREATE" -> emailService.sendAccountCreatedEmail(event.getEmail());
            case "DELETE" -> emailService.sendAccountDeletedEmail(event.getEmail());
        }
    }
}