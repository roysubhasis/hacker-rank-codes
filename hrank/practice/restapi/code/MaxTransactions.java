package hrank.practice.restapi.code;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;

public class MaxTransactions {

    private static final String URL = "https://jsonmock.hackerrank.com/api/transactions";
    public static void main(String[] args) throws Exception{
        MaxTransactions mxTransaction = new MaxTransactions();
        double totalTransactions = 0.00;
        DecimalFormat df = new DecimalFormat("#.00");
        int page = 1;
        System.out.println("Total transactions : "+df.format(mxTransaction.getMaxTransaction("Bob Martin", "Ilchester",page, totalTransactions)));
    }
    private double getMaxTransaction(String name, String city, int page, double totalTransactions) throws Exception {

        String urlForTransaction = String.format(URL+"?name="+URLEncoder.encode(name, StandardCharsets.UTF_8)+"&city="+city);
        String response = getResponsePerPage(urlForTransaction, page);
        JsonObject jsonResponse = new Gson().fromJson(response, JsonObject.class);
        int totalPages = jsonResponse.get("total_pages").getAsInt();
        JsonArray data = jsonResponse.getAsJsonArray("data");

        String amount;
        for (JsonElement e : data) {
            String userName = e.getAsJsonObject().get("userName").getAsString();

            JsonObject innerObject = e.getAsJsonObject();
            JsonObject locationObject = innerObject.getAsJsonObject("location");
            String userCity = locationObject.get("city").getAsString();
            if(name.equalsIgnoreCase(userName) && city.equalsIgnoreCase(userCity)){
                String userAmount = e.getAsJsonObject().get("amount").getAsString();
                //System.out.println("userName : "+userName + " and userCity : "+userCity + " and userAmount : "+userAmount);
                amount = getModifiedAmount(userAmount);
                totalTransactions = getSum(amount,totalTransactions);
            }
        }
        return totalPages==page? totalTransactions : getMaxTransaction(name, city, page+1, totalTransactions);
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

    private static String getModifiedAmount(String amount){
        return amount.replace("$", "")
                .replace(",", "");
    }

    private double getSum(String amount, double totalTransactions ){
        double tempValue = Double.parseDouble(amount);;
        return tempValue+totalTransactions;
    }
}
