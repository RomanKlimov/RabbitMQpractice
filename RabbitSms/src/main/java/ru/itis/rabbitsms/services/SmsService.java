package ru.itis.rabbitsms.services;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class SmsService {
    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Value("${sms.aero.user}")
    private String smsAeroLogin;

    @Value("${sms.aero.password}")
    private String smsAeroPassword;

    @Value("${sms.aero.from}")
    private String smsAeroFrom;

    @Value("${sms.aero.type}")
    private String smsAeroType;

    @Value("${sms.aero.url}")
    private String smsAeroUri;

    @Transactional
    public boolean sendSMS(String phone) throws InterruptedException, ExecutionException {

        Future<Boolean> result =  this.executorService.submit(() ->{

            RestTemplate restTemplate = new RestTemplate();

            StringBuilder sb = new StringBuilder();

            sb.append(this.smsAeroUri)
                    .append("?user=").append(this.smsAeroLogin)
                    .append("&password=").append(this.smsAeroPassword)
                    .append("&to=").append(phone)
                    .append("&text=").append("Registration status - success")
                    .append("&from=").append(this.smsAeroFrom)
                    .append("&type=").append(this.smsAeroType);

            ResponseEntity<String> responseEntity = restTemplate.getForEntity(sb.toString(), String.class);

            return responseEntity.getBody().contains("accepted");

        });

        return result.get();

    }
}
