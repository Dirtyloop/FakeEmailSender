FROM arm32v7/eclipse-temurin:17-jdk-jammy
RUN addgroup spring
RUN adduser spring --ingroup spring
USER spring:spring
EXPOSE 8080/tcp
COPY target/fake-email-sender.jar fake-email-sender.jar
ENTRYPOINT ["java", "-jar", "/fake-email-sender.jar"]