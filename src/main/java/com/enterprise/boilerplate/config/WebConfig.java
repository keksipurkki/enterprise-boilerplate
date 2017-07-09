package com.enterprise.boilerplate.config;

import com.enterprise.boilerplate.support.SocketHandler;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation
        .WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation
        .WebSocketHandlerRegistry;

/**
 *
 * For configuring the web layer
 *
 */

@Configuration
//@EnableWebMvc
class WebConfig extends WebMvcConfigurerAdapter {

    private SocketHandler socketHandler;

    WebConfig(SocketHandler handler) {
        this.socketHandler = handler;
    }

    @Configuration
    @EnableWebSocket
    class SocketConfig implements WebSocketConfigurer {
        public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
            registry.addHandler(socketHandler,  SocketHandler.PATH);
        }
    }

}
