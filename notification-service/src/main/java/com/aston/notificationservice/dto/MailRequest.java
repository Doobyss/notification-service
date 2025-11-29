package com.aston.notificationservice.dto;

import lombok.Data;

@Data
public class MailRequest {
    private String email;
    private String message;
}