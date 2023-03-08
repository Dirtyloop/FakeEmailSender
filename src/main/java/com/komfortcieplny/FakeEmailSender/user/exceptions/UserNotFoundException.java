package com.komfortcieplny.FakeEmailSender.user.exceptions;

import com.komfortcieplny.FakeEmailSender.user.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserNotFoundException  extends RuntimeException {
    private static final Logger logger = LogManager.getLogger(UserService.class);

    public UserNotFoundException(String message) {
        super(message);
        logger.error(message);
    }
}
