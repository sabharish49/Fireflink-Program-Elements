package WebBusinessLogics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;



public class Enterdate implements Nlp {
	@InputParams({ @InputParam(name = "Element", type = "org.openqa.selenium.WebElement"),
			@InputParam(name = "Date", type = "java.lang.String") })
	// @ReturnType(name = "string3", type = "java.lang.String")

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
		WebElement dateInput = (WebElement) attributes.get("Element");
		String dateValue = (String) attributes.get("Date");

		WebDriver driver = nlpRequestModel.getWebDriver();
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].value = arguments[1];", dateInput, dateValue);
			js.executeScript("arguments[0].dispatchEvent(new Event('input'));", dateInput);
			js.executeScript("arguments[0].dispatchEvent(new Event('change'));", dateInput);
			js.executeScript("arguments[0].dispatchEvent(new Event('blur'));", dateInput); 

			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully Entered date");
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to enter date " + e);
		}

//		String string3 = "Return Value";
//		nlpResponseModel.getAttributes().put("string3", string3);
		return nlpResponseModel;
	}
}