package Business_logics;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;

import org.springframework.stereotype.Component;

@Component("LIC14952_PJT1005_PE_NLPc78c5601-8e34-4967-89de-5516179d6765")
public class Switch_to_recent_app implements Nlp {
	   // @InputParams({@InputParam(name = "element", type = "org.openqa.selenium.WebElement")})
	   // @ReturnType(name = "isDisplay", type = "java.lang.Boolean")

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
	          try {
	              Runtime.getRuntime().exec("adb shell input keyevent KEYCODE_APP_SWITCH");
	              Thread.sleep(1000);
	              Runtime.getRuntime().exec("adb shell input keyevent KEYCODE_DPAD_CENTER");
	              Runtime.getRuntime().exec("adb shell input keyevent KEYCODE_DPAD_CENTER");
	              nlpResponseModel.setStatus(CommonConstants.pass);
	    			nlpResponseModel.setMessage("Successfully switched to Recent app");
	          } catch (Exception e) {
	              e.printStackTrace();
	              nlpResponseModel.setStatus(CommonConstants.pass);
	    			nlpResponseModel.setMessage("Failed to switch Recent app"+e);
	          }          
	          return nlpResponseModel;
	      }

}