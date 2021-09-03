package ua.training;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import ua.training.handler.MainHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SimpleHttpServerLauncher implements GlobalConstants {
    private HttpHandler handler = new MainHandler();
    private HttpServer server;

    public void initial() throws IOException{
        server = HttpServer.create(new InetSocketAddress("localhost",8080), 0);
        server.createContext(APPLICATION_CONTEXT, handler);
        server.start();
    }


}
