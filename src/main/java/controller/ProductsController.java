package controller;

import Domain.Products;
import Services.RespostaEndPoint;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ProductsController {
    private static List<Products> productsList = new ArrayList<>();
    static RespostaEndPoint res = new RespostaEndPoint();
    public static class produtos implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
                if ("GET".equals(exchange.getRequestMethod())) {
                List<Products> getAllFromArray = Products.getAllProducts(productsList);
                Products productsJson = new Products();

                if(!getAllFromArray.isEmpty()){
                    for(Products products : getAllFromArray){
                    System.out.println("name: " + products.getName());
                    System.out.println("factory : " + products.getFactory() );
                    System.out.println("quantity: " + products.getQuantity());
                    System.out.println("-------------------------");
                    System.out.println();
                    }
                    res.enviarResponseJson(exchange, productsJson.arraytoJson(getAllFromArray), 200);
                }

            } else if ("POST".equals(exchange.getRequestMethod())) {
                try (InputStream resquestBody = exchange.getRequestBody()){
                    JSONObject json = new JSONObject(new String(resquestBody.readAllBytes()));

                Products products = new Products(
                        json.getString("name"),
                        json.getString("factory"),
                        json.getInt("quantity")
                );
                productsList.add(products);
                res.enviarResponseJson(exchange, products.toJson(), 200);
                System.out.println("productslist" + products.toJson());
                }catch(Exception e){

                    String resposta  = e.toString();
//                    res.enviarResponseJson(exchange, responseJson, 200);
//
                }

//                RespostaEndPoint.enviarResponseJson(exchange, responseJson, 200);
            } else if ("PUT".equals(exchange.getRequestMethod())) {
//                response = "essa e a rota de Poducts Put";
//                RespostaEndPoint.enviarResponseJson(exchange, , 200);
            } else if ("DELETE".equals(exchange.getRequestMethod())) {

//                RespostaEndPoint.enviarResponseJson(exchange, , 200);
            }else if ("OPTIONS".equals(exchange.getRequestMethod())){
                exchange.sendResponseHeaders(200,-1);
                exchange.close();
                return;
            }
            else {
//                response = "ERRO" + "o metodo utilizado foi "+ exchange.getRequestMethod();
//                RespostaEndPoint.enviarResponseJson(exchange, responseJson, 405);
            }

        }}
}
