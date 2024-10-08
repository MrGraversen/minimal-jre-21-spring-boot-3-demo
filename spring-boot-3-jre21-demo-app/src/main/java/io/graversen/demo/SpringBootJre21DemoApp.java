package io.graversen.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "io.graversen.demo.configuration")
public class SpringBootJre21DemoApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootJre21DemoApp.class, args);
    }
}
