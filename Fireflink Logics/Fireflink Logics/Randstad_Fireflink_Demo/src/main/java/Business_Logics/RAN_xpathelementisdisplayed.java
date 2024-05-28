package Business_Logics;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.data.models.dto.DriverConfig;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;

import org.springframework.stereotype.Component;

@Component("LIC4858_PJT1019_PE_NLP2fc24dc7-53a5-44bb-b593-85fad19f7561")
public class RAN_xpathelementisdisplayed implements Nlp {
	
	    @InputParams({@InputParam(name = "xpath", type = "java.lang.String")})
	    @ReturnType(name = "isDisplay", type = "java.lang.Boolean")

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
	          String xpath = (String) attributes.get("xpath");
	           WebDriver driver = nlpRequestModel.getWebDriver();	          
	         boolean isDisplay=true;         
	         try { 
	        	 if(isDisplay == driver.findElement(By.xpath(xpath)).isDisplayed()) {
	        	 isDisplay=true;
	         }	    	 
	        	 nlpResponseModel.setStatus(CommonConstants.pass);
	        	 nlpResponseModel.setMessage("Element is Displayed");	
	        	 
	         }	         
	         catch(Exception e) {        	 
	        	 isDisplay=false;
	        	 nlpResponseModel.setStatus(CommonConstants.pass);
	        	 nlpResponseModel.setMessage("Element is not Displayed");	        	 
	         }
	         
	          nlpResponseModel.getAttributes().put("isDisplay", isDisplay);
	          return nlpResponseModel;
	      }

}
