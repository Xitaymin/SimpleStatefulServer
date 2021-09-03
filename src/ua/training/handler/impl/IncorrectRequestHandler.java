package ua.training.handler.impl;

import com.sun.net.httpserver.HttpExchange;
import ua.training.handler.RequestHandler;

import java.util.Map;

public class IncorrectRequestHandler implements RequestHandler {
    @Override
    public void execute(HttpExchange exchange, Map<String, String> usersParams) {
        sendResponse(exchange,404);
        exchange.close();
    }
}
