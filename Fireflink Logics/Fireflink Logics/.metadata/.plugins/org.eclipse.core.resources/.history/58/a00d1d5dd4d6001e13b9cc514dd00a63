package Business_logics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;


import org.springframework.stereotype.Component;

@Component("LIC14952_PJT1005_PE_NLPc6137127-7d46-4c8b-9523-2dc687cc7335")
public class Random_Json_value implements Nlp {
	    @InputParams({@InputParam(name = "Json Body", type = "java.lang.String"), @InputParam(name = "MapData", type = "java.util.Map")})
	    @ReturnType(name = "UpdatedJson", type = "java.lang.String")	    
	      @Override
	      public List<String> getTestParameters() throws NlpException {
	        List<String> params = new ArrayList<>();
	        return params;
	      }	    
	      @Override
	      public StringBuilder getTestCode() throws NlpException {
	        StringBuilder sb = new StringBuilder();
	        return sb;
	      }
	      
	      @Override
	      public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {	        
	          NlpResponseModel nlpResponseModel = new NlpResponseModel();
	          Map<String, Object> attributes = nlpRequestModel.getAttributes();
	          String json = (String) attributes.get("Json Body");
				 @SuppressWarnings("unchecked")
				Map<String, String> MapData=(Map<String, String>) attributes.get("MapData");
	          ObjectMapper objectMapper = new ObjectMapper();
	          String json1="";   
	          try {  
	        	  @SuppressWarnings("unchecked")
					Map<String, Object> map = objectMapper.readValue(json, Map.class);
		             map = replacePlaceholdersWithRandomStrings(map,MapData);
		             json1 = objectMapper.writeValueAsString(map);
		             nlpResponseModel.setStatus(CommonConstants.pass);
		        	 nlpResponseModel.setMessage("Successfully Generated the request body");
	          }          

	          catch(Exception e)
	          {
	        	  nlpResponseModel.setStatus(CommonConstants.fail);
	        	  nlpResponseModel.setMessage("Element Not found"+e);
	          }	          
	          nlpResponseModel.getAttributes().put("UpdatedJson", json1);
	          return nlpResponseModel;
	      }
	      
	      public static Map<String, Object> replacePlaceholdersWithRandomStrings(Map<String, Object> map, Map<String, String> comMap) {
	          for (Entry<String, Object> mapData : map.entrySet()) {
	              String key = mapData.getKey();
	              Object value = mapData.getValue();
	              if (value instanceof String) {
	                  String stringValue = (String) value;
	                  if (stringValue.startsWith("EXCEL>>")) {
	                      stringValue = stringValue.replace("EXCEL>>", "");
	                      stringValue = getCellConvertData(comMap.get(stringValue));
	                      map.put(key, getConvertData(stringValue));
	                  } else if (stringValue.startsWith("RAN_NUM>>")) {
	                      // If the value is a placeholder for a random number
	                      stringValue = stringValue.replace("RAN_NUM>>", "");
	                      int length = Integer.parseInt(stringValue);
	                      map.put(key, generateRandomString(length));
	                  } else {
	                      map.put(key, getConvertData(stringValue));
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
	                  map.put(key, updatedItemList);
	              }
	          }
	          return map;
	      }
	      
	    private static String getConvertData(String value){
		String modifiedData=value;
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
              }
              else if (value.startsWith("RAN_MAIL>>")) {
                  int startIndex = "RAN_MAIL>>".length();
                  int length = Integer.parseInt(value.substring(startIndex));
                  modifiedData = generateRandomMail(length);
              }
	    	  return modifiedData;
	      }
	
	private static String getCellConvertData(String value){
		String modifiedData=value;
	    	  if (value.contains("RAN_NUM>>")) {
	    		  int index = value.indexOf("RAN_NUM>>");
	    		  modifiedData =value.substring(0,index)+getConvertData(value.substring(index));
              } else if (value.contains("RAN_STR>>")) {
            	  int index = value.indexOf("RAN_STR>>");
	    		  modifiedData =value.substring(0,index)+getConvertData(value.substring(index));
              } else if (value.contains("RAN_ALPNUM>>")) {
            	  int index = value.indexOf("RAN_ALPNUM>>");
	    		  modifiedData =value.substring(0,index)+getConvertData(value.substring(index));
              }
              else if (value.contains("RAN_MAIL>>")) {
            	  int index = value.indexOf("RAN_MAIL>>");
	    		  modifiedData =value.substring(0,index)+getConvertData(value.substring(index));
              }
	    	  return modifiedData;
	      }
	
	      
public static String generateRandomString(int length) {
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

public static String generateFixedString(int length) {
	String Alphabets="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz";
	   StringBuilder s = new StringBuilder(length);
	   for (int i=0; i<length; i++) {
		   int ch = (int)(Alphabets.length() * Math.random());
		   s.append(Alphabets.charAt(ch));
		  }
		    return s.toString();
}

public static String generateAlphaNumericString(int length) {
	String AlphNumneric="abcdefghijklmnopqrstuvxyz123456789";
	   StringBuilder s = new StringBuilder(length);
	   for (int i=0; i<length; i++) {
		   int ch = (int)(AlphNumneric.length() * Math.random());
		   s.append(AlphNumneric.charAt(ch));
		  }
		    return s.toString();		    
}

public static String generateRandomMail(int length) {
	String alphebets="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz";
	String Numbers="123456789";
	String Mail=RandomStringUtils.random(length, alphebets)
			    +RandomStringUtils.random(length/2, Numbers)
			    +"@gmail.com";	
	return Mail;
}
}
