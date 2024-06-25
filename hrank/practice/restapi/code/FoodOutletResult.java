package hrank.practice.restapi.code;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Food Outlet Solution Without using Recursion
 * */
public class FoodOutletResult {
    public static ArrayList<String> getFoodOutlets(String city, int maxCost) throws Exception {

        ArrayList<String> listOfFoodOutlets = new ArrayList<>();
        String baseUrl = "https://jsonmock.hackerrank.com/api/food_outlets"+"?city="+ URLEncoder.encode(city, StandardCharsets.UTF_8);
        int page = 1;
        int totalPages = 1;

        while (page <= totalPages) {
            URL url = new URL(baseUrl + "&page=" + page);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();

            JsonObject response=new Gson().fromJson(content.toString(), JsonObject.class);
            totalPages = response.get("total_pages").getAsInt();
            JsonArray data = response.get("data").getAsJsonArray();
            for (JsonElement e: data) {
                String outlet_name = String.valueOf(e.getAsJsonObject().get("name"));
                String estimatedCost = String.valueOf(e.getAsJsonObject().get("estimated_cost"));
                String city1 = e.getAsJsonObject().get("city").getAsString();

                if(city.equalsIgnoreCase(city1) && ( Integer.parseInt(estimatedCost)<=maxCost)){
                    listOfFoodOutlets.add(outlet_name);
                }
            }
            page++;
        }
        return listOfFoodOutlets;
    }

    public static void main(String[] args) throws Exception {

        ArrayList<String> listOfFoodOutlets = new ArrayList<>();
        int page = 1;
        int maxCost = 50;
        listOfFoodOutlets =  getFoodOutlets("Denver",maxCost);
        System.out.println(listOfFoodOutlets);
    }
}
