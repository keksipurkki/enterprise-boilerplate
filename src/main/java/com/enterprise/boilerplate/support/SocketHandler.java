package com.enterprise.boilerplate.support;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketHandler extends TextWebSocketHandler {

    public static String PATH = "/socket";

}
