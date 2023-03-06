package com.komfortcieplny.FakeEmailSender.email.service;

import com.komfortcieplny.FakeEmailSender.email.model.EmailModel;
import com.komfortcieplny.FakeEmailSender.email.utils.EmailLog;
import com.komfortcieplny.FakeEmailSender.user.model.User;
import com.komfortcieplny.FakeEmailSender.user.service.UserService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final EmailLog emailLog = new EmailLog();
    private final UserService userService;

    public EmailService(JavaMailSender mailSender, UserService userService) {
        this.mailSender = mailSender;
        this.userService = userService;
    }
    public void sendEmail(EmailModel emailModel){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        String[] emailList = prepareEmailList();
        mailMessage.setFrom("FakeEmailSender");
        mailMessage.setTo(emailList);
        mailMessage.setSubject(emailModel.subject());
        mailMessage.setText(emailModel.message());
        mailSender.send(mailMessage);
        try {
            emailLog.logInfo("Message to " + emailModel.recipient() + " sent");
        } catch (IOException e) {
            System.out.println("Exception! Message was not logged");
        }
        System.out.println("sent");
    }

    private String[] prepareEmailList() {
        List<User> userList = userService.getUsers();
        List<String> emailList = new ArrayList<>();
        for(User user : userList) {
            emailList.add(user.getEmail());
        }
        return emailList.toArray(new String[0]);
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
