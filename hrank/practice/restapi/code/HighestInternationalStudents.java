package hrank.practice.restapi.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class HighestInternationalStudents {

    public static String UNIVERSITIES_API="https://jsonmock.hackerrank.com/api/universities";

    public static void main(String[] args) {
        String firstCity = "Singapore";
        String secondCity = "New York City";
        System.out.println(highestInternationalStudents(firstCity, secondCity));
    }

    public static String highestInternationalStudents(String firstCity, String secondCity){
        int highestStudentsInCity1 = Integer.MIN_VALUE;
        int highestStudentsInCity2 = Integer.MIN_VALUE;
        String university1 = "";
        String university2 = "";
        return universityWithHighestInternationStudent(firstCity,secondCity,1,highestStudentsInCity1,
                highestStudentsInCity2, university1, university2);
    }

    private static String universityWithHighestInternationStudent(String firstCity, String secondCity, int page,
                                               int highestStudentsInFirstCity,int highestStudentsInSecondCity, String university1,String university2) {
        String internationStudentJsonResp;
        try {
            internationStudentJsonResp = getInternationStudentPerPage(UNIVERSITIES_API, page);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        JsonObject jsonResponse = new Gson().fromJson(internationStudentJsonResp,JsonObject.class);
            JsonArray jsonArray = jsonResponse.get("data").getAsJsonArray();
            int totalPages = jsonResponse.get("total_pages").getAsInt();
            for(JsonElement jsonElement: jsonArray) {
                String universityFromResponse = jsonElement.getAsJsonObject().get("university").getAsString();
                int internationalStudentsCount = Integer.parseInt(jsonElement.getAsJsonObject().get("international_students").getAsString().replaceAll(",",""));;
                JsonObject locationJsonObject = jsonElement.getAsJsonObject().get("location").getAsJsonObject();
                String city = locationJsonObject.get("city").getAsString();
                if (city.equalsIgnoreCase(firstCity) &&
                        (universityFromResponse!= null && !universityFromResponse.equalsIgnoreCase(""))){
                    if (internationalStudentsCount > highestStudentsInFirstCity) {
                        highestStudentsInFirstCity = internationalStudentsCount;
                        university1 = universityFromResponse;
                    }
                } else if (city.equalsIgnoreCase(secondCity) &&
                        (universityFromResponse!= null && !universityFromResponse.equalsIgnoreCase(""))){
                    if (internationalStudentsCount > highestStudentsInSecondCity) {
                        highestStudentsInSecondCity = internationalStudentsCount;
                        university2 = universityFromResponse;
                    }
                }
            }
            return totalPages == page ? (university1 != null ? university1: university2): universityWithHighestInternationStudent(firstCity, secondCity, page+1,
                    highestStudentsInFirstCity,highestStudentsInSecondCity, university1,university2);
    }

    private static String getInternationStudentPerPage(String endpoint, int page) throws Exception{
        URL url = new URL(endpoint+"?page="+page);
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
