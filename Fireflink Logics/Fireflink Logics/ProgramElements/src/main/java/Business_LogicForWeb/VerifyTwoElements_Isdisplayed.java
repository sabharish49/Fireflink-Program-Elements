package Business_LogicForWeb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import org.springframework.stereotype.Component;

@Component("LIC1710_PJT1014_PE_NLP878077d2-0a49-4a59-bed2-7acf426438f7")
public class VerifyTwoElements_Isdisplayed implements Nlp {
	@InputParams({ @InputParam(name = "stringXpath1", type = "java.lang.String"),
			@InputParam(name = "stringXpath2", type = "java.lang.String") })
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
		String stringXpath1 = (String) attributes.get("stringXpath1");
		String stringXpath2 = (String) attributes.get("stringXpath2");
		Boolean getBoolean = false;
		WebDriver driver = nlpRequestModel.getWebDriver();
		try {
			Boolean condition = driver.findElement(By.xpath(stringXpath1)).isDisplayed();
			condition = driver.findElement(By.xpath(stringXpath2)).isDisplayed();
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
