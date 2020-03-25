package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.mail.Mail;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class SimpleEmailServiceTest {
    
    @InjectMocks
    private SimpleEmailService simpleEmailService;
    
    @Mock
    private JavaMailSender javaMailSender;
    
    @Autowired
    AdminConfig adminConfig;

    @Test
    void shouldSendEmailWithoutCc() {
        
        Mail mail = new Mail("test@test.com", "Test", "Test message", null);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        
        simpleEmailService.send(mail);
        
        verify(javaMailSender, times(1)).send(mailMessage);
    }
}