
package business_Logics;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.tyss.optimize.common.util.*;
import com.tyss.optimize.nlp.util.*;
import com.tyss.optimize.nlp.util.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;




public class GetLastMailFromInbox implements Nlp {
    @InputParams({@InputParam(name = "Mail Attributes", type = "java.util.Map")})
    @ReturnType(name = "last message", type = "java.lang.String")

      public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {
       
          NlpResponseModel nlpResponseModel = new NlpResponseModel();
          Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
          Map mailDetails = (Map) programElementsInput.get("Mail Attributes");
        	map.putAll(mailDetails);
        	String message ="";
          try {
        	message= getLastMail(getBearerToken());
      
       // System.out.println(message);
              nlpResponseModel.setMessage("The Last message in inbox is: "+message);
              nlpResponseModel.setStatus(CommonConstants.pass);
              }
          catch(Exception e) {
           // Your program element Exception goes here ...
              nlpResponseModel.setMessage("Failed to get message");
              nlpResponseModel.setStatus(CommonConstants.fail);
              }

          // Your program element business logic ends here ...
          nlpResponseModel.getAttributes().put("last message",message );
          return nlpResponseModel;
      }

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
      
      public static String getBearerToken() throws IOException {
  		String apiUrl = "https://api.mail.tm/token";
  		String password = map.get("password").toString();
  		String mail = map.get("mail").toString();
  String jsonBody = "{\n" +
                   "  \"address\": \""+mail+"\",\n" +
                   "  \"password\": \""+password+"\"\n" +
                   "}";
  		StringBuilder response = new StringBuilder();
  		URL url = new URL(apiUrl);
  		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
  		
  		connection.setRequestMethod("POST");
  		  connection.setRequestProperty("accept", "application/ld+json");
  	         //  connection.setRequestProperty("Authorization", "Bearer " + authorizationToken);
  	           connection.setRequestProperty("Content-Type", "application/ld+json");
  	           // Enable input and output streams
  	           connection.setDoOutput(true);
  	           
  	           
  	           try (OutputStream os = connection.getOutputStream()) {
  	               byte[] input = jsonBody.getBytes("utf-8");
  	               os.write(input, 0, input.length);
  	           }
  		int responseCode = connection.getResponseCode();
  		if ((responseCode == HttpURLConnection.HTTP_OK )||responseCode==HttpURLConnection.HTTP_CREATED) {
  			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
  			String line;
  			while ((line = reader.readLine()) != null) {
  				response.append(line);
  			}
  			reader.close();
  			connection.disconnect();
  		}
  		//  System.out.println(response.toString());
  		Object document = Configuration.defaultConfiguration().jsonProvider().parse(response.toString());
  		String token= JsonPath.read(document, "$.token");
  		return token;
  	}
      static Map map=new HashMap();
  	public static String getLastMail(String bearerToken) throws IOException {
		//String domain="";
		String apiUrl = "https://api.mail.tm/messages?page=1";
		StringBuilder response = new StringBuilder();
		URL url = new URL(apiUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
	       connection.setRequestProperty("accept", "application/ld+json");
          connection.setRequestProperty("Authorization", "Bearer " + bearerToken);
           connection.setRequestProperty("Content-Type", "application/ld+json");
           
		int responseCode = connection.getResponseCode();

		if ((responseCode == HttpURLConnection.HTTP_OK )||responseCode==HttpURLConnection.HTTP_CREATED) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();
			connection.disconnect();
		
		}
		 // System.out.println(response.toString());
		Object document = Configuration.defaultConfiguration().jsonProvider().parse(response.toString());
		net.minidev.json.JSONArray message = JsonPath.read(document, "$..id");
String mailContent=message.get(0).toString();
//System.out.println("Message id is : "+mailContent);
 url = new URL("https://api.mail.tm/messages/"+mailContent);
 connection = (HttpURLConnection) url.openConnection();
connection.setRequestMethod("GET");
connection.setRequestProperty("accept", "application/ld+json");
//connection.setRequestProperty("Content-Type", "application/ld+json");
connection.setRequestProperty("Authorization", "Bearer " + bearerToken);
 responseCode = connection.getResponseCode();
	StringBuilder response1 = new StringBuilder();

if ((responseCode == HttpURLConnection.HTTP_OK )||responseCode==HttpURLConnection.HTTP_CREATED) {
	BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	String line;
	while ((line = reader.readLine()) != null) {
		response1.append(line);
	}
	reader.close();
	connection.disconnect();
}
//System.out.println(response1);
 document = Configuration.defaultConfiguration().jsonProvider().parse(response1.toString());
net.minidev.json.JSONArray content = JsonPath.read(document, "$..text");
String text=content.get(0).toString();
//System.out.println("Message  is : "+text);
return text;
	
	}
  	public static void main1(String[] args) throws NlpException {
  		
  		// NlpResponseModel nlpResponseModel = new NlpResponseModel();
  		NlpRequestModel nlpRequestModel =new NlpRequestModel();
  		Map m=new HashMap();
  		m.put("password", "Password@123");
  		m.put("mail", "12nlpresponsemodeldev234@belgianairways.com");
  		m.put("id", "66a9eba37fdbec239b14ac40");
  		nlpRequestModel.getAttributes().put("Mail Attributes", m);

  		GetLastMailFromInbox c=new GetLastMailFromInbox();
  		c.execute(nlpRequestModel);
	}
} 