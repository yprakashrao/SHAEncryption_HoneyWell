package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example"}) // Ensures all sub-packages are scanned
public class EncryptTextApplication {
    public static void main(String[] args) {
        SpringApplication.run(EncryptTextApplication.class, args);
    }
}