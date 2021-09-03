package ua.training.handler;

import com.sun.net.httpserver.HttpExchange;
import ua.training.GlobalConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public interface RequestHandler extends GlobalConstants {
    void execute(HttpExchange exchange, Map<String,String> usersParams);

    default void sendResponse(HttpExchange exchange, String response, int httpCode){
        try {
            exchange.sendResponseHeaders(httpCode, response.length());
            exchange.getResponseBody().write(response.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    default void sendResponse(HttpExchange exchange, int httpCode){
        try {
            exchange.sendResponseHeaders(httpCode, -1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    default String getRequestBody(HttpExchange exchange) {
        String result = "";
        try (BufferedReader httpInput =
                     new BufferedReader(new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder();
            String input;
            while ((input = httpInput.readLine()) != null) {
                sb.append(input).append(" ");
            }
            result = sb.toString().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    default String getKeyFromURI(HttpExchange exchange){
        String path = exchange.getRequestURI().getPath();
        return path.substring(EXTENDED_CONTEXT.length());
    }
}
