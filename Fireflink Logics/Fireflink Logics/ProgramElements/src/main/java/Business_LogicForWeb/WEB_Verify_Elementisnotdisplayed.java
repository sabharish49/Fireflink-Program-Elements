package Business_LogicForWeb;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

@Component("LIC1710_PJT1014_PE_NLPb0637dd0-e3cc-488b-b039-dfc2a3fbab5a")
public class WEB_Verify_Elementisnotdisplayed implements Nlp {
	@InputParams({ @InputParam(name = "element", type = "org.openqa.selenium.WebElement"),
			@InputParam(name = "elementType", type = "java.lang.String"),
			@InputParam(name = "elementName", type = "java.lang.String") })
	@ReturnType(name = "getboolean", type = "java.lang.Boolean")

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
		WebDriver driver = nlpRequestModel.getWebDriver();
		Boolean getboolean = false;
		try {
			
			element.isDisplayed();
			getboolean=true;
			if(getboolean=true) {
	
				nlpResponseModel.setStatus(CommonConstants.fail);
				nlpResponseModel.setMessage("Element Is Dispalyed In WebPage");
			}
			else {
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Element Is not Dispalyed In WebPage");
				
			}
		
		}
		 catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Element is not displayed in web page");

		}

		nlpResponseModel.getAttributes().put("IsDisplayed", getboolean);
		return nlpResponseModel;
	}
}
