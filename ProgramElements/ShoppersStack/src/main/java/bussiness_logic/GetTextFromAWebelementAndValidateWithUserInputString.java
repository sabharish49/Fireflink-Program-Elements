package bussiness_logic;


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


@Component("LIC14952_PJT1001_PE_NLP00b096dd-6451-4f39-8c3e-9afc75193789")
public class GetTextFromAWebelementAndValidateWithUserInputString implements Nlp {
	
	    @InputParams({@InputParam(name = "element", type = "org.openqa.selenium.WebElement"), @InputParam(name = "string", type = "java.lang.String")})
	    @ReturnType(name = "result", type = "java.lang.Boolean")

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
	          WebElement element = (WebElement) attributes.get("element");
	          String  string= (String) attributes.get("string");
	          boolean result=true;
	          String elementText="";
	          WebDriver driver=nlpRequestModel.getWebDriver();
	          try {
	        	  elementText= element.getText();
	        	  if (elementText.equals(string)) {
	        		  result = true;
	        		  nlpResponseModel.setStatus(CommonConstants.pass);
	      			  nlpResponseModel.setMessage("Both Strings Are Equal");

	        	     }
	        	  else {
	        		  result=false;
	        		  nlpResponseModel.setStatus(CommonConstants.fail);
	      			  nlpResponseModel.setMessage("Both Strings Are Not Equal");
	        	  }
	          }
	          catch(Exception e) {
	        	  nlpResponseModel.setStatus(CommonConstants.fail);
	  			  nlpResponseModel.setMessage("Both Strings Are Not Equal");
	          }

	          // Your program element business logic goes here ...

	         // String string3 = "Return Value";
	          nlpResponseModel.getAttributes().put("result", result);
	          return nlpResponseModel;
	      }
	  

}



