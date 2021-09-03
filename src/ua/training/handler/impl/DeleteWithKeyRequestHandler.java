package ua.training.handler.impl;

import com.sun.net.httpserver.HttpExchange;
import ua.training.handler.RequestHandler;

import java.util.Map;

public class DeleteWithKeyRequestHandler implements RequestHandler {
    @Override
    public void execute(HttpExchange exchange, Map<String, String> texts) {
        texts.remove(getKeyFromURI(exchange));
        //todo process situation when no key was found
        sendResponse(exchange,200);
        exchange.close();
    }
}
