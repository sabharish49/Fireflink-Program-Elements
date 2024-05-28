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





@Component("LIC1710_PJT1014_PE_NLP6e742cbc-c54b-49f8-9078-685000b9f7f7")
public class GetListofAttributeValues1 implements Nlp {
	@InputParams({@InputParam(name = "FindsStringXpath", type = "java.lang.String"),
				  @InputParam(name = "AttributeValue", type = "java.lang.String"),
				  @InputParam(name = "SplitValue_1", type = "java.lang.String"),
				  @InputParam(name = "SplitValue_2", type = "java.lang.String"),
				  @InputParam(name = "SplitValue_3", type = "java.lang.String")})
    @ReturnType(name = "ListOfText", type = "java.util.ArrayList")

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
          String FindsStringXpath = (String) attributes.get("FindsStringXpath");
          String AttributeValue = (String) attributes.get("AttributeValue");
          String SplitValue_1 = (String) attributes.get("SplitValue_1");
          String SplitValue_2 = (String) attributes.get("SplitValue_2");
          String SplitValue_3 = (String) attributes.get("SplitValue_3");
	      ArrayList<String> ListOfText = new ArrayList<String>();
	      try 
	      	{
	    	  	WebDriver driver = (WebDriver) nlpRequestModel.getWebDriver();
	    		List<WebElement> eles = driver.findElements(By.xpath(FindsStringXpath));
	    		for(WebElement ele : eles)
	    		{
	    			String   str1 = ele.getAttribute(AttributeValue);
	    			String[] str2 = str1.split(SplitValue_1);
	    			String[] str3 = str2[1].split(SplitValue_2);
	    			String   str4 = str3[1].toString();
	    			String[] str5 = str4.split(SplitValue_3);
	    			ListOfText.add(str5[1].toString());
	    		}
	  			nlpResponseModel.setStatus(CommonConstants.pass);
	  			nlpResponseModel.setMessage(" ArrayList of String Text is Fetched  "+ListOfText);
			}
      catch (Exception e)
      	{
  			nlpResponseModel.setStatus(CommonConstants.fail);
  			nlpResponseModel.setMessage(" Something Went Wrong ");
		}
      nlpResponseModel.getAttributes().put("ListOfText", ListOfText);
      return nlpResponseModel;
      }
  } 