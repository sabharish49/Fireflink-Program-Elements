package Business_Logics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Component("LIC20665_PJT1001_PE_NLP85cccd4d-bbad-45f2-b4d3-782df7101340")
public class MOB_EnterInputintoElement implements Nlp {
	@InputParams({ @InputParam(name = "element", type = "org.openqa.selenium.WebElement"),
			@InputParam(name = "AndroidAttribute", type = "java.lang.String"),
			@InputParam(name = "IosAttribute", type = "java.lang.String"),
			@InputParam(name = "ExpectedText", type = "java.lang.String") })
	@ReturnType(name = "Result", type = "java.lang.Boolean")

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

		WebElement element = (WebElement) attributes.get("element");
		String AndroidAttribute = (String) attributes.get("AndroidAttribute");
		String IosAttribute = (String) attributes.get("IosAttribute");
		String ExpectedText = (String) attributes.get("ExpectedText");

		AndroidDriver androidDriver = nlpRequestModel.getAndroidDriver();
		IOSDriver iosDriver = nlpRequestModel.getIosDriver();

		boolean result = false;
		String attributeValue = null;
		try {

			if (androidDriver != null) {
				if (AndroidAttribute.equalsIgnoreCase("text")) {
					attributeValue = element.getText();
				} else {
					attributeValue = element.getAttribute(AndroidAttribute);
				}

				if (attributeValue.contains(ExpectedText)) {
					result = true;
					nlpResponseModel.setStatus(CommonConstants.pass);
					nlpResponseModel.setMessage("Succesfully Fetched the Attribute Value of the Element");
				} else {
					result = false;
					nlpResponseModel.setStatus(CommonConstants.fail);
					nlpResponseModel.setMessage("Failed to Fetch the Attribute Value of the Element");
				}

			} else {
				if (IosAttribute.equalsIgnoreCase("label")) {
					attributeValue = element.getText();
				} else {
					attributeValue = element.getAttribute(IosAttribute);
				}

				if (attributeValue.contains(ExpectedText)) {
					result = true;
					nlpResponseModel.setStatus(CommonConstants.pass);
					nlpResponseModel.setMessage("Succesfully Fetched the Attribute Value of the Element");
				} else {
					result = false;
					nlpResponseModel.setStatus(CommonConstants.fail);
					nlpResponseModel.setMessage("Failed to Fetch the Attribute Value of the Element");
				}
			}

			

		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to Fetch the Attribute Value of the Element "+e);
		}

		nlpResponseModel.getAttributes().put("Result", result);
		return nlpResponseModel;
	}
}
