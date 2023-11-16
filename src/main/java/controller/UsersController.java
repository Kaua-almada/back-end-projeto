package controller;
import DAL.UserDal;
import Services.ConexaoSqlServer;
import Services.RespostaEndPoint;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UsersController {

    public static class Usuarios implements HttpHandler {
        RespostaEndPoint res = new RespostaEndPoint();
        List<Domain.Usuarios> usersList = new ArrayList<>();
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "";


            JSONObject responseJson = new JSONObject();
            if ("GET".equals(exchange.getRequestMethod())) {
                UserDal userDal = new UserDal();

                List<Domain.Usuarios> getAllFromArray = Domain.Usuarios.getAllUser(usersList);
                Domain.Usuarios usersJson = new Domain.Usuarios();

                if(!getAllFromArray.isEmpty()){

                    for(Domain.Usuarios users : getAllFromArray){
                    System.out.println("name: " + users.getName());
                    System.out.println("last name: " + users.getlastLastname ());
                    System.out.println("email: " + users.getemail());
                    System.out.println("passeword: " + users.getpasseword());
                    System.out.println("cpf: " + users.getcpf());
                    System.out.println();
                    System.out.println("-------------------------");
                    System.out.println();
                    }
                    res.enviarResponseJson(exchange, usersJson.arraytoJson(getAllFromArray), 200);
                }
                    //try catch aqui dps

                else{
                    System.out.println("erroooo");
                }

                try {
                    userDal.listarUsuario();
                }catch (Exception e){
                    System.out.println("o erro foi:" + e);
                }
            } else if ("POST".equals(exchange.getRequestMethod())) {
                UserDal userDal = new UserDal();

                try (InputStream resquestBody = exchange.getRequestBody()){

                    JSONObject json = new JSONObject(new String(resquestBody.readAllBytes()));
                    int resp = 0;

                    Domain.Usuarios user = new Domain.Usuarios(
                            json.getString("name"),
                            json.getString("lastName"),
                            json.getString("email"),
                            json.getString("passeword"),
                            json.getString("cpf")
                    );

                    usersList.add(user);

                    resp = userDal.inserirUsuarios(user.name, user.lastName, user.email, user.passeword, user.cpf);

                    System.out.println("usersList" + user.toJson());

                    if(resp == 0){
                        response = "houve um problema ao criar o usuarios";
                    }else{
                        response = "Usuario criado com sucesso";
                    }

                    res.enviarResponseJson(exchange, user.toJson(), 200);


                }catch(Exception e){

                    response = e.toString();
                    System.out.println(response);
                    String resposta  = e.toString();
                    res.enviarResponseJson(exchange, responseJson, 200);
//
                }
            } else if ("PUT".equals(exchange.getRequestMethod())) {

                UserDal userDal = new UserDal();
                try {
                    userDal.atualizarUsuario();
                }catch (Exception e){
                    System.out.println("o erro foi:" + e);
                }

                response = "essa e a rota de Poducts Put";
                res.enviarResponse(exchange, response, 200);

            } else if ("DELETE".equals(exchange.getRequestMethod())) {
                UserDal userDal = new UserDal();

                try {
                    userDal.excluirUsuario();
                }catch (Exception e){
                    System.out.println("o erro foi:" + e);
                }

                response = "essa e a rota de Poducts Delete";
                res.enviarResponse(exchange, response, 200);

            }else if ("OPTIONS".equals(exchange.getRequestMethod())){

                exchange.sendResponseHeaders(200,-1);
                exchange.close();
                return;

            }
            else {

                response = "ERRO" + "o metodo utilizado foi "+ exchange.getRequestMethod();
                res.enviarResponse(exchange, response, 405);

            }
        }
    }
}
