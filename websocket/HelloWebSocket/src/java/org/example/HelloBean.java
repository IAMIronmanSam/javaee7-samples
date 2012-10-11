/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example;

import javax.net.websocket.annotations.WebSocketEndpoint;
import javax.net.websocket.annotations.WebSocketMessage;

/**
 * @author Arun Gupta
 */
@WebSocketEndpoint(path="/hello")
public class HelloBean {
    
    @WebSocketMessage
    public String sayHello(String name) {
        return "Hello " + name + "!";
    }
    
}
