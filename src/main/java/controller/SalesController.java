package controller;
import Domain.Sales;
import Services.RespostaEndPoint;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SalesController {

    private  static List<Sales> salesList = new ArrayList<>();
    static RespostaEndPoint res = new RespostaEndPoint();
    public static class produtos implements HttpHandler {
        String response = "";
        JSONObject responseJson = new JSONObject();
        @Override
        public void handle(HttpExchange exchange) throws IOException {


            if ("GET".equals(exchange.getRequestMethod())) {
                System.out.println("metodo get");

                Sales salesJson = new Sales();

                List<Sales> getAllFromArray = Sales.getAllSales(salesList);
                if(!getAllFromArray.isEmpty()){
                    for (Sales sales : getAllFromArray) {
                        System.out.println("Name: " + sales.getName());
                        System.out.println("Products: " + sales.getProduts());
                        System.out.println("Valor: " + sales.getvalor());
                        System.out.println("Finished Sale: " + sales.getFinishedSale());
                        System.out.println("Discount: " + sales.getdiscount());
                        System.out.println("Date of Sale: " + sales.getdateSale());
                        System.out.println();
                        System.out.println("--------------------------------------");
                    }
                    res.enviarResponseJson(exchange, salesJson.arraytoJson(getAllFromArray), 200);
                }
            }
            else if ("POST".equals(exchange.getRequestMethod())) {
                System.out.println("chegeui no post");
                try (InputStream resquestBody = exchange.getRequestBody()) {
                    JSONObject json = new JSONObject(new String(resquestBody.readAllBytes()));
                    Sales sales = new Sales(
                            json.getString("name"),
                            json.getString("produts"),
                            json.getString("valor"),
                            json.getBoolean("finishedSale"),
                            json.getDouble("discount"),
                            json.getString("dateSale")
                    );
                    salesList.add(sales);
                    res.enviarResponseJson(exchange, sales.arraytoJson(salesList), 200);
                    System.out.println("salesList" + sales.arraytoJson(salesList));
                } catch (Exception e) {
                    response = "erro";
                    String resposta = e.toString();
                    res.enviarResponseJson(exchange, responseJson, 200);
                }
            } else if ("PUT".equals(exchange.getRequestMethod())) {
                response = "essa e a rota de Poducts Put";
                RespostaEndPoint.enviarResponseJson(exchange, responseJson, 200);
            } else if ("DELETE".equals(exchange.getRequestMethod())) {
                response = "essa e a rota de Poducts Delete";
                RespostaEndPoint.enviarResponseJson(exchange, responseJson, 200);
            }else if ("OPTIONS".equals(exchange.getRequestMethod())){
                exchange.sendResponseHeaders(200,-1);
                exchange.close();
                return;
            }
            else {
                response = "ERRO" + "o metodo utilizado foi "+ exchange.getRequestMethod();
                RespostaEndPoint.enviarResponseJson(exchange, responseJson, 200);
            }
        }
    }
    public static void doGet(HttpExchange exchange) throws IOException{
        RespostaEndPoint res = new RespostaEndPoint();
        System.out.println("metodo get");

        Sales salesJson = new Sales();

        List<Sales> getAllFromArray = Sales.getAllSales(salesList);
            for (Sales sales : getAllFromArray) {
                System.out.println("Name: " + sales.getName());
                System.out.println("Products: " + sales.getProduts());
                System.out.println("Valor: " + sales.getvalor());
                System.out.println("Finished Sale: " + sales.getFinishedSale());
                System.out.println("Discount: " + sales.getdiscount());
                System.out.println("Date of Sale: " + sales.getdateSale());
                System.out.println();
                System.out.println("--------------------------------------");
            }
            res.enviarResponseJson(exchange, salesJson.arraytoJson(getAllFromArray), 200);
        }

    public static void doPost(HttpExchange exchange)throws IOException{}
    public static void doPut()throws IOException{}
    public static void doDelete()throws IOException{}
    public static void doOptions()throws IOException{}
}
