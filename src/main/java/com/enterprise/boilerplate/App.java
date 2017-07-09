package com.enterprise.boilerplate;

import com.enterprise.boilerplate.config.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded
        .EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat
        .TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@SpringBootApplication
@Slf4j
public class App {

    public static void main(String... args) {
        SpringApplication.run(App.class);
    }

    /* After the app has booted */
    @Bean
    public CommandLineRunner commandLineRunner() {
        return (args) -> {
            log.info("Hello {}", "there");
        };
    }

    @Bean
    protected ServletContextListener listener() {
        return new ServletContextListener() {

            @Override
            public void contextInitialized(ServletContextEvent sce) {
                log.info("ServletContext initialized");
            }

            @Override
            public void contextDestroyed(ServletContextEvent sce) {
                log.info("ServletContext destroyed");
            }

        };
    }

    /* If Tomcat needs tweaking... */
    @Bean
    EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory factory =
                new TomcatEmbeddedServletContainerFactory();

        return factory;
    }

    @Bean /* If Jackson object mapper needs tweaking */
    ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new ISO8601DateFormat());
        return mapper;
    }

}
