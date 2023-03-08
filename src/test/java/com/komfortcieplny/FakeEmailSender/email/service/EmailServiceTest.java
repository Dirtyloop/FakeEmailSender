package com.komfortcieplny.FakeEmailSender.email.service;

import com.komfortcieplny.FakeEmailSender.email.model.EmailModel;
import com.komfortcieplny.FakeEmailSender.user.model.User;
import com.komfortcieplny.FakeEmailSender.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import java.util.List;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private JavaMailSender mailSender;
    @Mock
    private UserService userService;

    @Test
    @DisplayName("Should Invoke mailSender.send")
    void sendEmailSimpleTest() {
        EmailModel emailModel = new EmailModel("Subject", "Message");
        String [] emails = {null, null, null};
        when(userService.getUsers()).thenReturn(List.of(new User(), new User(), new User()));
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("FakeEmailSender <javafakeapp@gmail.com>");
        mailMessage.setTo(emails);
        mailMessage.setSubject(emailModel.subject());
        mailMessage.setText(emailModel.message());
        emailService.sendEmail(emailModel);

        verify(mailSender, times(1)).send(mailMessage);
    }

    @Test
    @DisplayName("Should prepare emails and Invoke mailSender.send")
    void shouldPrepareEmailsAndSendTest() {
        EmailModel emailModel = new EmailModel("Subject", "Message");
        String [] emails = {"adam@test.com", "ewa@test.com", "ola@test.com"};
        User user1 = new User(1l, "Adam", "adam@test.com");
        User user2 = new User(2l, "Ewa", "ewa@test.com");
        User user3 = new User(3l, "Ola", "ola@test.com");

        when(userService.getUsers()).thenReturn(List.of(user1, user2, user3));
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("FakeEmailSender <javafakeapp@gmail.com>");
        mailMessage.setTo(emails);
        mailMessage.setSubject(emailModel.subject());
        mailMessage.setText(emailModel.message());
        emailService.sendEmail(emailModel);

        verify(mailSender, times(1)).send(mailMessage);
    }
}