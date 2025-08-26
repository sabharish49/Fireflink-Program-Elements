package bussiness_logic;

	import com.tyss.optimize.common.util.CommonConstants;
	import com.tyss.optimize.nlp.util.Nlp;
	import com.tyss.optimize.nlp.util.NlpException;
	import com.tyss.optimize.nlp.util.NlpRequestModel;
	import com.tyss.optimize.nlp.util.NlpResponseModel;
	import com.tyss.optimize.nlp.util.annotation.InputParam;
	import com.tyss.optimize.nlp.util.annotation.InputParams;
	import com.tyss.optimize.nlp.util.annotation.ReturnType;
	import java.util.Map;
	import java.util.ArrayList;
	import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.springframework.stereotype.Component;

	
@Component("LIC14952_PJT1001_PE_NLP1efad9ae-d178-41e2-96a1-c5fef0e28910")
public class SizeOfTheWebElement implements Nlp {
	
	    @InputParams({@InputParam(name = "String1", type = "java.lang.String")})
	    @ReturnType(name = "size", type = "java.lang.Integer")

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
	          String String1 = (String) attributes.get("String1");
	          int size=0;
	          WebDriver driver=nlpRequestModel.getWebDriver();
	          // Your program element business logic goes here ...
	          try {
	        	  List<WebElement> tags=driver.findElements(By.tagName("String1"));
	        		 size=tags.size();
	    
	        		  nlpResponseModel.setStatus(CommonConstants.pass);
	        		  nlpResponseModel.setMessage("The size of elements fetched successfully");
	        		 
	          }
	          catch(Exception e)
	          {
	    		  nlpResponseModel.setStatus(CommonConstants.fail);
	    		  nlpResponseModel.setMessage("Fetching size of elements is failed");
	          }
	          //String string3 = "Return Value";
	          nlpResponseModel.getAttributes().put("size", size);
	          return nlpResponseModel;
	      }
	  } 






