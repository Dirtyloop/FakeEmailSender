package com.komfortcieplny.FakeEmailSender.email.service;

import com.komfortcieplny.FakeEmailSender.email.model.EmailModel;
import com.komfortcieplny.FakeEmailSender.user.controller.UserController;
import com.komfortcieplny.FakeEmailSender.user.model.User;
import com.komfortcieplny.FakeEmailSender.user.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService {
    private static final String FROM = "FakeEmailSender <javafakeapp@gmail.com>";
    private static final Logger logger = LogManager.getLogger(UserController.class);
    private final JavaMailSender mailSender;
    private final UserService userService;

    public EmailService(JavaMailSender mailSender, UserService userService) {
        this.mailSender = mailSender;
        this.userService = userService;
    }

    public void sendEmail(EmailModel emailModel) {
        String[] emails = prepareEmailList();
        mailSender.send(prepareMailMessage(emails, emailModel));
        logger.info("Email sent to all users");
    }

    public void sendEmailToId(EmailModel emailModel, Long id) {
        String[] emails = {userService.getUser(id).getEmail()};
        mailSender.send(prepareMailMessage(emails, emailModel));
        logger.info(String.format("Email sent to user with id %d", id));
    }

    private String[] prepareEmailList() {
        List<User> userList = userService.getUsers();
        List<String> emailList = new ArrayList<>();
        for (User user : userList) {
            emailList.add(user.getEmail());
        }
        return emailList.toArray(new String[0]);
    }

    private SimpleMailMessage prepareMailMessage(String[] emails, EmailModel emailModel) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(FROM);
        mailMessage.setTo(emails);
        mailMessage.setSubject(emailModel.subject());
        mailMessage.setText(emailModel.message());
        return mailMessage;
    }
}
