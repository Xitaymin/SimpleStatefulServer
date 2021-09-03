package ua.training.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ua.training.GlobalConstants;
import ua.training.handler.impl.*;

import java.util.HashMap;
import java.util.Map;

public class MainHandler implements HttpHandler, GlobalConstants {
    private Map<String, RequestHandler> handlers;
    private Map<String, String> texts = new HashMap<>();

    public MainHandler() {
        this.handlers = new HashMap<>();
        handlers.put("GET/app/txts", new GetRequestHandler());
        handlers.put("GET/app/txts/", new GetWithKeyRequestHandler());
        handlers.put("POST/app/txts", new PostRequestHandler());
        handlers.put("POST/app/txts/", new PostWithKeyRequestHandler());
        handlers.put("DELETE/app/txts/", new DeleteWithKeyRequestHandler());
    }
    @Override
    public void handle(HttpExchange exchange)  {
        String methodName = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String commandKey = "";
        if (path.equals(APPLICATION_CONTEXT)||path.equals(EXTENDED_CONTEXT)) {
             commandKey = methodName + APPLICATION_CONTEXT;
        }
        else if (path.matches(PATH_WITH_KEY_REGEX)){
            commandKey = methodName + EXTENDED_CONTEXT;
        }

        handlers.getOrDefault(commandKey, new IncorrectRequestHandler()).execute(exchange, texts);
    }
}
