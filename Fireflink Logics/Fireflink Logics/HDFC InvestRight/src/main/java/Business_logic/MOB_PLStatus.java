package Business_logic;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.lang.Character;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import io.appium.java_client.android.AndroidDriver;

import org.springframework.stereotype.Component;


@Component("LIC14172_PJT1001_PE_NLP2f89a2dd-5018-45a9-bd94-5714d8d1d55a")
public class MOB_PLStatus implements Nlp {
	
	  @InputParams({@InputParam(name = "Element", type = "org.openqa.selenium.WebElement")
	  ,@InputParam(name = "startChar", type = "java.lang.Character")
	  ,@InputParam(name = "endChar", type = "java.lang.Character")})
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
          

      	  try {
      		WebElement Element = (WebElement) attributes.get("Element");
            char startChar= (char)attributes.get("startChar");
            char endChar= (char)attributes.get("endChar");
            
          int firstChar=0;
          int lastChar=0;
          String plStatus=null;
      		 AndroidDriver driver=(AndroidDriver)nlpRequestModel.getAndroidDriver();
    		String text=Element.getAttribute("content-desc");
    	firstChar	=text.lastIndexOf(startChar);
    	lastChar=text.lastIndexOf(endChar);
    	plStatus=text.substring(firstChar, lastChar);
    	
    	 nlpResponseModel.setStatus(CommonConstants.pass);
		 nlpResponseModel.setMessage("Succesfully Fetched the P/L Status " +plStatus);
    
   		
      	  }
   		catch(Exception e)
   		{
   		    nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed To Fetch the P/L Status " +e);
   		}
          
          return nlpResponseModel;
      }
  } 



