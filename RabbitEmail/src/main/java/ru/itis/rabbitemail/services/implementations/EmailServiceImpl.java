package ru.itis.rabbitemail.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.rabbitemail.services.interfaces.EmailService;


@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    @Transactional
    public void sendEmail(String emailDestination) {

        MimeMessagePreparator messagePreparator = mimeMessage -> {

            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("itisspring2018@gmail.com");
            messageHelper.setTo(emailDestination);
            messageHelper.setSubject("Info about registration");
            messageHelper.setText("Congratulations, registered! ", true);

        };

        try{
            this.javaMailSender.send(messagePreparator);
        }catch (MailException e) {
            throw new IllegalArgumentException(e);
        }

    }

}
