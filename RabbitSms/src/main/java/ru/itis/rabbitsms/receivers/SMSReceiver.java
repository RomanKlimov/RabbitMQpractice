package ru.itis.rabbitsms.receivers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.rabbitsms.services.SmsService;

import java.util.concurrent.ExecutionException;


@Component
public class SMSReceiver {

    private static final Logger logger = LoggerFactory.getLogger(SMSReceiver.class);

    private final SmsService smsService;

    @Autowired
    public SMSReceiver(SmsService smsService) {
        this.smsService = smsService;
    }

    @RabbitListener(queues = "rabbit-sms")
    public void process(String phone) throws ExecutionException, InterruptedException {

        logger.info("Sending sms on number " + phone);

        boolean result = this.smsService.sendSMS(phone);

        if (result) {
            logger.info("Message sent");
        } else {
            logger.error("error during sending sms");
        }

    }

}
