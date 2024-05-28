package Business_logic;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
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

@Component("LIC14172_PJT1001_PE_NLP59bf155a-5131-4796-924f-9a5c3d20979f")
public class MOB_xpathelementisdisplayed implements Nlp {
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
	          AndroidDriver driver = nlpRequestModel.getAndroidDriver();
	          
	         //WebElement element=(WebElement) attributes.get("element");
	         boolean isDisplay=true;

	          // Your program element business logic goes here ...
	         try {
	        	//WebElement ele = driver.findElement(By.xpath(xpath));
	        	 if(isDisplay == driver.findElement(By.xpath(xpath)).isDisplayed()) {
	        	 isDisplay=true;444
	         }
	        	 nlpResponseModel.setStatus(CommonConstants.pass);
	        	 nlpResponseModel.setMessage("Element is Displayed");
	         }
	         catch(Exception e) {
	        	 isDisplay=false;
	        	 nlpResponseModel.setStatus(CommonConstants.pass);
	        	 nlpResponseModel.setMessage("Element is not Displayed");
	        	 
	         }
	         // String string3 = "Return Value";
	          nlpResponseModel.getAttributes().put("isDisplay", isDisplay);
	          return nlpResponseModel;
	      }

}
