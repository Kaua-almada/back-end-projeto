package Domain;

import org.json.JSONObject;

import java.util.List;

public class Sales {
    public int id = 0;
    public String name = " ";
    public String produts = " ";
    public String valor =  " ";
    public boolean finishedSale = false;
    public Double discount = 0.0;
    public String dateSale = "";

    public Sales(){

    }
    public Sales(String user, String produts, String valor,boolean finishedSale, double discount, String dateSale){

        this.name = user;
        this.produts = produts;
        this.valor = valor;
        this.finishedSale = finishedSale;
        this.discount = discount;
        this.dateSale = dateSale;
    }
    //getters and setters
    public String getName(){
        return name;
    }
    public void setName(String user){
        this.name = user;
    }
    public String getProduts(){
        return produts;
    }
    public void setproduts(String produts){
        this.produts = produts;
    }
    public String getvalor(){
        return valor;
    }
    public void setValor(String valor){
        this.valor = valor;
    }
    public boolean getFinishedSale(){
        return finishedSale;
    }
    public void setfinishedSale(boolean finishedSale){
        this.finishedSale = finishedSale;
    }
    public Double getdiscount(){
        return discount;
    }
    public void setdiscount(Double discount){
        this.discount = discount;
    }
    public String getdateSale(){
        return dateSale;
    }
    public void setdateSale(Double discount){
        this.dateSale = dateSale;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("produts", produts);
        json.put("valor", valor);
        json.put("finishedSale", finishedSale);
        json.put("discount", discount);
        json.put("dateSale", dateSale);
        return json;
    }

    public static List<Sales> getAllSales(List<Sales> salesList){return salesList;}
    public JSONObject arraytoJson(List<Sales> salesListArray) {
            JSONObject json = new JSONObject();

            if (!salesListArray.isEmpty()) {
                var keyJson = 0;
                for (Sales sales : salesListArray) {
                    JSONObject valorJson = new JSONObject();
                    valorJson.put("name", sales.getName());
                    valorJson.put("produts", sales.getProduts());
                    valorJson.put("valor", sales.getvalor());
                    valorJson.put("finishedSale", sales.getFinishedSale());
                    valorJson.put("discount", sales.getdiscount());
                    valorJson.put("dateSale", sales.getdateSale());
                    json.put(String.valueOf(keyJson), valorJson);

                    keyJson++;
                }

                return json;
            }else {
                return null;
            }
    }
    public static Sales getSales(int index, List<Sales> salesList) {
        if (index >= 0 && index < salesList.size()) {
            return salesList.get(index);
        }else {
            return null;
        }
    }
}

