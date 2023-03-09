# FakeEmailSender

### About Application
FakeEmailSender is a microservice made in Java with Spring Boot. Application is responsible for sending emails to users from its own database.

Application uses gmail account to sending emails, but you can configure it for other provider.
### Guides

First, before run this application you have to update 'application.properties' file.

Replace "user" and "password" with your account credentials.
```properties
spring.mail.username=user
spring.mail.password=password
```
After that you can run the application however you like (from IDE, from terminal using `mvn spring-boot:run`).

### User database
Application works with H2 database preconfigured with `schema.sql` and `data.sql` files. After application start there is one table named `USERS` with five users saved.

### REST API
Service provides its API on the following endpoints:
* User Database - http://localhost:8080/api/v1/users
* Email Sender - http://localhost:8080/api/v1/email

Now you can:
* get all users - GET http://localhost:8080/api/v1/users
* get user with id - GET http://localhost:8080/api/v1/users/{id}
* create new user - POST http://localhost:8080/api/v1/users
* update user with id - PUT http://localhost:8080/api/v1/users/{id}
* delete user with id - DELETE http://localhost:8080/api/v1/users/{id}

* send email to all users - POST http://localhost:8080/api/v1/email
* send email to user with id - POST http://localhost:8080/api/v1/email/{id}


All data should be sent in Request Body in JSON format:
* get user with id=1 returns {"id": 1, "name": "user name", "email": "user email"}
* create user expects {"name": "user name", "email": "user email"}
* update user expects {"name": "new user name", "email": "new user email"}
* send email expects {"subject": "Subject", "message": "Message"}

### Data validation
There is a validation mechanism on User.name and User.email fields.
Application accept only NotBlank names and proper email addresses.

### Loggers
There are two independent logging mechanisms implemented in the application.

One (log4j2) to log general application events: `./logs/fakeemailsender.log`

The second one is a simple self-made request logger to log all requests from REST API: `./logs/requests.log`

Log files will be created automatically with first entry.