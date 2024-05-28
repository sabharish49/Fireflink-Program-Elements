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
import org.springframework.stereotype.Component;




@Component("LIC1710_PJT1014_PE_NLP04bcb363-d9b1-4bb7-810e-f3066c4300fa")
public class IsEnabledOrNot implements Nlp {
    @InputParams({@InputParam(name = "stringXpath", type = "java.lang.String")})
    @ReturnType(name = "getBoolean", type = "java.lang.Boolean")

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
          String stringXpath = (String) attributes.get("stringXpath");
          Boolean getBoolean =false;
          WebDriver driver=(WebDriver)nlpRequestModel.getWebDriver();
          try
          {
			Boolean condition = driver.findElement(By.xpath(stringXpath)).isEnabled();
			if(condition==true)
			{
				getBoolean =true;
	          	nlpResponseModel.setStatus(CommonConstants.pass);
	          	nlpResponseModel.setMessage("Element Is Enabled In WebPage");
			}
			else
			{
	          	nlpResponseModel.setStatus(CommonConstants.pass);
	          	nlpResponseModel.setMessage("Element Is Not Enabled In WebPage");
			}
          } 
          catch (Exception e) 
          {
        	nlpResponseModel.setStatus(CommonConstants.pass);
  			nlpResponseModel.setMessage("Element Not Exist In WebPage");
          }

          nlpResponseModel.getAttributes().put("getBoolean", getBoolean);
          return nlpResponseModel;
      }
  } 