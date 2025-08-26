
package business_logics;

import com.tyss.optimize.common.util.*;
import com.tyss.optimize.nlp.util.*;
import com.tyss.optimize.nlp.util.annotation.*;

import java.time.Duration;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;




public class Click_On_SubOptions implements Nlp {
	@InputParams({ @InputParam(name = "List", type = "java.util.List") })
	// @ReturnType(name = "Sum of Two numbers", type = "java.lang.Integer")

	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

		Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
		List<String> xpaths = (List<String>) programElementsInput.get("List");
		NlpResponseModel nlpResponseModel = new NlpResponseModel();

		WebDriver driver = nlpRequestModel.getWebDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {

			for (int i = 0; i < xpaths.size(); i++) {
				String value = xpaths.get(i);
				String xpath = "//input[@value='" + value + "']";
				System.out.println(xpath);
				// Locate the element using XPath
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();

			}
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Click action is Successfull");
			
		} catch (Exception e) {
			// Your program element Exception handling goes here ...
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Fail to perform click action");
			
		}

		// Your program element business logic ends here ...
		//nlpResponseModel.getAttributes().put("Sum of Two numbers", returnValue);
		return nlpResponseModel;
	}

}