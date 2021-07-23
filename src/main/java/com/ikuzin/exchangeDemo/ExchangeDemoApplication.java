package com.ikuzin.exchangeDemo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ExchangeDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExchangeDemoApplication.class, args);
    }
}
