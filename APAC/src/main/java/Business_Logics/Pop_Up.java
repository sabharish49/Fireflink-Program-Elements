package Business_Logics;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;


public class Pop_Up implements Nlp {
	   //@InputParams({@InputParam(name = "appPackage", type = "java.lang.String")})
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
	          AndroidDriver driver = nlpRequestModel.getAndroidDriver();
	          Map<String, Object> attributes = nlpRequestModel.getAttributes();
	          //String Message = (String) attributes.get("appPackage");	
	          boolean isAlertDisplayed = false;
	         try {       	
	        	 isAlertDisplayed = isAlertPresent(driver);
	             if (isAlertDisplayed) {
	            	 nlpResponseModel.setStatus(CommonConstants.pass);
		        	 nlpResponseModel.setMessage("Alert pop is present in current screen");
	             } else {
	            	 nlpResponseModel.setStatus(CommonConstants.pass);
		        	 nlpResponseModel.setMessage("Pop up not present in current screen");
 	             }
	         }
	         catch(Exception e) {
	        	 nlpResponseModel.setStatus(CommonConstants.pass);
	        	 nlpResponseModel.setMessage("Pop up not present in current screen"+e); 
	         }
	      nlpResponseModel.getAttributes().put("isDisplay", isAlertDisplayed);
	          return nlpResponseModel;
	      }
	      
	      public static boolean isAlertPresent(WebDriver driver) {
	          try {
	              WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	              wait.until(ExpectedConditions.alertIsPresent());
	              return true;
	          } catch (TimeoutException e) {
	              return false;
	          }
	  }
	      }