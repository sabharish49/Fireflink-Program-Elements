package Business_LogicForAndroid;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;


@Component("LIC1710_PJT1014_PE_NLP9298ccee-7cdb-4fa4-bb77-2faf5bd995ae")
public class MOB_OpenNotificationwithMobile implements Nlp {
    @InputParams({@InputParam(name = "udid", type = "java.lang.String"),})

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
          String udid = (String) attributes.get("udid");
  		AndroidDriver driver = (AndroidDriver) nlpRequestModel.getDriver().getSpecificIDriver();
          // Your program element business logic goes here ...
          try {
        	
        	DesiredCapabilities cap = new DesiredCapabilities();
  	    	cap.setCapability(MobileCapabilityType.UDID,udid);
  	    	cap.setCapability(MobileCapabilityType.NO_RESET, true);
  			URL url = new URL("http://localhost:4723/wd/hub");
  			
  		     driver = new AndroidDriver (url,cap);
  		    driver.openNotifications();
  		    
			
			Thread.sleep(10000);
			driver.openNotifications();
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Notification Opened Successfully");
          }
          catch(Exception e) {
        	  e.printStackTrace();
  			nlpResponseModel.setStatus(CommonConstants.fail);
  			nlpResponseModel.setMessage("Failed to Open Notification");
          }
         // String string3 = "Return Value";
          //nlpResponseModel.getAttributes().put("string3", string3);
          return nlpResponseModel;
      }
  } 

