package Practice;

import java.util.HashMap;
import java.util.Map;

public class StringToMap {
    public static void main(String[] args) {
        String sample = "{MaritalStatus=Married, Weight=60, HeightFT=6, AnnualIncome=5 Lakhs to 10 Lakhs, HeightIN=30}";
        Map<String, String> map = parseStringToMap(sample);
        System.out.println(map);
    }

    public static Map<String, String> parseStringToMap(String str) {
        Map<String, String> map = new HashMap<>();
        str = str.substring(1, str.length() - 1);
        String[] pairs = str.split(", ");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();
            map.put(key, value);
        }
        return map;
    }
}
