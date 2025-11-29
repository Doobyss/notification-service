package com.aston.notificationservice.listener;

import com.aston.notificationservice.dto.UserEvent;
import com.aston.notificationservice.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = {"user-events"})
class KafkaUserListenerTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @MockBean
    private EmailService emailService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testAccountCreatedEvent() throws Exception {
        UserEvent event = new UserEvent();
        event.setEmail("test@mail.com");
        event.setOperation("CREATE");

        String json = objectMapper.writeValueAsString(event);

        kafkaTemplate.send("user-events", json);

        verify(emailService, timeout(5000))
                .sendAccountCreatedEmail("test@mail.com");
    }

    @Test
    void testAccountDeletedEvent() throws Exception {
        UserEvent event = new UserEvent();
        event.setEmail("test@mail.com");
        event.setOperation("DELETE");

        String json = objectMapper.writeValueAsString(event);

        kafkaTemplate.send("user-events", json);

        verify(emailService, timeout(5000))
                .sendAccountDeletedEmail("test@mail.com");
    }

}