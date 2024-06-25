package hrank.practice.restapi.code;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class PatientMedicalRecord {

    public static String MEDICAL_RECORDS_API = "https://jsonmock.hackerrank.com/api/medical_records";
    public static void main(String[] args) {
        double avgBodyTemperature = 0.0;
        int userId = 3;
        System.out.println(getAverageTemperatureForUser(userId,avgBodyTemperature,1));

    }
    private static double getAverageTemperatureForUser(int userId, double avgBodyTemperature, int pageNum){

        String medicalRecordsJsonResp;
        try {
            String URL = String.format(MEDICAL_RECORDS_API+"?userId="+userId);
            medicalRecordsJsonResp = getMedicalRecordsPerPage(URL, pageNum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JsonObject jsonResponse = new Gson().fromJson(medicalRecordsJsonResp, JsonObject.class);
        int totalPages = jsonResponse.get("total_pages").getAsInt();
        JsonArray jsonArray = jsonResponse.getAsJsonArray("data");
        System.out.println("jsonArray size "+ jsonArray.size());
        System.out.println("totalPages  "+ totalPages);
        if(jsonArray.isEmpty()){
            return 0;
        }
        for(JsonElement jsonElement: jsonArray) {
            JsonObject vitalsDetails = jsonElement.getAsJsonObject().get("vitals").getAsJsonObject();
            double bodyTemperature = vitalsDetails.get("bodyTemperature").getAsDouble();
            int userIdFromResp = jsonElement.getAsJsonObject().get("userId").getAsInt();
            //have to write logic for average the body temperature...YET TO COMPLETE
            //
            //

        }
        return totalPages == pageNum ? avgBodyTemperature : getAverageTemperatureForUser(userId, avgBodyTemperature,pageNum+1);
    }

    private static String getMedicalRecordsPerPage(String endpoint, int page) throws IOException {
        URL url = new URL(endpoint+"&page="+page);
        System.out.println("endpoint ->"+url.toString());
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
        System.out.println("resp for page " + page + " --> "+sb.toString());
        return sb.toString();
    }

}
