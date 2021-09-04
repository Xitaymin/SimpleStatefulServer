package ua.training;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import ua.training.handler.MainHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Properties;

public class Main implements GlobalConstants {
    public static void main(String[] args) throws IOException {

        HttpHandler handler = new MainHandler();
        HttpServer server;

        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "network.properties";

        Properties properties = new Properties();
        properties.load(new FileInputStream(appConfigPath));

        String hostname = properties.getProperty("host.name");
        String port = properties.getProperty("port.number");

        server = HttpServer.create(new InetSocketAddress(hostname, Integer.parseInt(port)), 0);
        server.createContext(APPLICATION_CONTEXT, handler);
        server.start();
    }

}
