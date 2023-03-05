package com.komfortcieplny.FakeEmailSender.email.controller;

import com.komfortcieplny.FakeEmailSender.email.model.EmailModel;
import com.komfortcieplny.FakeEmailSender.email.service.EmailService;
import com.komfortcieplny.FakeEmailSender.user.controller.UserController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class EmailController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    private final EmailModel emailModel = new EmailModel(
            "michal.nowakowski85@gmail.com",
            "temat",
            "Witaj! Otrzymałeś pierwszy email z FakeEmailSender!");

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @RequestMapping("/hello")
    public String hello() {
        //emailService.sendEmail(emailModel);
        return "Hello!";
    }

    @PostMapping("/send")
    public String send(@RequestBody EmailModel emailModel) {
        emailService.sendEmail(emailModel);
        logger.info("sent controller");
        return "Email2 sent";
    }

    @GetMapping("/sendemail")
    public String sendEmail() {
        //emailService.sendEmail(emailModel);
        logger.info("sent controller");
        return "Email sent";
    }
    @PostMapping("/sendemail2")
    public String sendEmail2(@RequestBody EmailModel emailModel) {
        emailService.sendEmail(emailModel);
        logger.info("sent controller");
        return "Email2 sent";
    }

}
