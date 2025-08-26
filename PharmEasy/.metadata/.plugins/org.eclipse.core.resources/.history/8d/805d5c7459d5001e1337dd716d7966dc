
package Business_Logic;

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

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Component;

@Component("LIC18416_PJT1001_PE_NLP15962860-014c-4ce3-ab55-9add61f56d9e")
public class setDatatypeAndAssertsForParameters implements Nlp {
	@InputParams({ @InputParam(name = "ParametersList", type = "java.util.List"),
			@InputParam(name = "DatatypeAndAsserions", type = "java.lang.String") })
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
		List list = (List) attributes.get("ParametersList");
		String regex = (String) attributes.get("DatatypeAndAsserions");

		// Your program element business logic goes here ...
		boolean res = false;
		try {
			WebDriver driver = nlpRequestModel.getWebDriver();
			WebElement element = null;
			String parameter = null;

			Actions action = new Actions(driver);

			String[] split1 = regex.split(":");

			for (int i = 0; i < list.size(); i++) {
				parameter = (String) list.get(i);
				element = driver.findElement(By.xpath("//mat-label[text()='" + parameter
						+ "']/following-sibling::mat-form-field//mat-label[text()='Field Type']"));

				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView(true);", element);

				action.click(element).build().perform();

				String combination = split1[i];

				res = selectDataType(driver, combination, parameter, nlpRequestModel, nlpResponseModel);

			}

			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully selected data types and Assertions are set");

		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("failed to select data types and failed to set Assertions "+e);
		}

		nlpResponseModel.getAttributes().put("result", res);
		return nlpResponseModel;
	}

	public static boolean selectDataType(WebDriver driver, String combination, String parameter,
			NlpRequestModel nlpRequestModel, NlpResponseModel nlpResponseModel) throws InterruptedException {

		Actions action = new Actions(driver);
		boolean res = false;
		try {
			String[] split2 = null;
			String datatypeComb = null;
			String datatype = null;
			try {
				split2 = combination.split(";");
				datatypeComb = split2[0];

			} 
			catch (Exception e) {
				datatypeComb = combination;
			}

			try {
				String[] splitToggle = datatypeComb.split("_");
				datatype = splitToggle[0];

				res = false;
				while (res == false) {
					if (driver.findElement(By.xpath("//mat-option[contains(@class,'active')]//span")).getText()
							.contains(datatype)) {
						action.sendKeys(Keys.ENTER).build().perform();
						res = true;
					} else {
						Thread.sleep(500);
						action.sendKeys(Keys.ARROW_DOWN).build().perform();
					}

				}

				for (int i = 1; i <= splitToggle.length; i++) {
					try {
						System.out.println("entered inside toggle");
						Thread.sleep(500);
						if (splitToggle[i].equals("Array")) {
							System.out.println("about to click arr");
							driver.findElement(By.xpath(
									"(//mat-label[text()='" + parameter + "']/following-sibling::mat-slide-toggle)[1]"))
									.click();
						}
						if (splitToggle[i].equals("Null")) {
							System.out.println("about to click null");
							driver.findElement(By.xpath(
									"(//mat-label[text()='" + parameter + "']/following-sibling::mat-slide-toggle)[2]"))
									.click();
						}
					} catch (Exception e) {
						// datatype = splitToggle[0];
					}
				}

			} catch (Exception e) {
				datatype = combination;
				selectOnlyDataTypeWithNoAssertion(datatype, driver);
			}
			
			String[] split3 = null;
			String[] split4 = null;
			String operator = null;
			String value = null;
			try {
				split3 = split2[1].split("'");
			} catch (Exception e) {

				split4 = split2[1].split("=");
				operator = split4[0];
				value = split4[1];
				setSingleAssertion(driver, operator, value);

			}

			driver.findElement(By.xpath(
					"//mat-label[text()='" + parameter + "']/following-sibling::div//mat-icon[text()='library_add']"))
					.click();

			for (int k = 1; k <= split3.length; k++) {
				split4 = split3[k - 1].split("=");
				operator = split4[0];
				value = split4[1];

				driver.findElement(
						By.xpath("(//mat-label[text()='Operator'])[" + k + "]/following-sibling::mat-form-field"))
						.click();
				res = false;
				while (res == false) {
					if (driver.findElement(By.xpath("//mat-option[contains(@class,'active')]//span")).getText()
							.contains(operator)) {
						action.sendKeys(Keys.ENTER).build().perform();
						driver.findElement(By.xpath(
								"(//mat-label[text()='Value'])[" + k + "]/following-sibling::mat-form-field//input"))
								.sendKeys(value);
						res = true;
					} else {
						Thread.sleep(500);
						action.sendKeys(Keys.ARROW_DOWN).build().perform();
					}
				}
				Thread.sleep(500);
				if (k != split3.length) {
					driver.findElement(By.xpath("//span[text()='Add']")).click();
				}

			}

			driver.findElement(By.xpath("//span[text()='Submit']")).click();

		
			res=true;
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return res;
	}

	public static void selectOnlyDataTypeWithNoAssertion(String datatype, WebDriver driver)
			throws InterruptedException {

		boolean res = false;
		Actions action = new Actions(driver);
		while (res == false) {
			if (driver.findElement(By.xpath("//mat-option[contains(@class,'active')]//span")).getText()
					.contains(datatype)) {
				action.sendKeys(Keys.ENTER).build().perform();
				res = true;
			} else {
				Thread.sleep(500);
				action.sendKeys(Keys.ARROW_DOWN).build().perform();
			}
		}

	}
	public static void setSingleAssertion(WebDriver driver, String operator, String value) throws InterruptedException {
		boolean res = false;
		Actions action = new Actions(driver);

		driver.findElement(By.xpath("(//mat-label[text()='Operator'])[1]/following-sibling::mat-form-field")).click();
		while (res == false) {
			if (driver.findElement(By.xpath("//mat-option[contains(@class,'active')]//span")).getText()
					.contains(operator)) {
				action.sendKeys(Keys.ENTER).build().perform();
				driver.findElement(
						By.xpath("(//mat-label[text()='Value'])[1]/following-sibling::mat-form-field//input"))
						.sendKeys(value);
				res = true;
			} else {
				Thread.sleep(500);
				action.sendKeys(Keys.ARROW_DOWN).build().perform();
			}
		}
		Thread.sleep(500);

		driver.findElement(By.xpath("//span[text()='Submit']")).click();
	}


}
