package ua.training;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SimpleHttpServerLauncher {
    private HttpHandler handler = new SimpleHttpHandler();
    private HttpServer server;
    //todo get context path, localhost and port from properties

    public void initial() throws IOException{
        server = HttpServer.create(new InetSocketAddress("localhost",8080), 0);
        server.createContext("/app/txts", handler);
        server.start();
    }


}
