package Practice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

public class json {
    public static void main(String[] args) {
        String json = "{ \"orgId\": \"Org_Id\",\r\n"
        		+ "    \"businessType\": \"PRIVATE\",\r\n"
        		+ "    \"issuerCode\": \"YESGIFTCARD\",\r\n"
        		+ "    \"issuerAccountNumber\": \"1111111111\",\r\n"
        		+ "    \"issuerAccountIFSC\": \"SBIN0054532\",\r\n"
        		+ "    \"clientId\":\"C91\",\r\n"
        		+ "    \"physicalGiftCardBusinessName\":\"OrgVC\",\r\n"
        		+ "    \"virtualGiftCardBusinessName\":\"OrgVC\",\r\n"
        		+ "    \"documents\": [\r\n"
        		+ "        {\r\n"
        		+ "            \"docType\": \"PAN\",\r\n"
        		+ "            \"docValue\": \"PAN_Value\",\r\n"
        		+ "            \"docImage\": \"https://card91-stag-docs.s3.ap-south-1.amazonaws.com/SANDBOX/220502104700156ID1FILE5278200.jpeg\",\r\n"
        		+ "            \"countryISOCode\": \"IND\"\r\n"
        		+ "        },\r\n"
        		+ "        {\r\n"
        		+ "            \"docType\": \"GST_CERT\",\r\n"
        		+ "            \"docValue\": \"GST_CERT_Value\",\r\n"
        		+ "            \"docImage\": \"https://card91-stag-docs.s3.ap-south-1.amazonaws.com/SANDBOX/220502104700156ID1FILE5278200.jpeg\",\r\n"
        		+ "            \"countryISOCode\": \"IND\"\r\n"
        		+ "        },\r\n"
        		+ "        {\r\n"
        		+ "            \"docType\": \"TAN\",\r\n"
        		+ "            \"docValue\": \"TAN_Value\",\r\n"
        		+ "            \"docImage\": \"https://card91-stag-docs.s3.ap-south-1.amazonaws.com/SANDBOX/220502104700156ID1FILE5278200.jpeg\",\r\n"
        		+ "            \"countryISOCode\": \"IND\"\r\n"
        		+ "        }\r\n"
        		+ "    ],\r\n"
        		+ "    \"addresses\": [\r\n"
        		+ "        {\r\n"
        		+ "            \"addressType\": \"REGISTERED_ADDRESS\",\r\n"
        		+ "            \"address1\": \"Ayyakanna Street\",\r\n"
        		+ "            \"address2\": \"Newpet\",\r\n"
        		+ "            \"city\": \"Palamaner\",\r\n"
        		+ "            \"countryISOCode\": \"IND\",\r\n"
        		+ "            \"pincode\": \"517408\",\r\n"
        		+ "            \"state\": \"AndhraPradesh\"\r\n"
        		+ "        }\r\n"
        		+ "    ]\r\n"
        		+ "}";

        String data = "{Org_Id=TC001, PAN_Value=abcd, UpdatedJason=sample, compCode=158552}";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> jsonMap = objectMapper.readValue(json, Map.class);
            String[] keyValuePairs = data.substring(1, data.length() - 1).split(", ");
            Map<String, String> comMap = new HashMap();
            for (String pair : keyValuePairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    comMap.put(keyValue[0], keyValue[1]);
                }
            }
            jsonMap = replacePlaceholdersWithValues(jsonMap, comMap);
            String updatedJson = objectMapper.writeValueAsString(jsonMap);
            System.out.println(updatedJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> replacePlaceholdersWithValues(Map<String, Object> jsonMap, Map<String, String> comMap) {
        for (Map.Entry<String, Object> mapData : jsonMap.entrySet()) {
            String key = mapData.getKey();
            Object value = mapData.getValue();
            if (value instanceof String) {
                String strValue = (String) value;
                for (Map.Entry<String, String> staticData : comMap.entrySet()) {
                    String staticKey = staticData.getKey();
                    String staticValue = staticData.getValue();
                    strValue = strValue.replace(staticKey, staticValue);
                }
                jsonMap.put(key, strValue);
            } else if (value instanceof List) {
                List<Object> listValue = (List<Object>) value;
                List<Object> updatedList = new ArrayList();
                for (Object listItem : listValue) {
                    if (listItem instanceof Map) {                 
                        updatedList.add(replacePlaceholdersWithValues((Map<String, Object>) listItem, comMap));
                    } else {
                        updatedList.add(listItem);
                    }
                }
                jsonMap.put(key, updatedList);
            }
        }
        
        return jsonMap;
    }

}
