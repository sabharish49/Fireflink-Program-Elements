package Practice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class json_iterator {
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

        try {
            LinkedHashMap<String, Object> orderedMap = new LinkedHashMap<>();
            // Pattern to match key-value pairs in the JSON string
            Pattern pattern = Pattern.compile("\\\"([^\\\"]+)\\\":(\\[.*?\\]|\\\".*?\\\"),?");

            Matcher matcher = pattern.matcher(jsonStr);
            while (matcher.find()) {
                String key = matcher.group(1);
                String value = matcher.group(2);
                // Handling array values by creating JSONArray objects
                if (value.startsWith("[")) {
                    JSONArray jsonArray = new JSONArray(value);
                    orderedMap.put(key, jsonArray);
                } else {
                    orderedMap.put(key, value);
                }
            }

            for (Map.Entry<String, Object> entry : orderedMap.entrySet()) {
                String key1 = entry.getKey();
                Object value = entry.getValue();
                if (value instanceof JSONArray) {
                    JSONArray arrayValue = (JSONArray) value;
                    System.out.println("Key: " + key1);
                    for (int i = 0; i < arrayValue.length(); i++) {
                    	System.out.println("jsonarray");
                        System.out.println("Value " + (i + 1) + ": " + arrayValue.getString(i));
                    }
                } else {
                	System.out.println("Only json key and value");
                    System.out.println("Key: " + key1 + ", Value: " + value);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

