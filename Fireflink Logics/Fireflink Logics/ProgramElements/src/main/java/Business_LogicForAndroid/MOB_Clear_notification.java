package Business_LogicForAndroid;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import io.appium.java_client.android.AndroidDriver;


import org.springframework.stereotype.Component;

@Component("LIC1710_PJT1014_PE_NLP50839604-eb62-4d0e-9d7e-e008e80b6c85")
public class MOB_Clear_notification  implements Nlp {
    @InputParams({@InputParam(name = "locatorValue", type = "java.lang.String")})
    
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
        String loctorValue = (String) attributes.get("locatorValue");
       
        try {
            AndroidDriver driver=(AndroidDriver) nlpRequestModel.getAndroidDriver();
            
              if (driver.findElement(By.xpath(loctorValue)).isEnabled()) 
                     {
	    	             driver.findElement(By.xpath(loctorValue)).click();
	    	             nlpResponseModel.setStatus(CommonConstants.pass);
		                 nlpResponseModel.setMessage("Successfully cleared  notification ");
                     }
              
	           else
	           {
		                driver.navigate().back();
		                nlpResponseModel.setStatus(CommonConstants.pass);
		                nlpResponseModel.setMessage("Notification is not present at the movement");
	               }
            
        } catch(Exception e) {
                  nlpResponseModel.setStatus(CommonConstants.fail);
			         nlpResponseModel.setMessage("Failed to clear notification");
        }
        return nlpResponseModel;
    }
}
