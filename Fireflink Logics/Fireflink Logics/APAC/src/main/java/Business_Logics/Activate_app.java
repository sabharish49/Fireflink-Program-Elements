package Business_Logics;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import io.appium.java_client.android.AndroidDriver;

import org.springframework.stereotype.Component;

@Component("LIC19046_PJT1001_PE_NLP8de322a6-8d62-44be-adee-186a6efb495a")
public class Activate_app implements Nlp {
	   @InputParams({@InputParam(name = "appPackage", type = "java.lang.String")})
	    //@ReturnType(name = "isDisplay", type = "java.lang.Boolean")
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
	          String Message = (String) attributes.get("appPackage");
	          
	         try {       	
	             driver.activateApp(Message);
	             nlpResponseModel.setStatus(CommonConstants.pass);
	        	 nlpResponseModel.setMessage("Successfully closed all apps");
	         }
	         catch(Exception e) {
	        	 nlpResponseModel.setStatus(CommonConstants.fail);
	        	 nlpResponseModel.setMessage("Failed to close all apps"+e);  	 
	         }
	          return nlpResponseModel;
	      }
	      

	      }