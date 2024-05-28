package Mobileweb;

	  import com.tyss.optimize.common.util.CommonConstants;
      import com.tyss.optimize.nlp.util.Nlp;
	  import com.tyss.optimize.nlp.util.NlpException;
	  import com.tyss.optimize.nlp.util.NlpRequestModel;
	  import com.tyss.optimize.nlp.util.NlpResponseModel;
	  import com.tyss.optimize.nlp.util.annotation.InputParam;
	  import com.tyss.optimize.nlp.util.annotation.InputParams;

import io.appium.java_client.android.AndroidDriver;

import java.util.Map;
	  import java.util.ArrayList;
	  import java.util.List;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

	   @Component("LIC1710_PJT1014_PE_NLPc1fbb830-dc26-4a28-8bca-4508f558768e")
	  public class MWEB_TapByXpathOnElement implements Nlp {
	        @InputParams({@InputParam(name = "Xpath", type = "java.lang.String")})
	      

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
	          String Xpath = (String)attributes.get("Xpath");
	          AndroidDriver driver = (AndroidDriver) nlpRequestModel.getAndroidDriver();
	          try {
	        	  driver.findElement(By.xpath(Xpath)).click();
	        	  nlpResponseModel.setStatus(CommonConstants.pass);
	    	       nlpResponseModel.setMessage("Successfully Tapped On Element");    
	             }
	          catch(Exception e){
	        	  nlpResponseModel.setStatus(CommonConstants.fail +e);
	        	  nlpResponseModel.setMessage("Failed To Tap On Element");
	          }
	          return nlpResponseModel;
	      }
	  } 
	


