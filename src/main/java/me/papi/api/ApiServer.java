package me.papi.api;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

public class ApiServer {
    public static void start(int port) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/api/player", new PlayerEndpoint());
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
