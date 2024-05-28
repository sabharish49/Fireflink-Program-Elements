package Business_logics;


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

@Component("LIC14952_PJT1005_PE_NLP27e35329-7575-4e42-9716-58180e9aee62")
public class Switch implements Nlp {
	  @InputParams({@InputParam(name = "appPackage", type = "java.lang.String"),@InputParam(name = "appActivity", type = "java.lang.String")})
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
	          String appPackage = (String) attributes.get("appPackage");
	          String appActivity = (String) attributes.get("appActivity");
	          String command = String.format("adb shell am start -a android.intent.action.MAIN -c android.intent.category.LAUNCHER -n %s/%s", appPackage, appActivity);
	          try {
	              Runtime.getRuntime().exec(command);
	              System.out.println("Command executed successfully.");
	          } catch (Exception e) {
	              System.out.println("Error executing command: " + e.getMessage());
	          }
	          return nlpResponseModel;
	      }

}