package Business_LogicForAndroid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
//@Slf4j
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;

@Component("LIC1710_PJT1014_PE_NLP800a94b4-697c-443c-85d6-abf99a44775b")
public class MOB_VerifyIfElementisDispalyedInAndroidScreen implements Nlp {
	@InputParams({ @InputParam(name = "xpath", type = "java.lang.String")})
	@ReturnType(name = "isDisplayed", type = "java.lang.Boolean")

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
		String xpath=(String) attributes.get("xpath");
		Boolean isDisplayed = null;
		
		try {
	//	log.info("===Execution started====");
			AndroidDriver driver=nlpRequestModel.getAndroidDriver();
	//		log.info("===Indentified the driver instance====");
			isDisplayed =driver.findElement(By.xpath(xpath)).isDisplayed();
	//		log.info("===Element is Displayed ====");
			if(isDisplayed==true) {
		//		log.info("===Element is Displayed on the UI====");
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Element Is Displayed in Android Screen");
			}
			else {
		//		log.info("===Fail to Identify the Element in the UI====");
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Element Is  Not Displayed in Android Screen");
			}
		} catch (Exception e) {
			isDisplayed = false;
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Element Is Not Displayed in Android Screen"+e);

		}

		nlpResponseModel.getAttributes().put("isDisplayed", isDisplayed);
		return nlpResponseModel;
	}
}
