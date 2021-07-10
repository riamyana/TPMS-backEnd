package com.trasportManagement.transportservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailData {

    private String toEmail;
    private String subject;
    private String body;
    private Map<String, Object> model;

    public EmailData(String toEmail, String subject, Map<String, Object> model) {
        this.toEmail = toEmail;
        this.subject = subject;
        this.model = model;
    }
}