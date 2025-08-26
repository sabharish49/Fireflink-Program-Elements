
package Business_Logic;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import org.springframework.stereotype.Component;

@Component("LIC19774_PJT1002_PE_NLP4b5c6e8f-48a2-4be3-b466-a238305500fd")
public class selectOptionalCoversAndHealthAddOns implements Nlp {
	@InputParams({ @InputParam(name = "inputData", type = "java.lang.String") })
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
		String inputData = (String) attributes.get("inputData");
		boolean res = false;
		try {
			WebDriver driver = nlpRequestModel.getWebDriver();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'more Covers')]")));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);",
					driver.findElement(By.xpath("//span[contains(text(),'more Covers')]")));
			driver.findElement(By.xpath("//span[contains(text(),'more Covers')]")).click();
			List<WebElement> removeElements = driver
					.findElements(By.xpath("//span[contains(text(),'Remove')]/following-sibling::input"));

			for (WebElement webElement : removeElements) {
				js.executeScript("arguments[0].scrollIntoView(true);", webElement);
				webElement.click();
			}

			String input = inputData;
			String[] AddOns = input.split(":");

			List<WebElement> optionalCovers = driver.findElements(By.xpath(
					"//h2[contains(text(),'Optional Covers')]/../following-sibling::div//h4 | //h2[contains(text(),'Health Add-Ons')]/../following-sibling::div//h4"));

			for (WebElement webElement : optionalCovers) {
				String text = webElement.getText();
				for (String value : AddOns) {
					if (value.equals(text)) {
						js.executeScript("arguments[0].scrollIntoView(true);", driver
								.findElement(By.xpath("//h4[text()='" + text + "']/../preceding-sibling::div//input")));
						driver.findElement(
								By.xpath("//h4[contains(text(),'" + text + "')]/../preceding-sibling::div//input"))
								.click();
					}
				}
				
				
				
				
				
				
				
				
				
				
				
			}

			res = true;
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Option covers and Health AddOns set successfully");

		} catch (Exception e) {
			res = false;
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to set Option covers and Health AddOns ");
		}

		nlpResponseModel.getAttributes().put("result", res);
		return nlpResponseModel;
	}
}