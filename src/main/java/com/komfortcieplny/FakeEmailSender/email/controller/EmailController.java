package com.komfortcieplny.FakeEmailSender.email.controller;

import com.komfortcieplny.FakeEmailSender.email.model.EmailModel;
import com.komfortcieplny.FakeEmailSender.email.service.EmailService;
import com.komfortcieplny.FakeEmailSender.user.service.UserService;
import com.komfortcieplny.FakeEmailSender.utils.RequestLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {

    private static final Logger logger = LogManager.getLogger(UserService.class);
    private final EmailService emailService;
    private final RequestLogger requestLogger;

    public EmailController(EmailService emailService, RequestLogger requestLogger) {
        this.emailService = emailService;
        this.requestLogger = requestLogger;
    }

    @PostMapping()
    public String sendEmail(@RequestBody EmailModel emailModel) {
        try {
            requestLogger.logInfo(
                    String.format(
                            "sendEmail to all users with %s requested",
                            emailModel.toString()
                    )
            );
        } catch (IOException e) {
            logger.error("Exception! sendEmail request was not logged");
        }
        emailService.sendEmail(emailModel);
        return "Email sent to all users";
    }

    @PostMapping("/{id}")
    public String sendEmail(@RequestBody EmailModel emailModel, @PathVariable("id") Long id) {
        try {
            requestLogger.logInfo(
                    String.format(
                            "sendEmail to user with id %d with %s requested",
                            id,
                            emailModel.toString()
                    )
            );
        } catch (IOException e) {
            logger.error("Exception! sendEmail request was not logged");
        }
        emailService.sendEmailToId(emailModel, id);
        return String.format("Email sent to user with id %d", id);
    }
}
