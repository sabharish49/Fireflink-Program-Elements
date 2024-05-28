package Business_logic;


import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component("LIC14172_PJT1001_PE_NLP51d0eb1f-8705-4838-83d8-a2f8355baea1")
public class MOB_CaptchaAutomation implements Nlp {
	@InputParams({@InputParam(name = "value", type = "java.lang.String"), @InputParam(name = "value1", type = "java.lang.String"),@InputParam(name = "operator", type = "java.lang.String")})
	@ReturnType(name = "result", type = "java.lang.Integer")

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
		String value = (String) attributes.get("value");
		String value1 = (String) attributes.get("value1");
		String operator = (String) attributes.get("operator");
		AppiumDriver driver = (AndroidDriver) nlpRequestModel.getDriver().getSpecificIDriver();
		
		int result=0;
		int a=Integer.parseInt(value);
		int b=Integer.parseInt(value1);

		// Your program element business logic goes here ...
		try {
			if(operator.equals("+")) {
				result=a+b;
				System.out.println("results"+" "+result);
			}
			else {
				result=a-b;
				System.out.println("results"+" "+result);
			}
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Sucessfully Handled The Numeric Captcha");
		}catch(Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to Handle The Numeric Captcha");
		}

		//  String string3 = "Return Value";
		nlpResponseModel.getAttributes().put("result", result);
		return nlpResponseModel;
	}
} 