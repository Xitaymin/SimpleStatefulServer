package ua.training;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class SimpleHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange)  {
        String requestParam = exchange.getRequestURI().toString().split("\\?")[1].split("=")[1];
    }
}
