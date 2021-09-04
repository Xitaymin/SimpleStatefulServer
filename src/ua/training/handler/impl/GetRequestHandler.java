package ua.training.handler.impl;

import com.sun.net.httpserver.HttpExchange;
import ua.training.StatusCodesConstants;
import ua.training.handler.RequestHandler;

import java.util.Map;

public class GetRequestHandler implements RequestHandler, StatusCodesConstants {
    @Override
    public void execute(HttpExchange exchange, Map<String,String> texts) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> e : texts.entrySet()) {
            sb.append(e.getKey());
            sb.append(" = ");
            sb.append(e.getValue());
            sb.append("\n");
        }
        String response = sb.toString();
        sendResponse(exchange,response,SUCCESFUL);
        exchange.close();
    }
}
