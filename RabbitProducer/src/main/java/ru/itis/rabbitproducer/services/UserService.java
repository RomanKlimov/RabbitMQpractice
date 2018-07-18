package ru.itis.rabbitproducer.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.itis.rabbitproducer.dto.UserDTO;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final String exchangeTopic = "boot-rabbit-exchange";
    private final String emailBinding = "send-email";
    private final String smsBinding = "send-sms";

    private final RabbitTemplate rabbitTemplate;

    public UserService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void Send(UserDTO userDTO) {

        logger.info("Sending to " + userDTO.toString());

        this.rabbitTemplate.convertAndSend(this.exchangeTopic, this.emailBinding, userDTO.getEmail());
        this.rabbitTemplate.convertAndSend(this.exchangeTopic, this.smsBinding, userDTO.getPhone());

        logger.info("It's done.");

    }
}
