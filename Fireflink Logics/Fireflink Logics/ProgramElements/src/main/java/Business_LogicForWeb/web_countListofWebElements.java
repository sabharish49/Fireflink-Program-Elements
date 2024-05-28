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

@Component("LIC1710_PJT1014_PE_NLP5e35a095-3b83-4bc3-85f4-07535ef4299a")
public class web_countListofWebElements implements Nlp {
	@InputParams({@InputParam(name = "StringXpath", type = "java.lang.String")})
    @ReturnType(name = "sizeofWebElements", type = "java.util.Integer")

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
          Integer sizeofWebElements=0;
          try {
        	WebDriver driver = (WebDriver) nlpRequestModel.getDriver().getSpecificIDriver();
  			List<WebElement> listofproducts = driver.findElements(By.xpath(StringXpath));
            sizeofWebElements=listofproducts.size();
  			nlpResponseModel.setStatus(CommonConstants.pass);
  			nlpResponseModel.setMessage("Size of list xpath is: "+sizeofWebElements);

  		} catch (Exception e) {

  			nlpResponseModel.setStatus(CommonConstants.fail);
  			nlpResponseModel.setMessage("Get Size of list xpath failed");

  		}
          nlpResponseModel.getAttributes().put("sizeofWebElements", sizeofWebElements);
          return nlpResponseModel;
      }
  } 