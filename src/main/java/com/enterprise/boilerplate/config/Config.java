package com.enterprise.boilerplate.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Autowired
    private PersistenceConfig persistenceConfig;

    @Autowired
    private WebConfig webConfig;

}
