package ua.training;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SimpleHttpServerLauncher {
    private HttpHandler handler = new SimpleHttpHandler();
    private HttpServer server;

    public void initial() throws IOException{
        HttpServer.create(new InetSocketAddress("localhost",8080), 0);
        server.createContext("/app", handler);
        server.start();
    }


}
