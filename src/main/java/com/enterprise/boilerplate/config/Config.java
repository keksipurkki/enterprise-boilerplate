package com.enterprise.boilerplate.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Config {

    @Autowired
    private PersistenceConfig persistenceConfig;


}
