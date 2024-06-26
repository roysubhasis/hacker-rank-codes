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

public class BodyTemperature {

    public static String MEDICAL_RECORDS_API = "https://jsonmock.hackerrank.com/api/medical_records";
    public static void main(String[] args) {
        double[] bodyTemperature = {Integer.MAX_VALUE,Integer.MIN_VALUE};
        String docName = "Dr Arnold Bullock"; //"Dr Pilar Cristancho";//"Dr Allysa Ellis";
        int diagnosisId = 2;
        bodyTemperature = getBodyTemperature(docName,diagnosisId,bodyTemperature,1);
        if (bodyTemperature[0] == Integer.MIN_VALUE && bodyTemperature[1] == Integer.MAX_VALUE){
            System.out.println("Error fetching Body Temperature record...!!!");
        }
        System.out.println("For " + docName + " and diagnosisId " + diagnosisId + " , the Min. Temp is "+bodyTemperature[0] + " & Max. Temp is " + bodyTemperature[1]);
    }
    private static double[] getBodyTemperature(String docName, int diagnosisId, double[] bodyTempArray, int pageNum){

        String bodyTempJsonResp;
        try {
            bodyTempJsonResp = getBodyTemperaturePerPage(MEDICAL_RECORDS_API, pageNum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JsonObject jsonResponse = new Gson().fromJson(bodyTempJsonResp, JsonObject.class);
        int totalPages = jsonResponse.get("total_pages").getAsInt();
        JsonArray jsonArray = jsonResponse.getAsJsonArray("data");
        for(JsonElement jsonElement: jsonArray) {
            //int objectId = jsonElement.getAsJsonObject().get("id").getAsInt();
            JsonObject diagnosisDetails = jsonElement.getAsJsonObject().get("diagnosis").getAsJsonObject();
            JsonObject vitalsDetails = jsonElement.getAsJsonObject().get("vitals").getAsJsonObject();
            JsonObject docDetails = jsonElement.getAsJsonObject().get("doctor").getAsJsonObject();
            double bodyTemperature = vitalsDetails.get("bodyTemperature").getAsDouble();
            if ((docDetails.get("name").getAsString().equals(docName)) &&
                    (diagnosisDetails.get("id").getAsInt()== diagnosisId)) {
                //System.out.println("page -> "+ pageNum + " objectId " + objectId + " doc -> "+ docDetails.get("name").getAsString() + " dig id " + diagnosisDetails.get("id").getAsInt() + " body temp " + bodyTemperature);
                if((bodyTempArray[0] == Integer.MIN_VALUE) && (bodyTempArray[1] == Integer.MAX_VALUE)){
                    bodyTempArray[0] = bodyTemperature;
                    bodyTempArray[1] = bodyTemperature;
                }
                if (bodyTemperature < bodyTempArray[0]) {
                    bodyTempArray[0] = bodyTemperature;
                }
                if (bodyTemperature > bodyTempArray[1]) {
                    bodyTempArray[1] = bodyTemperature;
                }
            }
        }
        return totalPages == pageNum ? bodyTempArray: getBodyTemperature(docName, diagnosisId,bodyTempArray,pageNum+1);
    }

    private static String getBodyTemperaturePerPage(String endpoint, int page) throws IOException {
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
        //System.out.println("resp for page " + page + " --> "+sb.toString());
        return sb.toString();
    }
}