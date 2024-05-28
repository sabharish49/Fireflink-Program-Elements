
package Business_LogicForAndroid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

  import org.springframework.stereotype.Component;

@Component("LIC1710_PJT1014_PE_NLPaa87814b-179f-4732-96de-0f7eaa1e2f3c")
public class MOB_PerformSpecialKeysInMobileKeyboard implements Nlp {
      @InputParams({@InputParam(name = "KeyPad", type = "java.lang.String")})

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
          String KeyPad = (String) attributes.get("KeyPad");
          AndroidDriver driver = nlpRequestModel.getAndroidDriver();
          try {
        	  switch (KeyPad) {
			case "APP_SWITCH":
	        	  driver.pressKey(new KeyEvent().withKey(AndroidKey.APP_SWITCH));
				break;
			case "NUMPAD_ENTER":
	        	  driver.pressKey(new KeyEvent().withKey(AndroidKey.NUMPAD_ENTER));
				break;
			case "ENTER":
	        	  driver.pressKey(new KeyEvent().withKey(AndroidKey.ENTER));
				break;
			case "BACK":
	        	  driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
				break;
			case "COPY":
	        	  driver.pressKey(new KeyEvent().withKey(AndroidKey.COPY));
				break;
			case "PASTE":
	        	  driver.pressKey(new KeyEvent().withKey(AndroidKey.PASTE));
				break;
			case "CUT":
	        	  driver.pressKey(new KeyEvent().withKey(AndroidKey.CUT));
				break;
			case "SPACE":
	        	  driver.pressKey(new KeyEvent().withKey(AndroidKey.SPACE));
				break;
			case "CLEAR":
	        	  driver.pressKey(new KeyEvent().withKey(AndroidKey.CLEAR));
				break;
			case "ESCAPE":
	        	  driver.pressKey(new KeyEvent().withKey(AndroidKey.ESCAPE));
				break;
			case "HOME":
	        	  driver.pressKey(new KeyEvent().withKey(AndroidKey.HOME));
				break;
			default:
	      		   nlpResponseModel.setStatus(CommonConstants.fail);
	           	   nlpResponseModel.setMessage("Not match to the Dial Keypad"); 
				break;
			}
     		   nlpResponseModel.setStatus(CommonConstants.pass);
           	   nlpResponseModel.setMessage("Tapped on Dial Keypad " +KeyPad);
          }
          catch(Exception e)
          {
      		   nlpResponseModel.setStatus(CommonConstants.fail);
       	   nlpResponseModel.setMessage("Failed to Tap on Dial Keypad");    	  
          }
		return nlpResponseModel;
      }
  } 