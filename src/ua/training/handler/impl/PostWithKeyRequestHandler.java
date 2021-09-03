package ua.training.handler.impl;

import com.sun.net.httpserver.HttpExchange;
import ua.training.handler.RequestHandler;

import java.util.Map;

public class PostWithKeyRequestHandler implements RequestHandler {
    @Override
    public void execute(HttpExchange exchange, Map<String, String> usersParams) {
        String body = getRequestBody(exchange);
        if(!body.isEmpty()){
            String key = getKeyFromURI(exchange);
            usersParams.remove(key);
            usersParams.put(String.valueOf(body.hashCode()),body);
        }

        sendResponse(exchange,200);
        exchange.close();
    }
}
