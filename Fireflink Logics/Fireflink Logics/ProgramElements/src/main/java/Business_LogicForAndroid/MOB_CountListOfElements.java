package Business_LogicForAndroid;


import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import io.appium.java_client.android.AndroidDriver;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import org.springframework.stereotype.Component;


@Component("LIC1710_PJT1014_PE_NLPc7e325a4-b94e-4b4d-813f-dde8ff4c1a65")
public class MOB_CountListOfElements implements Nlp {
	@InputParams({@InputParam(name = "StringXpath", type = "java.lang.String")})
    @ReturnType(name = "getCountofWebElements", type = "java.util.Integer")

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
          Integer getCountofWebElements=0;
          try {
        	AndroidDriver driver = (AndroidDriver) nlpRequestModel.getAndroidDriver();
  			List<WebElement> getList = driver.findElements(By.xpath(StringXpath));
  			getCountofWebElements=getList.size();
  			nlpResponseModel.setStatus(CommonConstants.pass);
  			nlpResponseModel.setMessage("Size of list xpath is: "+getCountofWebElements);

  		} catch (Exception e) {
  			nlpResponseModel.setStatus(CommonConstants.fail);
  			nlpResponseModel.setMessage(" Something went Wrong ");

  		}
          nlpResponseModel.getAttributes().put("getCountofWebElements", getCountofWebElements);
          return nlpResponseModel;
      }
  } 