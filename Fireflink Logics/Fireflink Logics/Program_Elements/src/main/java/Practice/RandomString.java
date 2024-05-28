package Practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RandomString {
    public static void main(String[] args) {
    	String json = "{\r\n"
    	        + "        \"id\": \"RAN_NUM>>3\",\r\n"
    	        + "        \"title\": \"thanks\",\r\n"
    	        + "        \"componentId\": 1,\r\n"
    	        + "        \"bugId\": \"fgfg\",\r\n"
    	        + "        \"items\": [\r\n"
    	        + "            {\r\n"
    	        + "                \"id\": \"RAN_STR>>3\",\r\n"
    	        + "                \"datasource\": \"eks-dev\",\r\n"
    	        + "                \"databases\": \"vault\",\r\n"
    	        + "                \"query\": \"UPDATE slab_task_detail SET net_sale_amount = 200.5 , discount_percent = 10, updated_on = now() WHERE slab_task_id = 12 AND client = 'B2C' AND partner_id = '1235';\\n\",\r\n"
    	        + "                \"params\": {}\r\n"
    	        + "            }\r\n"
    	        + "        ],\r\n"
    	        + "        \"status\": \"CREATED\",\r\n"
    	        + "        \"runAt\": null,\r\n"
    	        + "        \"requesterId\": \"b2fb06b9-c528-4b01-8dc2-8baa1e94bf15\",\r\n"
    	        + "        \"requesterName\": \"\",\r\n"
    	        + "        \"approverId\": \"e7fa800d-93cb-4d26-b12b-5d79dc228d3a\",\r\n"
    	        + "        \"approverName\": \"Abuzar\",\r\n"
    	        + "        \"approvedOn\": null,\r\n"
    	        + "        \"createdOn\": \"2024-02-28T13:08:21.828374\",\r\n"
    	        + "        \"templateId\": null,\r\n"
    	        + "        \"autoApproved\": false,\r\n"
    	        + "        \"maxRowsImpact\": null\r\n"
    	        + "    }";


        System.out.println(json);
        String mapData = "{Org_Id=TC001, PAN_Value=abcd, UpdatedJason=sample, compCode=158552}";
        String[] keyValuePairs = mapData.substring(1, mapData.length() - 1).split(", ");
        Map<String, String> dataMap = new HashMap<>();
        for (String pair : keyValuePairs) {
            String[] entry = pair.split("=");
            dataMap.put(entry[0], entry[1]);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, Object> map = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {});
            map = replacePlaceholdersWithRandomStrings(map, dataMap);
            String json1 = objectMapper.writeValueAsString(map);
            System.out.println(json1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> replacePlaceholdersWithRandomStrings(Map<String, Object> jsonMap, Map<String, String> comMap) {
        for (Map.Entry<String, Object> mapData : jsonMap.entrySet()) {
            String key = mapData.getKey();
            Object value = mapData.getValue();
            if (value instanceof String) {
                String stringValue = (String) value;
                if (stringValue.startsWith("EXCEL>>")) {
                    stringValue = stringValue.replace("EXCEL>>", "");
                    stringValue = getCellConvertData(comMap.get(stringValue));
                    jsonMap.put(key, getConvertData(stringValue));
                } else if (stringValue.startsWith("RAN_NUM>>")) {
                    // If the value is a placeholder for a random number
                    stringValue = stringValue.replace("RAN_NUM>>", "");
                    int length = Integer.parseInt(stringValue);
                    jsonMap.put(key, generateRandomNumber(length));
                } else {
                    jsonMap.put(key, getConvertData(stringValue));
                }
            } else if (value instanceof List) {
                // If the value is a list, handle each item in the list
                List<Object> itemList = (List<Object>) value;
                List<Object> updatedItemList = new ArrayList<>();
                for (Object item : itemList) {
                    if (item instanceof Map) {
                        Map<String, Object> updatedItem = replacePlaceholdersWithRandomStrings((Map<String, Object>) item, comMap);
                        updatedItemList.add(updatedItem);
                    } else {
                        updatedItemList.add(item);
                    }
                }
                jsonMap.put(key, updatedItemList);
            }
        }
        return jsonMap;
    }


    private static Object generateRandomNumber(int length) {
    	if(length==10) {
 		   Random random = new Random();
 			int randomNumber = 100000000 + random.nextInt(900000000);
 	        String num = "9" + randomNumber;
                 return num;
 	   }
 	   else {
 		   String Numbers="123456789";
 		   StringBuilder s = new StringBuilder(length);
 		   for (int i=0; i<length; i++) {
 			   int ch = (int)(Numbers.length() * Math.random());
 			   s.append(Numbers.charAt(ch));
 			  }
 		   return s.toString();
 	   }
	}
	private static String getConvertData(String value) {
        String modifiedData = value;
        if (value.startsWith("RAN_NUM>>")) {
            int startIndex = "RAN_NUM>>".length();
            int length = Integer.parseInt(value.substring(startIndex));
            modifiedData = generateRandomString(length);
        } else if (value.startsWith("RAN_STR>>")) {
            int startIndex = "RAN_STR>>".length();
            int length = Integer.parseInt(value.substring(startIndex));
            modifiedData = generateFixedString(length);
        } else if (value.startsWith("RAN_ALPNUM>>")) {
            int startIndex = "RAN_ALPNUM>>".length();
            int length = Integer.parseInt(value.substring(startIndex));
            modifiedData = generateAlphaNumericString(length);
        } else if (value.startsWith("RAN_MAIL>>")) {
            int startIndex = "RAN_MAIL>>".length();
            int length = Integer.parseInt(value.substring(startIndex));
            modifiedData = generateRandomMail(length);
        }
        return modifiedData;
    }

    private static String getCellConvertData(String value) {
        String modifiedData = value;
        if (value.contains("RAN_NUM>>")) {
            int index = value.indexOf("RAN_NUM>>");
            modifiedData = getConvertData(value.substring(0, index)) + getConvertData(value.substring(index));
        } else if (value.contains("RAN_STR>>")) {
            int index = value.indexOf("RAN_STR>>");
            modifiedData = getConvertData(value.substring(0, index)) + getConvertData(value.substring(index));
        } else if (value.contains("RAN_ALPNUM>>")) {
            int index = value.indexOf("RAN_ALPNUM>>");
            modifiedData = getConvertData(value.substring(0, index)) + getConvertData(value.substring(index));
        } else if (value.contains("RAN_MAIL>>")) {
            int index = value.indexOf("RAN_MAIL>>");
            modifiedData = getConvertData(value.substring(0, index)) + getConvertData(value.substring(index));
        }
        return modifiedData;
    }

    public static String generateRandomString(int length) {
        if (length == 10) {
            Random random = new Random();
            int randomNumber = 100000000 + random.nextInt(900000000);
            return "9" + randomNumber;
        } else {
            String Numbers = "123456789";
            StringBuilder s = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                int ch = (int) (Numbers.length() * Math.random());
                s.append(Numbers.charAt(ch));
            }
            return s.toString();
        }
    }

    public static String generateFixedString(int length) {
        String Alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz";
        StringBuilder s = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int ch = (int) (Alphabets.length() * Math.random());
            s.append(Alphabets.charAt(ch));
        }
        return s.toString();
    }

    public static String generateAlphaNumericString(int length) {
        String AlphNumneric = "abcdefghijklmnopqrstuvxyz123456789";
        StringBuilder s = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int ch = (int) (AlphNumneric.length() * Math.random());
            s.append(AlphNumneric.charAt(ch));
        }
        return s.toString();
    }

    public static String generateRandomMail(int length) {
        String alphebets = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz";
        String Numbers = "123456789";
        String Mail = RandomStringUtils.random(length, alphebets)
                + RandomStringUtils.random(length / 2, Numbers)
                + "@gmail.com";
        return Mail;
    }
}
