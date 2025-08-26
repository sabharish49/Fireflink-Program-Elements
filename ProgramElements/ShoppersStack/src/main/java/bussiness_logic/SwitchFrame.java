package bussiness_logic;

	import java.util.ArrayList;
	import java.util.List;
	import java.util.Map;

	import org.openqa.selenium.By;
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

@Component("LIC14952_PJT1001_PE_NLP10669f4c-736a-43c7-9d1b-cbe000103df7")
public class SwitchFrame implements Nlp {
		
		    @InputParams({@InputParam(name = "framexpath", type = "java.lang.String")})
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
		         
		          String framexpath = (String) attributes.get("framexpath");

		          // Your program element business logic goes here ...
		           Boolean result=true;
		          WebDriver driver = nlpRequestModel.getWebDriver();
		          

			        try {
			            
			            
			            WebElement iframeElement = driver.findElement(By.xpath(framexpath));

			            driver.switchTo().frame(iframeElement);

			               result=true;
		        		   nlpResponseModel.setStatus(CommonConstants.pass);
		        		   nlpResponseModel.setMessage("switched to frame");
			        } catch (Exception e) {
			        	
			        	result=false;
						nlpResponseModel.setStatus(CommonConstants.fail);
			 		    nlpResponseModel.setMessage("not switched" +e);
			            
			        }
		       
		          nlpResponseModel.getAttributes().put("result", result);
		          return nlpResponseModel;
		      }
		  } 
