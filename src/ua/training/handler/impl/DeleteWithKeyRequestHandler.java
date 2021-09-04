package ua.training.handler.impl;

import com.sun.net.httpserver.HttpExchange;
import ua.training.StatusCodesConstants;
import ua.training.handler.RequestHandler;

import java.util.Map;

public class DeleteWithKeyRequestHandler implements RequestHandler, StatusCodesConstants {
    @Override
    public void execute(HttpExchange exchange, Map<String, String> texts) {
        if(texts.remove(getKeyFromURI(exchange))!=null){
            sendResponse(exchange,SUCCESFUL_WITHOUT_BODY);
        }
        else {
        sendResponse(exchange,NOT_FOUND_RESOURCE);
        }
        exchange.close();
    }
}
