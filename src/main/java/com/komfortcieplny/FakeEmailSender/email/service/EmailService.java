package com.komfortcieplny.FakeEmailSender.email.service;

import com.komfortcieplny.FakeEmailSender.email.model.EmailModel;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    public void sendEmail(EmailModel emailModel) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("FakeEmailSender");
        mailMessage.setTo(emailModel.getRecipient());
        mailMessage.setSubject(emailModel.getSubject());
        mailMessage.setText(emailModel.getMessage());

        mailSender.send(mailMessage);

        System.out.println("sent");
    }

    public void sendE() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("FakeEmailSender");
        mailMessage.setTo("michal.nowakowski85@gmail.com");
        mailMessage.setSubject("Subject");
        mailMessage.setText("Text");

        mailSender.send(mailMessage);

        System.out.println("sendE");
    }
}
