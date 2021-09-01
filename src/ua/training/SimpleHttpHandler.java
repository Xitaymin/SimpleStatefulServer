package ua.training;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleHttpHandler implements HttpHandler {
    Map<String, String> usersParams = new HashMap<>();
    AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void handle(HttpExchange exchange)  {
//        String requestParam = exchange.getRequestURI().toString().split("\\?")[1].split("=")[1];
        if ("GET".equals(exchange.getRequestMethod())) {
            handleGetRequest(exchange);
        } else if ("POST".equals(exchange.getRequestMethod())) {
            handlePostRequest(exchange);
        }
        else if ("DELETE".equals(exchange.getRequestMethod())){
            handleDeleteRequest(exchange);
        }

    }

    private void handleDeleteRequest(HttpExchange exchange) {
    }
//    POST /app/txts, если тело запроса не пустое, то взять его как текст, сгенерить для него ключ и положить в Map<String, String>
    private void handlePostRequest(HttpExchange exchange) {
//        Optional<String> requestBody = Optional.ofNullable(getRequestBody(exchange));
//        requestBody.ifPresent();
        //todo check if request body is empty
        String requestBody = getRequestBody(exchange);
        if(!requestBody.isEmpty()){
        usersParams.put(String.valueOf(counter.incrementAndGet()),requestBody);
            System.out.println(usersParams.get(String.valueOf(counter.get())));
        }
        System.out.println("Map size is " + usersParams.size());
        try {
            exchange.sendResponseHeaders(200, -1);
            exchange.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getRequestBody(HttpExchange exchange){
        InputStream requestBody = exchange.getRequestBody();
        String result = null;
        //todo use autocloseable
        try {
            //todo why not Scanner
            BufferedReader httpInput = new BufferedReader(new InputStreamReader(
                    exchange.getRequestBody(), StandardCharsets.UTF_8));
            //todo StringBuilder or StringBuffer
            StringBuilder in = new StringBuilder();
            String input;

            while ((input = httpInput.readLine()) != null) {
                in.append(input).append(" ");
            }
            result = in.toString().trim();
            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void handleGetRequest(HttpExchange exchange) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String,String> e:usersParams.entrySet()) {
            sb.append(e.getKey());
            sb.append(" = ");
            sb.append(e.getValue());
            sb.append("\n");
        }
//            String s = String.format("<html><body><h1>%s</h1></body></html>", resp);

            String response = sb.toString();
        try {
            exchange.sendResponseHeaders(200, sb.toString().length());
            exchange.getResponseBody().write(response.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        exchange.close();
    }

    private void showLine(Map.Entry<String, String> e) {
    }
}
