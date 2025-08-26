package Business_Logics;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

@Component("LIC19046_PJT1001_PE_NLP51bf473e-cf2c-4096-8e19-c6eae9efba20")
public class Switch_to_recent_app implements Nlp {
	    @InputParams({@InputParam(name = "appPackage", type = "java.lang.String"), @InputParam(name = "appActivity", type = "java.lang.String")})
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
	          String appPackage=(String) attributes.get("appPackage");
	          String appActivity=(String) attributes.get("appActivity");
	          try {
	        	  String adbCommand = "adb shell am start -a android.intent.action.MAIN" +
	                      " -c android.intent.category.LAUNCHER" +
	                      " -n " + appPackage + "/" + appActivity;
	              nlpResponseModel.setStatus(CommonConstants.pass);
	              try {
	                  Process process = Runtime.getRuntime().exec(adbCommand);
	                  process.waitFor();
	              } catch (Exception e) {
	                  e.printStackTrace();
	              }
	              nlpResponseModel.setStatus(CommonConstants.pass);
	    			nlpResponseModel.setMessage("Successfully switch to app");
	          } catch (Exception e) {
	              e.printStackTrace();
	              nlpResponseModel.setStatus(CommonConstants.fail);
	    			nlpResponseModel.setMessage("Failed to switch Recent app"+e);
	          }          
	          return nlpResponseModel;
	      }

}