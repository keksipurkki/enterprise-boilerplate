package com.enterprise.boilerplate.app;

import com.enterprise.boilerplate.config.Config;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackageClasses = Config.class)
@Configuration
public class App {

    public static void main(String... args) {
        SpringApplication.run(App.class);
    }

    public CommandLineRunner demo() {

        return (args) -> {

            System.out.println("Hello");

        };

    }

}
