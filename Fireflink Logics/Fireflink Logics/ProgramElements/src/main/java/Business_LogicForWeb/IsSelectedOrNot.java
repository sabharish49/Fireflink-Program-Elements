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




@Component("LIC1710_PJT1014_PE_NLP3bf04f58-6728-4e50-ae18-dcdcc35d18c1")
public class IsSelectedOrNot implements Nlp {
    @InputParams({@InputParam(name = "Set String Xpath", type = "java.lang.String")})
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
          Boolean getBoolean =false;
          try
          {
            WebDriver driver=(WebDriver)nlpRequestModel.getWebDriver();
            String stringXpath = (String) attributes.get("Set String Xpath");
			Boolean condition = driver.findElement(By.xpath(stringXpath)).isSelected();
			if(condition==true)
			{
				getBoolean =true;
	          	nlpResponseModel.setStatus(CommonConstants.pass);
	          	nlpResponseModel.setMessage("Element Is Selected In WebPage");
			}
			else
			{
	          	nlpResponseModel.setStatus(CommonConstants.pass);
	          	nlpResponseModel.setMessage("Element Is Not Selected In WebPage");
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