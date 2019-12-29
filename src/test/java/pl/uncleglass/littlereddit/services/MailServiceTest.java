package pl.uncleglass.littlereddit.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MailServiceTest {
    @Autowired
    public JavaMailSender emailSender;

    @Test
    void name() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("bartekkiliszek@gmail.com");
        message.setFrom("info@uncleglass.pl");
        message.setSubject("test");
        message.setText("text");
        emailSender.send(message);
    }
}