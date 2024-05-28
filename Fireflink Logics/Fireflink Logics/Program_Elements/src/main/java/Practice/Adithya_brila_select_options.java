package Practice;

import com.google.gson.*;

public class Adithya_brila_select_options {
    public static void main(String[] args) {
        String jsonStr = "{\r\n"
                + "\"Disease name\":\"Disease\",\r\n"
                + "\"Date of Diagnosis\":\"250324\",\r\n"
                + "\"Last Consultation Date\":\"250324\",\r\n"
                + "\"Name of Surgery\":\"Surgery\",\r\n"
                + "\"Details of Treatment\":\"Nothing\",\r\n"
                + "\"Disability %\":\"30\",\r\n"
                + "\"Period of hospitalization\":\"2\",\r\n"
                + "\"Any Other information\":\"Nothing\"\r\n"
                + "\r\n"
                + "}";

        JsonObject jsonObject = new Gson().fromJson(jsonStr, JsonObject.class);

        for (String key : jsonObject.keySet()) {
            JsonElement value = jsonObject.get(key);
            System.out.println("Key: " + key + ", Value: " + value);
        }
    }
}
