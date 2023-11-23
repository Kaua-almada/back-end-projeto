package Services;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import controller.ProductsController;
import controller.SalesController;
import controller.UsersController;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Servidor {
    public void piServer() throws IOException {
        HttpHandler salesHandler = new SalesController.produtos();
        HttpHandler products = new ProductsController.produtos();
        HttpHandler userHandler = new UsersController.user();

        HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);
        server.createContext("/api/users",exchange -> {
            configureCorsHeaders(exchange);
            userHandler.handle(exchange);
        });
        server.createContext("/api/venda", exchange -> {
            configureCorsHeaders(exchange);
            salesHandler.handle(exchange);
        });
        server.createContext("/api/produtos", exchange -> {
            configureCorsHeaders(exchange);
            products.handle(exchange);
        });
        server.setExecutor(null);
        System.out.println("Servidor iniciado");
        server.start();
    }
    private static void configureCorsHeaders(HttpExchange exchange) {
        Headers headers = exchange.getResponseHeaders();
        String requestOrigin = exchange.getRequestHeaders().getFirst("Origin");
        if (requestOrigin != null) {
            headers.set("Access-Control-Allow-Origin", requestOrigin);
            headers.set("Access-Control-Allow-Methods", "GET, OPTIONS, POST, PUT, DELETE");
            headers.set("Access-Control-Allow-Headers", "Content-Type");
            headers.set("Access-Control-Allow-Credentials", "true");
        }
    }

}
