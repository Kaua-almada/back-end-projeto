package Domain;

import org.json.JSONObject;

import java.util.List;

public class Products {
    int id = 0;
    public String name = "";
    public String factory = "";
    public int quantity = 0;
    //contrutor
    public void Users(){
    }
    public Products(String name, String factory, int quantity){
        this.name = name;
        this.factory = factory;
        this.quantity = quantity;
    }
    public Products(){

    }

    //getters and setters
    public  String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getFactory(){
        return factory;
    }
    public void setFactory(String factory){
        this.factory = factory;
    }
    public int getQuantity(){return quantity;}
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("factory", factory);
        json.put("quantity", quantity);
        return json;
    }

    public static Products getProducts(int index, List<Products> productsList) {

        if (index >= 0 && index < productsList.size()) {
            return productsList.get(index);
        } else {
            return null;
        }

    }
    public static List<Products> getAllProducts(List<Products> productsList) {

        return productsList;
    }

    public JSONObject arraytoJson(List<Products> usersListArray) {
        JSONObject json = new JSONObject();

        if (!usersListArray.isEmpty()) {
            var keyJson = 0;
            for (Products products : usersListArray) {
                JSONObject valorJson = new JSONObject();
                valorJson.put("name", products.getName());
                valorJson.put("factory", products.getFactory());
                valorJson.put("quantity", products.getQuantity());

                json.put(String.valueOf(keyJson), valorJson);

                keyJson++;
            }

            return json;
        }else {
            return null;
        }

    }
}



