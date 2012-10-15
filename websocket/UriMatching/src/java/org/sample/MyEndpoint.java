/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sample;

import javax.net.websocket.annotations.WebSocketEndpoint;
import javax.net.websocket.annotations.WebSocketMessage;

/**
 *
 * @author arungup
 */
@WebSocketEndpoint(path="/orders/{order-id}")
public class MyEndpoint {
    @WebSocketMessage
    public String processOrder(@WebSocketPathParam("order-id")String orderId) {
        return "";
    }
    
}
