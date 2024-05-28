package Business_Logics;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import org.springframework.stereotype.Component;

@Component("LIC4858_PJT1019_PE_NLP72cac5cc-da0b-4703-af34-8495c4b86751")
public class GetAttributeValue implements Nlp {
	
	    @InputParams({@InputParam(name = "Element", type = "org.openqa.selenium.WebElement"),@InputParam(name = "AttributeName", type = "java.lang.String")})
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
	           WebElement xpath = (WebElement) attributes.get("Element");
	           String attributevalue = (String) attributes.get("AttributeName");	          
	         boolean isDisplay=true;         
	         try { 
	        	Thread.sleep(2000);
	        	String value = xpath.getAttribute(attributevalue);
	        	 if(value.isEmpty()) {
	        	 isDisplay=true;
	         }	
	        	 else
	        	 {
	        	 	 isDisplay=false;
	        	 }
	        	 nlpResponseModel.setStatus(CommonConstants.pass);
	        	 nlpResponseModel.setMessage("successfully fetched sttribute value");	   	 
	         }	         
	         catch(Exception e) {        	 
	       
	        	 nlpResponseModel.setStatus(CommonConstants.fail);
	        	 nlpResponseModel.setMessage("Failed to fetch attribute value");	        	 
	         }        
	          nlpResponseModel.getAttributes().put("isDisplay", isDisplay);
	          return nlpResponseModel;
	      }

}
