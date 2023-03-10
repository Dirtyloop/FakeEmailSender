FROM eclipse-temurin:17-jdk-focal
COPY target/fake-email-sender.jar /fake-email-sender.jar
ENTRYPOINT ["java", "-jar", "/fake-email-sender.jar"]