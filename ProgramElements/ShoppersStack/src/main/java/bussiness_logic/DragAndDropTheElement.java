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

@Component("LIC14952_PJT1001_PE_NLPd5739082-5649-4e1f-a126-18b6e6bf110b")
public class DragAndDropTheElement implements Nlp {
		
		    @InputParams({@InputParam(name = "source", type = "org.openqa.selenium.WebElement"),
		    @InputParam(name = "target", type = "org.openqa.selenium.WebElement")})
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
		         
		          WebElement source = (WebElement) attributes.get("source");
		          WebElement target = (WebElement) attributes.get("target");

		          // Your program element business logic goes here ...
		           Boolean result=true;
		           WebDriver driver = nlpRequestModel.getWebDriver();
		           try {

		           Actions actions = new Actions(driver);

		           actions.dragAndDrop(source, target).build().perform();
		           result=true;
	   		       nlpResponseModel.setStatus(CommonConstants.pass);
	   		       nlpResponseModel.setMessage("drag and droped successfully");

		           }
		           catch (Exception e) {
						result=false;
						nlpResponseModel.setStatus(CommonConstants.fail);
			 		    nlpResponseModel.setMessage("failed to drag and drop" +e);
		           }
		          nlpResponseModel.getAttributes().put("result", result);
		          return nlpResponseModel;
		      }
		  } 
