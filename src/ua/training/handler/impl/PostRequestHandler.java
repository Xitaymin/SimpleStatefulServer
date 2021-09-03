package ua.training.handler.impl;

import com.sun.net.httpserver.HttpExchange;
import ua.training.handler.RequestHandler;

import java.util.Map;

public class PostRequestHandler implements RequestHandler {
    @Override
    public void execute(HttpExchange exchange, Map<String, String> usersParams) {
        String requestBody = getRequestBody(exchange);
        if(!requestBody.isEmpty()){
            String key = String.valueOf(requestBody.hashCode());
            if(usersParams.putIfAbsent(key,requestBody)==null){
                sendResponse(exchange,200);
            }
            else sendResponse(exchange,key,200);
        }
        exchange.close();
    }


}
