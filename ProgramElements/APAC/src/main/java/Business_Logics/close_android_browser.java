package Business_Logics;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;

import org.springframework.stereotype.Component;

@Component("LIC19046_PJT1001_PE_NLP3aec51cb-3408-4412-b70a-4163f3121d2c")
public class close_android_browser implements Nlp {
	  //  @InputParams({@InputParam(name = "element", type = "org.openqa.selenium.WebElement")})
	    //@ReturnType(name = "isDisplay", type = "java.lang.Boolean")

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
	        	 Runtime.getRuntime().exec("adb shell pm clear com.android.chrome");
	        	 nlpResponseModel.setStatus(CommonConstants.pass);
	        	 nlpResponseModel.setMessage("Successfully closed android browser");
	         }
	         catch(Exception e) {
	        	 nlpResponseModel.setStatus(CommonConstants.pass);
	        	 nlpResponseModel.setMessage("Failed to close android browser"+e);
	        	 
	         }
	         
	         //.getAttributes().put("isDisplay", isDisplay);
	          return nlpResponseModel;
	      }
}