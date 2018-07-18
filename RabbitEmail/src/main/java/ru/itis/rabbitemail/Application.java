package ru.itis.rabbitemail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"ru.itis.rabbitemail.services", "ru.itis.rabbitemail.receivers"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }
}


