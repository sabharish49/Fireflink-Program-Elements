package bussiness_logic;
import java.util.ArrayList;
	import java.util.List;
	import java.util.Map;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.interactions.Actions;
	import com.tyss.optimize.common.util.CommonConstants;
	import com.tyss.optimize.nlp.util.Nlp;
	import com.tyss.optimize.nlp.util.NlpException;
	import com.tyss.optimize.nlp.util.NlpRequestModel;
	import com.tyss.optimize.nlp.util.NlpResponseModel;
	import com.tyss.optimize.nlp.util.annotation.InputParam;
	import com.tyss.optimize.nlp.util.annotation.InputParams;
	import com.tyss.optimize.nlp.util.annotation.ReturnType;

		import org.springframework.stereotype.Component;


@Component("LIC14952_PJT1001_PE_NLP5c7f4a1b-379b-41c5-94dc-30a01b27cc1b")
public class SlidingTheElement implements Nlp {
		    @InputParams({@InputParam(name = "element", type = "org.openqa.selenium.WebElement"),
		    @InputParam(name = "offset", type = "java.lang.Integer"),
		    @InputParam(name = "numberOfTimesToSlide", type = "java.lang.Integer")})
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
		          Integer offset = (Integer) attributes.get("offset");
		          Integer numberOfTimesToSlide = (Integer) attributes.get("numberOfTimesToSlide");
		          boolean result=true;
		          WebDriver driver = nlpRequestModel.getWebDriver();
		          try {
		        	  Actions actions = new Actions(driver);
		        	  for(int i=0;i<=numberOfTimesToSlide;i++) {
		        	  
		        	  actions.clickAndHold(element).moveByOffset(offset, 0).release().perform();
		        	  } 
					    	  result=true;
					    	  nlpResponseModel.setStatus(CommonConstants.pass);
					    	  nlpResponseModel.setMessage("element is moved successfully");
					   			} catch (Exception e) 
		          {		
					  result=false;
			    	  nlpResponseModel.setStatus(CommonConstants.fail);
			    	  nlpResponseModel.setMessage("element is not moved");

					
				}
		          
		          
		          nlpResponseModel.getAttributes().put("result", result);
		          return nlpResponseModel;
		      }
		  } 
