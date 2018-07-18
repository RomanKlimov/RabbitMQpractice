package ru.itis.rabbitemail.receivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.rabbitemail.services.interfaces.EmailService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Component
public class EmailReceiver {

    private static final Logger logger = LoggerFactory.getLogger(EmailReceiver.class);
    private final EmailService emailService;

    @Autowired
    public EmailReceiver(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "rabbit-email")
    public void process(String email){

        logger.info("Sending on mail to: " + email);

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> this.emailService.sendEmail(email));

        logger.info("Done");

    }
}
