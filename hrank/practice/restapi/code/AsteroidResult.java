package hrank.practice.restapi.code;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Asteroid {
    String designation;
    String discovery_date;
    String period_yr;
    String pha;

    Asteroid(String designation, String discovery_date, String period_yr, String pha) {
        this.designation = designation;
        this.discovery_date = discovery_date;
        this.period_yr = period_yr;
        this.pha = pha;
    }
}

class AsteroidMonitor {

    public static List<Asteroid> getAllAsteroids() throws Exception {
        List<Asteroid> asteroids = new ArrayList<>();
        String baseUrl = "https://jsonmock.hackerrank.com/api/asteroids";
        int page = 1;
        int totalPages = 1;

        while (page <= totalPages) {
            URL url = new URL(baseUrl + "?page=" + page);
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

            JsonObject response = JsonParser.parseString(content.toString()).getAsJsonObject();
            totalPages = response.get("total_pages").getAsInt();
            JsonArray data = response.get("data").getAsJsonArray();

            for (JsonElement e: data) {

                String designation = e.getAsJsonObject().get("designation").getAsString();
                String discovery_date = e.getAsJsonObject().get("discovery_date").getAsString();;
                String period_yr = e.getAsJsonObject().get("period_yr")==null?"0": e.getAsJsonObject().get("period_yr").getAsString() ;
                String pha = e.getAsJsonObject().get("pha").getAsString();
                asteroids.add(new Asteroid(designation, discovery_date, period_yr, pha));
            }
            page++;
        }
        return asteroids;
    }

    public static List<String> asteroidMonitor(int year, String pha) throws Exception {
        List<Asteroid> asteroids = getAllAsteroids();
        List<Asteroid> filteredAsteroids = new ArrayList<>();

        for (Asteroid asteroid : asteroids) {
            int discoveryYear = Integer.parseInt(asteroid.discovery_date.substring(0, 4));
            if (discoveryYear == year && asteroid.pha.equals(pha)) {
                filteredAsteroids.add(asteroid);
            }
        }

        Collections.sort(filteredAsteroids, new Comparator<Asteroid>() {
            @Override
            public int compare(Asteroid a1, Asteroid a2) {
                double periodYr1 = a1.period_yr.isEmpty() ? 0.0 : Double.parseDouble(a1.period_yr);
                double periodYr2 = a2.period_yr.isEmpty() ? 0.0 : Double.parseDouble(a2.period_yr);

                if (periodYr1 != periodYr2) {
                    return Double.compare(periodYr1, periodYr2);
                }
                return a1.designation.compareTo(a2.designation);
            }
        });

        List<String> result = new ArrayList<>();
        for (Asteroid asteroid : filteredAsteroids) {
            result.add(asteroid.designation);
        }

        return result;
    }

}

/**
 * Asteroid Monitor Solution Without using Recursion
 * */
class AsteriodResult {

    public static void main(String[] args) throws Exception {
        int year = 2011;
        String pha = "Y";

        List<String> asteroidDesignations = AsteroidMonitor.asteroidMonitor(year, pha);
        for (String designation : asteroidDesignations) {
            System.out.println(designation);
        }
    }
}