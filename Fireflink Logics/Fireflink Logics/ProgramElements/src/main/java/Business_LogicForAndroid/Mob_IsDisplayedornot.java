package Business_LogicForAndroid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;

import org.springframework.stereotype.Component;

@Component("LIC1710_PJT1014_PE_NLP35aa94dd-5cfd-4cfd-88e6-7ffa565aa2f9")
public class Mob_IsDisplayedornot implements Nlp {
	@InputParams({ @InputParam(name = "stringXpath", type = "java.lang.String") })
	@ReturnType(name = "getBoolean", type = "java.lang.Boolean")

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
		Boolean getBoolean = false;

		try {
			AndroidDriver driver = (AndroidDriver) nlpRequestModel.getDriver().getSpecificIDriver();
			String stringXpath = (String) attributes.get("stringXpath");
			Boolean condition = driver.findElement(By.xpath(stringXpath)).isDisplayed();
			if (condition == true) {
				getBoolean = true;
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Element Is Dispalyed In WebPage");
			} else {
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Element Is Not Dispalyed In WebPage");
			}
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Element Not Exist In WebPage");
		}

		nlpResponseModel.getAttributes().put("getBoolean", getBoolean);
		return nlpResponseModel;
	}
}
