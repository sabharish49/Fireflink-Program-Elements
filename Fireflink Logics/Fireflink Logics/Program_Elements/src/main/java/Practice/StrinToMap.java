package Practice;

import java.util.HashMap;

public class StrinToMap {
    public static void main(String[] args) {
        String input = "key1=value1,key2=value2,key3=value3, key4=value4,key5=value5, key6=value6";
        String[] pairs = input.split(",");
        HashMap<String, String> map = new HashMap<>();
        for (String pair : pairs) {
                String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                map.put(keyValue[0], keyValue[1]);
            } else {
                System.err.println("Invalid key-value pair: " + pair);
            }
        }
        System.out.println("Map: " + map);
    }
}
