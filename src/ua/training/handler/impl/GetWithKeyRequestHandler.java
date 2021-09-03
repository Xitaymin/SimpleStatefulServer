package ua.training.handler.impl;

import com.sun.net.httpserver.HttpExchange;
import ua.training.handler.RequestHandler;

import java.util.Map;

public class GetWithKeyRequestHandler implements RequestHandler {
    @Override
    public void execute(HttpExchange exchange, Map<String, String> usersParams) {
        String path = exchange.getRequestURI().getPath();
        String key = path.substring(EXTENDED_CONTEXT.length());
        String response = usersParams.getOrDefault(key, NOT_FOUND);

        sendResponse(exchange, response, 200);
        exchange.close();

    }
}
