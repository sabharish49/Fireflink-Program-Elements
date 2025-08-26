
package Business_Logic;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
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

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Slf4j

@Component("LIC23707_PJT1008_PE_NLP44bdbde3-4019-48b2-bbf9-aa02916f4dce")
public class fillQuickQuoteDetails implements Nlp {
	@InputParams({ @InputParam(name = "inputData", type = "java.lang.String"),@InputParam(name = "mobileNumber", type = "java.lang.String") })
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
		String input = (String) attributes.get("inputData");
		String mobileNumber = (String) attributes.get("mobileNumber");
		
		// Your program element business logic goes here ...
		boolean res = false;
		try {
			WebDriver driver = nlpRequestModel.getWebDriver();
			
			Duration implicitWait = driver.manage().timeouts().getImplicitWaitTimeout();
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));

			Map<String, String> map = new HashMap<>();
			String str = input.substring(1, input.length() - 1);

			try {
				String[] pairs = str.split(";");
				for (String pair : pairs) {
					String[] keyValue = pair.split("=");
					String key = keyValue[0].trim();
					String value = keyValue[1].trim();
					map.put(key, value);
				}
			} catch (Exception e) {

			}

			
			Actions action = new Actions(driver);
			
			String productCategory = map.get("productCategory");
			String chooseMembers = map.get("chooseMembers");
			String ages = map.get("ages");
			String email = map.get("email");

			driver.findElement(By.xpath("//input[contains(@dropdown,'ProductCategory')]")).click();
			String xpath="//li[contains(text(),'" + productCategory + "')]";
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath(xpath)));
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));

			wait.until(ExpectedConditions.elementToBeClickable(
					driver.findElement(By.xpath(xpath))));
		
		
			
			action.moveToElement(driver.findElement(By.xpath(xpath)));
			driver.findElement(By.xpath(xpath)).click();

			driver.findElement(By.xpath("//button[@id=\"next\"]")).click();

			driver.findElement(By.xpath("//input[contains(@placeholder,'choose members')]")).click();
			
			xpath="//li[contains(text(),'" + chooseMembers + "')]";
			wait.until(ExpectedConditions.elementToBeClickable(
					driver.findElement(By.xpath(xpath))));
			action.moveToElement(driver.findElement(By.xpath(xpath)));
			
			
			driver.findElement(By.xpath(xpath)).click();

			String[] age = ages.split(",");

			switch (chooseMembers) {
			case "Self": {
				driver.findElement(By.xpath("//input[contains(@name,\"age\")]")).sendKeys(age[0]);
				driver.findElement(By.xpath("//button[@id=\"next\"]")).click();

			}
				break;
			case "Self & Spouse": {
				driver.findElement(By.xpath("//input[contains(@name,\"age\")]")).sendKeys(age[0]);
				driver.findElement(By.xpath("//button[@id=\"next\"]")).click();
				driver.findElement(By.xpath("//input[@ng-model=\"pQA.preQuote.spouseAge\"]")).sendKeys(age[1]);
				driver.findElement(By.xpath("//button[@id=\"next\"]")).click();

			}
				break;
			case "Self & kids": {
				driver.findElement(By.xpath("//input[contains(@name,\"age\")]")).sendKeys(age[0]);
				driver.findElement(By.xpath("//button[@id=\"next\"]")).click();

				String[] noKids = age[1].split(":");
				driver.findElement(By.xpath("//input[@ng-model=\"pQA.preQuote.Kids\"]")).sendKeys(noKids[0]);
				driver.findElement(By.xpath("//button[@id=\"next\"]")).click();

				String[] childAge = noKids[1].split("-");
				int count = 0;
				for (int i = 1; i <= childAge.length; i++) {
					wait.until(ExpectedConditions.elementToBeClickable(
							driver.findElement(By.xpath("(//div[@class='input-group kid2']//input)[" + i + "]"))));

					driver.findElement(By.xpath("(//div[@class='input-group kid2']//input)[" + i + "]"))
							.sendKeys(childAge[count++]);
				}
				driver.findElement(By.xpath("//button[@id=\"next\"]")).click();

			}
				break;
			case "Self,spouse and kids": {
				driver.findElement(By.xpath("//input[contains(@name,\"age\")]")).sendKeys(age[0]);
				driver.findElement(By.xpath("//button[@id=\"next\"]")).click();
				driver.findElement(By.xpath("//input[@ng-model=\"pQA.preQuote.spouseAge\"]")).sendKeys(age[1]);
				driver.findElement(By.xpath("//button[@id=\"next\"]")).click();

				String[] noKids = age[2].split(":");
				driver.findElement(By.xpath("//input[@ng-model=\"pQA.preQuote.Kids\"]")).sendKeys(noKids[0]);
				driver.findElement(By.xpath("//button[@id=\"next\"]")).click();

				String[] childAge = noKids[1].split("-");
				int count = 0;
				for (int i = 1; i <= childAge.length; i++) {
					wait.until(ExpectedConditions.elementToBeClickable(
							driver.findElement(By.xpath("(//div[@class='input-group kid2']//input)[" + i + "]"))));
					driver.findElement(By.xpath("(//div[@class='input-group kid2']//input)[" + i + "]"))
							.sendKeys(childAge[count++]);
				}
				driver.findElement(By.xpath("//button[@id=\"next\"]")).click();

			}
				break;
			case "Spouse & kids": {
				driver.findElement(By.xpath("//input[contains(@name,\"age\")]")).sendKeys(age[0]);
				driver.findElement(By.xpath("//button[@id=\"next\"]")).click();
				driver.findElement(By.xpath("//input[@ng-model=\"pQA.preQuote.spouseAge\"]")).sendKeys(age[1]);
				driver.findElement(By.xpath("//button[@id=\"next\"]")).click();

				String[] noKids = age[2].split(":");
				driver.findElement(By.xpath("//input[@ng-model=\"pQA.preQuote.Kids\"]")).sendKeys(noKids[0]);
				driver.findElement(By.xpath("//button[@id=\"next\"]")).click();

				String[] childAge = noKids[1].split("-");
				int count = 0;
				for (int i = 1; i <= childAge.length; i++) {
					wait.until(ExpectedConditions.elementToBeClickable(
							driver.findElement(By.xpath("(//div[@class='input-group kid2']//input)[" + i + "]"))));
					driver.findElement(By.xpath("(//div[@class='input-group kid2']//input)[" + i + "]"))
							.sendKeys(childAge[count++]);

				}
				driver.findElement(By.xpath("//button[@id=\"next\"]")).click();
			}
				break;
			}

			wait.until(ExpectedConditions
					.visibilityOf(driver.findElement(By.xpath("//input[@ng-model=\"pQA.preQuote.MobileNo\"]"))));
			driver.findElement(By.xpath("//input[@ng-model=\"pQA.preQuote.MobileNo\"]")).sendKeys(mobileNumber);
			driver.findElement(By.xpath("//button[@id=\"next\"]")).click();

			wait.until(ExpectedConditions
					.visibilityOf(driver.findElement(By.xpath("//input[@ng-model=\"pQA.preQuote.EmailId\"]"))));
			driver.findElement(By.xpath("//input[@ng-model=\"pQA.preQuote.EmailId\"]")).sendKeys(email);

			wait.until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath("//button[@id=\"check-products\"]"))));
			driver.findElement(By.xpath("//button[@id=\"check-products\"]")).click();

			driver.manage().timeouts().implicitlyWait(implicitWait);
			res = true;

			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Details entered Successfully in Quick Quote Page");
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to enter details in Quick Quote Page "+ e);
		}

		nlpResponseModel.getAttributes().put("result", res);
		return nlpResponseModel;
	}
}