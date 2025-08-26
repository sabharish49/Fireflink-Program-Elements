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

@Component("LIC14952_PJT1001_PE_NLP9ea3ee04-654c-4ad2-9649-9586ff8e55e5")
public class GetTextFromListOfWebelementsWithSameTagname implements Nlp {
	
	    @InputParams({@InputParam(name = "tagname", type = "java.lang.String")})
	    @ReturnType(name = "list", type = "java.util.ArrayList")

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
	          String tagname = (String) attributes.get("tagname");
	          WebDriver driver=nlpRequestModel.getWebDriver();
	          ArrayList list=new ArrayList();
	          //String string2 = (String) attributes.get("string2");

	          // Your program element business logic goes here ...
	          
	          try {
	        	  List<WebElement> tags= driver.findElements(By.tagName("tagname"));
	        	  for (WebElement tag :tags) {
	        		  
	        		  list.add(tag.getText());
				}
	        	 
	        	  nlpResponseModel.setStatus(CommonConstants.pass);
		          nlpResponseModel.setMessage("list of text is obtained successfully");
	          }
	          catch(Exception e) {
	        	  nlpResponseModel.setStatus(CommonConstants.fail);
		          nlpResponseModel.setMessage("failed");
	          }
	        //  String string3 = "Return Value";
	          nlpResponseModel.getAttributes().put("list", list);
	          return nlpResponseModel;
	      }
	  } 











