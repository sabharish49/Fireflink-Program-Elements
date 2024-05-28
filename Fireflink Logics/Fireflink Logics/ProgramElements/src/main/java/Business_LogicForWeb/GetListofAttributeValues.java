package Business_LogicForWeb;

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



@Component("LIC1710_PJT1014_PE_NLPb244b5df-c4fd-4fbe-91c0-2cf7fd880e9f")
public class GetListofAttributeValues implements Nlp {
	@InputParams({@InputParam(name = "StringXpath", type = "java.lang.String"),
				  @InputParam(name = "Attribute", type = "java.lang.String")})
    @ReturnType(name = "ListOfStringText", type = "java.util.ArrayList")

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
          String StringXpath = (String) attributes.get("StringXpath");
          String Attribute = (String) attributes.get("Attribute");
	      ArrayList<String> ListOfStringText = new ArrayList<String>();
	      try 
	      	{
	    	  	WebDriver driver = (WebDriver) nlpRequestModel.getWebDriver();
	    		List<WebElement> eles = driver.findElements(By.xpath(StringXpath));
	    		int i = 0;	    		
	    		for(WebElement ele: eles)
	    		{
	    			ListOfStringText.add(i, ele.getAttribute(Attribute).toString());
	    			i++;
	    		}
	  			nlpResponseModel.setStatus(CommonConstants.pass);
	  			nlpResponseModel.setMessage(" ArrayList of String Attribute Text is Fetched  "+ListOfStringText);
			}
      catch (Exception e)
      	{
  			nlpResponseModel.setStatus(CommonConstants.fail);
  			nlpResponseModel.setMessage(" Something Went Wrong ");
		}
      nlpResponseModel.getAttributes().put("ListOfStringText", ListOfStringText);
      return nlpResponseModel;
      }
  } 