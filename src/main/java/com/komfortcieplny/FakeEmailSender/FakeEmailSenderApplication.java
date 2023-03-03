package com.komfortcieplny.FakeEmailSender;

import com.komfortcieplny.FakeEmailSender.email.model.EmailModel;
import com.komfortcieplny.FakeEmailSender.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class FakeEmailSenderApplication {

	@Autowired
	private EmailService emailService;

	private EmailModel emailModel = new EmailModel(
			"michal.nowakowski85@gmail.com",
			"temat",
			"Witaj! Otrzymałeś pierwszy email z FakeEmailSender!");

	public static void main(String[] args) {
		SpringApplication.run(FakeEmailSenderApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void sendMail() {
		//emailService.sendEmail(emailModel);
		System.out.println("event");
	}

}
