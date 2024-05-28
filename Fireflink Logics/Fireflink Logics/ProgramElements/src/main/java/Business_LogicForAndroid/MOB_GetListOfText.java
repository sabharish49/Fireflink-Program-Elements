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

@Component("LIC1710_PJT1014_PE_NLPaf2c4ef7-9330-421a-bf87-415e69bd1c84")
public class MOB_GetListOfText implements Nlp {
	@InputParams({@InputParam(name = "StringXpath", type = "java.lang.String")})
    @ReturnType(name = "listOfText", type = "java.util.ArrayList")

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
          ArrayList<String> listOfText = new ArrayList<>();
          try {

        	AndroidDriver driver = (AndroidDriver) nlpRequestModel.getAndroidDriver();
  			List<WebElement> getList = driver.findElements(By.xpath(StringXpath));
  			for (WebElement singlValue : getList) 
  			{
  				listOfText.add(singlValue.getText());
  			}
  			nlpResponseModel.setStatus(CommonConstants.pass);
  			nlpResponseModel.setMessage("Got the list of values : "+listOfText);

  		} catch (Exception e) {

  			nlpResponseModel.setStatus(CommonConstants.fail);
  			nlpResponseModel.setMessage("Failed to Get the list of values");

  		}
          nlpResponseModel.getAttributes().put("listOfText", listOfText);
          return nlpResponseModel;
      }
  } 