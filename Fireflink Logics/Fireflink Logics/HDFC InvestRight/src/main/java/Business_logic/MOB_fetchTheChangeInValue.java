package Business_logic;


	import com.tyss.optimize.common.util.CommonConstants;
	import com.tyss.optimize.nlp.util.Nlp;
	import com.tyss.optimize.nlp.util.NlpException;
	import com.tyss.optimize.nlp.util.NlpRequestModel;
	import com.tyss.optimize.nlp.util.NlpResponseModel;
	import com.tyss.optimize.nlp.util.annotation.InputParam;
	import com.tyss.optimize.nlp.util.annotation.InputParams;
	import com.tyss.optimize.nlp.util.annotation.ReturnType;

	import io.appium.java_client.android.AndroidDriver;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.time.LocalTime;
import java.util.ArrayList;
	import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
	import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Component;
@Slf4j
	

@Component("LIC14172_PJT1001_PE_NLP11135a57-73da-403f-8bd5-457b36905159")
public class MOB_fetchTheChangeInValue implements Nlp {
	@InputParams({@InputParam(name =  "xpath", type = "java.lang.String"), @InputParam(name = "attribute", type = "java.lang.String"),@InputParam(name = "startChar", type = "java.lang.String"),@InputParam(name = "endChar", type = "java.lang.String"),@InputParam(name = "startIndex", type = "java.lang.Integer"),@InputParam(name = "endIndex", type = "java.lang.Integer"),@InputParam(name = "endTime", type = "java.lang.String"),@InputParam(name = "wait", type = "java.lang.Integer")})
	@ReturnType(name = "changeValue", type = "java.lang.String")

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
			String xpath=(String) attributes.get("xpath");
			String attribute = (String) attributes.get("attribute");
			String startChar = (String) attributes.get("startChar");
			String endChar = (String) attributes.get("endChar");
			Integer startIndex = (Integer) attributes.get("startIndex");
			Integer endIndex = (Integer) attributes.get("endIndex");
			String endTime=(String) attributes.get("endTime");
			Integer wait=(Integer) attributes.get("wait");
			String changeValue=null;
			// Your program element business logic goes here ..
			try {
				DesiredCapabilities dc = new DesiredCapabilities();
	      		dc.setCapability("platformName", "Android");
	      		dc.setCapability("noReset","true");
	            AndroidDriver driver=(AndroidDriver)nlpRequestModel.getAndroidDriver(); 
	        	log.info("Driver ----------"+driver);	        	
	        	Thread.sleep(wait);
	      		changeValue=change(driver, xpath, attribute,startChar,endChar, startIndex, endIndex, endTime,nlpResponseModel,nlpRequestModel);
	      			
			}
			catch (Exception e) {
				nlpResponseModel.setStatus(CommonConstants.fail);
				nlpResponseModel.setMessage("Failed to open session"+e);

			}
		    nlpResponseModel.getAttributes().put("changeValue", changeValue);
			return nlpResponseModel;

		}
		

		public static String change(AndroidDriver driver, String xpath, String attribute, String startChar, String endChar,
				Integer startIndex, Integer endIndex, String endTime, NlpResponseModel nlpResponseModel,
				NlpRequestModel nlpRequestModel) {
			
	    	String changevalue=null;
	  		try {
	  			WebElement ele = driver.findElement(By.xpath(xpath));
	  			String value1 = ele.getAttribute(attribute);
	  			log.info("value1============="+value1);
	  			int a= value1.indexOf(startChar);
	  			log.info("startchar------------:"+a);
	  			int b= value1.indexOf(endChar);
	  			log.info("endchar---------------"+b);
	  			String substring= value1.substring(a+startIndex, b+endIndex);
	  			log.info("substring========================"+substring);

	  			//System.out.println("substring"+" "+substring);
	  			//System.out.println("======================");
	  			changevalue = substring;

	  			//System.out.println("enter time:");
	  			String inputTime = endTime;
	  			LocalTime time = LocalTime.parse(inputTime); 

	  			while(substring.equals(changevalue)) {
	  				try {
	  					//System.out.println("inside while");
	  					LocalTime currentTime = LocalTime.now();
	  					if (currentTime.isBefore(time)) 
	  					{
	  						String value2 = driver.findElement(By.xpath(xpath)).getAttribute(attribute);					
	  						int a1= value2.indexOf(startChar);
	  						log.info("A1-------------------------- :"+a1);
	  						int b1= value2.indexOf(endChar);
	  						log.info("A1-------------------------- :"+b1);

	  						String substring1= value2.substring(a1+startIndex, b1+endIndex);
	  						//System.out.println("substring1"+" "+substring1);
	  						changevalue=substring1;
	  					}
	  					else {
	  						break;
	  					}
	  				}

	  				catch(StaleElementReferenceException e) {
	  					change(driver, xpath, attribute,startChar,endChar, startIndex, endIndex, endTime,nlpResponseModel,nlpRequestModel);
	  					nlpResponseModel.setStatus(CommonConstants.fail);
	  					nlpResponseModel.setMessage("Failed to fetch the change in value due to exception1"+e);
	  				}
	  			}
	  		
	  			if(substring.equals(changevalue)) {
	  				nlpResponseModel.setStatus(CommonConstants.fail);
					nlpResponseModel.setMessage("Failed to fetch the changed value in the given time");	
				}
				else {
					nlpResponseModel.setStatus(CommonConstants.pass);
					nlpResponseModel.setMessage("Successfully verified change in values "+" "+"Previous Value : "+substring +"and CurrentValue is : "+ changevalue);
				}

	  		}
	  		catch(StaleElementReferenceException e) {
	  			change(driver, xpath, attribute,startChar,endChar, startIndex, endIndex, endTime,nlpResponseModel,nlpRequestModel);
	  			nlpResponseModel.setStatus(CommonConstants.fail);
				nlpResponseModel.setMessage("Failed to fetch the changed value due to exception2 "+e);
	  		}
	  		return changevalue;

	  	}
	}

