package hrank.practice.restapi.code;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collections;

import java.net.URL;

public class AsteroidMonitoring {

    public static String ASTEROID_API = "https://jsonmock.hackerrank.com/api/asteroids/search";
    public static void main(String[] args) {
        String[] asteroidArr = asteroidMonitor("2011","N"); //asteroidMonitor("2011","Y");
        for(String value: asteroidArr) {
            System.out.println(value);
        }
    }
    public static String[] asteroidMonitor(String year,String pha){
        ArrayList<Asteroid> asteroidList = new ArrayList<>();
        getAsteroidMonitoringStation(year,pha,1,asteroidList);
        Collections.sort(asteroidList);
        return asteroidList.stream().map((asteroid -> asteroid.designation)).toArray(String[]::new);
    }

    public static ArrayList<Asteroid> getAsteroidMonitoringStation(String year, String pha, int page, ArrayList<Asteroid> asteroidList){
        try {
            String  responseJson = getAsteroidMonitoringStationPerPage(ASTEROID_API, year, pha, page);
            JsonObject jsonResponse = new Gson().fromJson(responseJson, JsonObject.class);
            JsonArray jsonArray = jsonResponse.get("data").getAsJsonArray();
            int totalPages = jsonResponse.get("total_pages").getAsInt();
            for (JsonElement jsonElement : jsonArray) {
                String designation = jsonElement.getAsJsonObject().get("designation").getAsString();
                String discoveryDate = jsonElement.getAsJsonObject().get("discovery_date").getAsString();
                String phaFromResp= jsonElement.getAsJsonObject().get("pha").getAsString();
                String periodYear = jsonElement.getAsJsonObject().get("period_yr") == null ? "0.0":  jsonElement.getAsJsonObject().get("period_yr").getAsString();
                Asteroid asteroidObject = new Asteroid(designation, discoveryDate, phaFromResp, periodYear);
                asteroidList.add(asteroidObject);
                //System.out.println("designation : "+ designation + " discoveryDate : "+ discoveryDate + " pha : "+ phaFromResp + " periodYear : "+ periodYear);
            }
            return totalPages == page ? asteroidList: getAsteroidMonitoringStation(year, pha, page+1, asteroidList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*Logic for sorting
     * Sort ascending based on period_yr.
     * If period_yr does not exist for an asteroid, assume its value as 0.
     * In case of a tie, sort on designation, also ascending.
     * */
    static class Asteroid implements Comparable<Asteroid>{
        String designation;
        String discoveryDate;
        String pha;
        String periodYr;

        public Asteroid(String designation, String discoveryDate, String pha, String periodYr) {
            this.designation = designation;
            this.discoveryDate = discoveryDate;
            this.pha = pha;
            this.periodYr = periodYr;
        }
        @Override
        public String toString() {
            return "AsteroidMonitor{" +
                    "designation='" + designation + '\'' +
                    ", discoveryDate='" + discoveryDate + '\'' +
                    ", pha='" + pha + '\'' +
                    ", periodYr='" + periodYr + '\'' +
                    '}';
        }

        @Override
        public int compareTo(Asteroid that) {
            if(Double.parseDouble(this.periodYr) > Double.parseDouble(that.periodYr)) {
                return 1;
            }else if(Double.parseDouble(this.periodYr) == Double.parseDouble(that.periodYr)){
                return this.designation.compareTo(that.designation);
            }else {
                return -1;
            }

        }
    }

    private static String getAsteroidMonitoringStationPerPage(String endpoint, String year, String pha, int page) throws Exception{
        URL url = new URL(endpoint+"?discovery_date="+year+"&pha="+pha+"&page="+page);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.addRequestProperty("Content-Type", "application/json");
        int status = connection.getResponseCode();
        if(status <200 || status >300){
            throw new IOException("error in reading data with the status"+status);
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String response = "";

        if((response = br.readLine())!=null){
            sb.append(response);
        }
        return sb.toString();
    }
}
