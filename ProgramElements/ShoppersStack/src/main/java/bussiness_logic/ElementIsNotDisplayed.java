package bussiness_logic;
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



@Component("LIC14952_PJT1001_PE_NLP8d7d9b6a-a758-4349-bcb8-94c09fdca552")
public class ElementIsNotDisplayed implements Nlp {

	@InputParams({@InputParam(name = "element", type = "org.openqa.selenium.WebElement")})
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
		WebElement element = (WebElement) attributes.get("element");
		boolean result=true;
		WebDriver driver=nlpRequestModel.getWebDriver();
		// Your program element business logic goes here ...
		try {
			if(element.isDisplayed())
			{
				result=true;
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Element is displayed successfully");

			}
			else {
				result=false;
				nlpResponseModel.setStatus(CommonConstants.fail);
				nlpResponseModel.setMessage("Element is not displayed ");
			}
		}
		catch(Exception e)
		{
			result=false;
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Element is not displayed");
		}
		//String string3 = "Return Value";
		nlpResponseModel.getAttributes().put("result", result);
		return nlpResponseModel;
	}
}



