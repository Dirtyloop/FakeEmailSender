package com.komfortcieplny.FakeEmailSender.utils;

import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class RequestLogger {

    private static final String NEW_LINE = System.lineSeparator();
    Path filePath = Path.of("./logs/requests.log");

    public boolean logInfo(String log) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        stringBuilder.append(" ");
        stringBuilder.append(log);
        stringBuilder.append(NEW_LINE);

        Files.writeString(
                filePath,
                stringBuilder,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        );
        return true;
    }
}
