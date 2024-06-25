package hrank.practice.restapi.code;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class FoodOutlet {
    private static final String OUTLET_URL = "https://jsonmock.hackerrank.com/api/food_outlets";

    public static void main(String[] args) throws Exception {
        FoodOutlet foodOutletObj = new FoodOutlet();
        ArrayList<String> listOfFoodOutlets = new ArrayList<>();

        int page = 1;
        int maxCost = 50;
        String city = "Denver";
        listOfFoodOutlets = foodOutletObj.getFoodOutletNames(city,maxCost ,1,listOfFoodOutlets);
        System.out.println("listOfFoodOutlets in " + city + " -> "+listOfFoodOutlets);
    }

    private ArrayList<String> getFoodOutletNames(String city, int maxCost, int page, ArrayList<String> listOfFoodOutlets){

        String urlForTransaction = String.format(OUTLET_URL+"?city="+city);
        String response = null;
        try {
            response = getResponsePerPage(urlForTransaction, page);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JsonObject jsonResponse = new Gson().fromJson(response, JsonObject.class);
        int totalPages = jsonResponse.get("total_pages").getAsInt();
        JsonArray data = jsonResponse.getAsJsonArray("data");
        String amount = "";
        for (JsonElement e : data) {
            String outlet_name = String.valueOf(e.getAsJsonObject().get("name"));
            String estimatedCost = String.valueOf(e.getAsJsonObject().get("estimated_cost"));
            String city1 = e.getAsJsonObject().get("city").getAsString();

            if(city.equalsIgnoreCase(city1) && ( Integer.parseInt(estimatedCost)<=maxCost)){
                listOfFoodOutlets.add(outlet_name);
            }
        }
        return totalPages==page? listOfFoodOutlets : getFoodOutletNames(city, maxCost, page+1, listOfFoodOutlets);
    }

    private String getResponsePerPage(String endpoint, int page) throws MalformedURLException, IOException, ProtocolException {

        URL url = new URL(endpoint+"&page="+page);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.addRequestProperty("Content-Type", "application/json");

        int status = con.getResponseCode();
        if(status<200 || status>=300) {
            throw new IOException("Error in reading data with status:"+status);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String response;
        StringBuilder sb = new StringBuilder();
        while((response = br.readLine())!=null) {
            sb.append(response);
        }

        br.close();
        con.disconnect();
        return sb.toString();
    }
}
