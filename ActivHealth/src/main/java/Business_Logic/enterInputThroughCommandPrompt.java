
package Business_Logic;

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

import org.springframework.stereotype.Component;

@Component("LIC19774_PJT1001_PE_NLPe7c537b4-b259-4fd5-9bbb-359748afce7a")
public class enterInputThroughCommandPrompt implements Nlp {
	@InputParams({ @InputParam(name = "Input", type = "java.lang.String") })
	@ReturnType(name = "result", type = "java.lang.Boolean")

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
		String Input = (String) attributes.get("Input");
		
		boolean res=false;
		try {
			AndroidDriver driver = nlpRequestModel.getAndroidDriver();
			String adbCommand = "adb shell input text '"+Input+"'"
					+ " -c android.intent.category.LAUNCHER"
					+ " -n com.apacfin.newalpha/com.apacfin.newalpha.view.basic.splash.SplashActivity";
			try {
				Process process = Runtime.getRuntime().exec(adbCommand);
				process.waitFor();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			res=true;
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Input entered Successfully");
			
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to enter input");
		}
		nlpResponseModel.getAttributes().put("result", res);
		return nlpResponseModel;
	}
}