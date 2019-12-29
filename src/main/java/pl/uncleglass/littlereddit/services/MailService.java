package pl.uncleglass.littlereddit.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import pl.uncleglass.littlereddit.domain.User;

import javax.mail.internet.MimeMessage;
import java.util.Locale;

@Service
public class MailService {

    private JavaMailSender javaMailSender;
    private SpringTemplateEngine templateEngine;
    private final String BASE_URL = "http://localhost:8080";
    @Value("${spring.mail.username}")
    private String sender;

    public MailService(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sendEmail(String to, String subject, String content, boolean isHtml) {

        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
            message.setTo(to);
            message.setFrom(sender);
            message.setSubject(subject);
            message.setText(content,isHtml);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Async
    public void sendEmailFromTemplate(User user, String templateName, String subject) {
        Locale locale = Locale.ENGLISH;
        Context context = new Context(locale);
        context.setVariable("user", user);
        context.setVariable("baseURL",BASE_URL);
        String content = templateEngine.process(templateName,context);
        sendEmail(user.getEmail(),subject,content,true);
    }

    @Async
    public void sendActivationEmail(User user) {
        sendEmailFromTemplate(user, "activation", "Little Reddit User Activation");
    }

    @Async
    public void sendWelcomeEmail(User user) {
        sendEmailFromTemplate(user, "welcome", "Welcome new Little Reddit User");
    }

}
