
	package bussiness_logic;
	import java.io.File;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Map;

	import org.apache.commons.io.FileUtils;
	import org.openqa.selenium.OutputType;
    import org.openqa.selenium.TakesScreenshot;
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


			@Component("LIC14952_PJT1001_PE_NLP28a05449-b877-4416-989c-e29ae5edc6d4")
public class GetScreenshotOfWebpage implements Nlp {
			
			    @InputParams({@InputParam(name = "filePath", type = "java.lang.String")})
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
			          
			          String  filePath= (String) attributes.get("filePath");
			          boolean result=true;
			          
			          WebDriver driver=nlpRequestModel.getWebDriver();
			          try {
			        	  TakesScreenshot ts= (TakesScreenshot) driver;
			        	  
			        	  File src=ts.getScreenshotAs(OutputType.FILE);
			        	  File dest = new File(filePath);
			        	  FileUtils.copyFile(src, dest);
			        	  result=true;
			        	  nlpResponseModel.setStatus(CommonConstants.pass);
			  			  nlpResponseModel.setMessage("screenshot captured successfully");
			        	  		        	  
			          }
			          catch(Exception e) {
			        	  result=false;
			        	  nlpResponseModel.setStatus(CommonConstants.fail);
			  			  nlpResponseModel.setMessage("screenshot not captured");
			          }

			          // Your program element business logic goes here ...

			         // String string3 = "Return Value";
			          nlpResponseModel.getAttributes().put("result", result);
			          return nlpResponseModel;
			      }
			  

		




			// TODO Auto-generated method stub

		}





