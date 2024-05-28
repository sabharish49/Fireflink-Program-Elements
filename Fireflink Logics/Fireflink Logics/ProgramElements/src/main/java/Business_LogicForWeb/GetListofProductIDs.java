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

@Component("LIC1710_PJT1014_PE_NLPd16e06cb-d2fc-4bcc-a132-1bcbdcf1613a")
public class GetListofProductIDs implements Nlp {
	@InputParams({@InputParam(name = "Set String Xpath", type = "java.lang.String"),
				  @InputParam(name = "Set Attribute Value", type = "java.lang.String")})
    @ReturnType(name = "ReturnListOfText", type = "java.util.ArrayList")

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
	      ArrayList<String> getListOfValues = new ArrayList<String>();
	      try 
	      	{
	          	String stringXpath = (String) attributes.get("Set String Xpath");
	          	String attributeValue = (String) attributes.get("Set Attribute Value");
	    	  	WebDriver driver = (WebDriver) nlpRequestModel.getWebDriver();
	    		List<WebElement> eles = driver.findElements(By.xpath(stringXpath));
	    		int i = 0;
	    		for(WebElement ele : eles)
	    		{
	    			String   str1 = ele.getAttribute(attributeValue);
	    			String[] str2 = str1.split(".jpg");
	    			String[] str3 = str2[1].split("product/");
	    			String   str4 = str3[1].toString();
	    			String[] str5 = str4.split("/");
	    			getListOfValues.add(i, str5[1].toString());
	    			i++;
	    		}
	  			nlpResponseModel.setStatus(CommonConstants.pass);
	  			nlpResponseModel.setMessage(" ArrayList of String Text is Fetched  "+getListOfValues);
			}
      catch (Exception e)
      	{
  			nlpResponseModel.setStatus(CommonConstants.pass);
  			nlpResponseModel.setMessage(" Something Went Wrong ");
		}
      nlpResponseModel.getAttributes().put("ReturnListOfText", getListOfValues);
      return nlpResponseModel;
      }
  } 