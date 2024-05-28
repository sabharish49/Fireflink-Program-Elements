package Business_LogicForAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v114.network.Network;
import org.springframework.stereotype.Component;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;




@Component("LIC1710_PJT1014_PE_NLP227eb035-3244-4221-8e19-1badebd71874")
public class AddRequestListenerAndNavigateToURL implements Nlp {
	@InputParams({ @InputParam(name = "url", type = "java.lang.String") })
	@ReturnType(name = "deviceId", type = "java.util.ArrayList")

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
		List<String> l1=new ArrayList<>();
		List<String> l2=new ArrayList<>();
		List<String> l3=new ArrayList<>();
		

		NlpResponseModel nlpResponseModel = new NlpResponseModel();
		Map<String, Object> attributes = nlpRequestModel.getAttributes();
		AtomicReference<String> requiredData = new AtomicReference<>("");
		//String deviceID="";
		try {
			String url = (String) attributes.get("url");
			WebDriver driver=(WebDriver)nlpRequestModel.getWebDriver();
	        
			DevTools devTools = ((ChromeDriver) driver).getDevTools();
			devTools.createSession();
			devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
			devTools.addListener(Network.requestWillBeSent(), request -> {
				String requestBody = String.valueOf(request.getRequest().getPostData());
				
				if (requestBody.toString().contains("deviceId")) {
					requiredData.set(requestBody.substring(9).replace("[", ""));
				//log.info("Request Body fetched successfully; value is: {}", requiredData.get());
					 int n=requiredData.get().indexOf("deviceId");
					 int m=requiredData.get().indexOf("\"", n+11);
					
                     l1.add( requiredData.get().substring(n+11,m));
                     
				}
				
				if(requestBody.toString().contains("deviceToken")) {
                    // requiredData.set(requestBody.substring(9).replace("[", ""));
                    // System.out.println("Request body is:\n" +requiredData);
                     int n1=requiredData.get().indexOf("deviceToken");
                     int m1=requiredData.get().indexOf("\"", n1+14);
                     l2.add( requiredData.get().substring(n1+14,m1));
               
                 return;
                     
                 } 
			});
			
			driver.get(url);


			//Log4j("Navigated to url: {} successfully", url);

			nlpResponseModel.setMessage("Navigated to url and request header fetched successfully"+ l3);
			nlpResponseModel.setStatus("PASS");
		} catch (Exception e) {
//			log.error("Exception is: {}", e);
			nlpResponseModel.setMessage("Failed to fetch request header");
			nlpResponseModel.setStatus("FAIL");
		}
		finally {

//        	  System.out.println(l1); 
			 l3.add(l1.get(l1.size() - 1));
	 		 l3.add(l2.get(l2.size() - 1));

        }
		nlpResponseModel.getAttributes().put("deviceId", l3);
		return nlpResponseModel;
	}
}