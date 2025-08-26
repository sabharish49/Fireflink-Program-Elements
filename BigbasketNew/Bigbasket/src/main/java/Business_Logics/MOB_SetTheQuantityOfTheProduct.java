
package Business_Logics;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
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

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import org.springframework.stereotype.Component;

@Component("LIC20665_PJT1001_PE_NLPa7fc8379-3713-428e-bcf9-39efad7e9cdc")
public class MOB_SetTheQuantityOfTheProduct implements Nlp {
	@InputParams({ @InputParam(name = "Quantity", type = "java.lang.String"),@InputParam(name = "ProductName", type = "java.lang.String")})
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
		String Quantity = (String) attributes.get("Quantity");
		String ProductName = (String) attributes.get("ProductName");

		AndroidDriver androidDriver = nlpRequestModel.getAndroidDriver();
		IOSDriver iosDriver = nlpRequestModel.getIosDriver();

		boolean res = false;

		try {
			if (androidDriver != null) {
				WebDriverWait wait = new WebDriverWait(androidDriver, Duration.ofSeconds(20));

				String productName = ProductName;
				String inputValue = Quantity;

				int val1 = Integer.parseInt(inputValue);

				
				while (res == false) {
					try {
						String cartValue = androidDriver.findElement(By.xpath(
								"(//android.view.View[@content-desc='cartCount']//android.view.View)|//android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/txtProductDesc' and contains(@text,'"
										+ productName
										+ "')]/following-sibling::android.widget.RelativeLayout[@resource-id='com.bigbasket.mobileapp:id/addToCartCustomLayoutContainer']//android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/txtInBasket']"))
								.getAttribute("content-desc");
						if (cartValue == null) {
							cartValue = androidDriver.findElement(By.xpath(
									"(//android.view.View[@content-desc='cartCount']//android.view.View)|//android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/txtProductDesc' and contains(@text,'"
											+ productName
											+ "')]/following-sibling::android.widget.RelativeLayout[@resource-id='com.bigbasket.mobileapp:id/addToCartCustomLayoutContainer']//android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/txtInBasket']"))
									.getText();
						}

						int val2 = Integer.parseInt(cartValue);
						if (val1 > val2) {

							androidDriver.findElement(By.xpath(
									"//*[@content-desc='incrementProductButton'] | //android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/txtProductDesc' and contains(@text,'"
											+ productName
											+ "')]/following-sibling::android.widget.RelativeLayout[@resource-id='com.bigbasket.mobileapp:id/addToCartCustomLayoutContainer']//android.widget.ImageView[@resource-id='com.bigbasket.mobileapp:id/viewIncBasketQty']"))
									.click();
							wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
									"(//android.view.View[@content-desc='cartCount']//android.view.View)|//android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/txtProductDesc' and contains(@text,'"
											+ productName
											+ "')]/following-sibling::android.widget.RelativeLayout[@resource-id='com.bigbasket.mobileapp:id/addToCartCustomLayoutContainer']//android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/txtInBasket']")));
							wait.until(ExpectedConditions.visibilityOf(androidDriver.findElement(By.xpath(
									"//*[@content-desc='incrementProductButton'] | //android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/txtProductDesc' and contains(@text,'"
											+ productName
											+ "')]/following-sibling::android.widget.RelativeLayout[@resource-id='com.bigbasket.mobileapp:id/addToCartCustomLayoutContainer']//android.widget.ImageView[@resource-id='com.bigbasket.mobileapp:id/viewIncBasketQty']"))));

						} else if (val1 < val2) {

							androidDriver.findElement(By.xpath(
									"//*[@content-desc='decrementProductButton'] | //android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/txtProductDesc' and contains(@text,'"
											+ productName
											+ "')]/following-sibling::android.widget.RelativeLayout[@resource-id='com.bigbasket.mobileapp:id/addToCartCustomLayoutContainer']//android.widget.ImageView[@resource-id='com.bigbasket.mobileapp:id/viewDecBasketQty']"))
									.click();
							wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
									"(//android.view.View[@content-desc='cartCount']//android.view.View)|//android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/txtProductDesc' and contains(@text,'"
											+ productName
											+ "')]/following-sibling::android.widget.RelativeLayout[@resource-id='com.bigbasket.mobileapp:id/addToCartCustomLayoutContainer']//android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/txtInBasket']")));
							wait.until(ExpectedConditions.visibilityOf(androidDriver.findElement(By.xpath(
									"//*[@content-desc='decrementProductButton'] | //android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/txtProductDesc' and contains(@text,'"
											+ productName
											+ "')]/following-sibling::android.widget.RelativeLayout[@resource-id='com.bigbasket.mobileapp:id/addToCartCustomLayoutContainer']//android.widget.ImageView[@resource-id='com.bigbasket.mobileapp:id/viewDecBasketQty']"))));

						} else if (val1 == val2) {
							wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
									"(//android.view.View[@content-desc='cartCount']//android.view.View)|//android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/txtProductDesc' and contains(@text,'"
											+ productName
											+ "')]/following-sibling::android.widget.RelativeLayout[@resource-id='com.bigbasket.mobileapp:id/addToCartCustomLayoutContainer']//android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/txtInBasket']")));
							res = true;
							break;
						}

						System.out.println("val1 : " + val1);
						System.out.println("val2 : " + val2);
					} catch (Exception e) {
						
						System.out.println("Products limit got reached or Prdouct is removed");
						break;
					}

				}

			} else {
				WebDriverWait wait = new WebDriverWait(iosDriver, Duration.ofSeconds(20));

				String productName = ProductName;
				String inputValue = Quantity;

				int val1 = Integer.parseInt(inputValue);

				
				while (res == false) {
					try {
						String cartValue = iosDriver.findElement(By.xpath(
								"//XCUIElementTypeOther[@name=\"cartCount\"]/following-sibling::XCUIElementTypeStaticText | //android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/txtProductDesc' and contains(@text,'"
										+ productName
										+ "')]/following-sibling::android.widget.RelativeLayout[@resource-id='com.bigbasket.mobileapp:id/addToCartCustomLayoutContainer']//android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/txtInBasket']"))
								.getAttribute("value");
						if (cartValue == null) {
							cartValue = iosDriver.findElement(By.xpath(
									"//XCUIElementTypeOther[@name=\"cartCount\"]/following-sibling::XCUIElementTypeStaticText | //android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/txtProductDesc' and contains(@text,'"
											+ productName
											+ "')]/following-sibling::android.widget.RelativeLayout[@resource-id='com.bigbasket.mobileapp:id/addToCartCustomLayoutContainer']//android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/txtInBasket']"))
									.getText();
						}

						int val2 = Integer.parseInt(cartValue);
						if (val1 > val2) {

							iosDriver.findElement(By.xpath(
									"//XCUIElementTypeButton[@name=\"incrementProductButton\"] | //android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/txtProductDesc' and contains(@text,'"
											+ productName
											+ "')]/following-sibling::android.widget.RelativeLayout[@resource-id='com.bigbasket.mobileapp:id/addToCartCustomLayoutContainer']//android.widget.ImageView[@resource-id='com.bigbasket.mobileapp:id/viewIncBasketQty']"))
									.click();
							wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
									"//XCUIElementTypeOther[@name=\"cartCount\"]/following-sibling::XCUIElementTypeStaticText | //android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/txtProductDesc' and contains(@text,'"
											+ productName
											+ "')]/following-sibling::android.widget.RelativeLayout[@resource-id='com.bigbasket.mobileapp:id/addToCartCustomLayoutContainer']//android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/txtInBasket']")));
							wait.until(ExpectedConditions.visibilityOf(iosDriver.findElement(By.xpath(
									"//XCUIElementTypeButton[@name=\"incrementProductButton\"] | //android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/txtProductDesc' and contains(@text,'"
											+ productName
											+ "')]/following-sibling::android.widget.RelativeLayout[@resource-id='com.bigbasket.mobileapp:id/addToCartCustomLayoutContainer']//android.widget.ImageView[@resource-id='com.bigbasket.mobileapp:id/viewIncBasketQty']"))));

						} else if (val1 < val2) {

							iosDriver.findElement(By.xpath(
									"//XCUIElementTypeButton[@name=\"decrementProductButton\"] | //android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/txtProductDesc' and contains(@text,'"
											+ productName
											+ "')]/following-sibling::android.widget.RelativeLayout[@resource-id='com.bigbasket.mobileapp:id/addToCartCustomLayoutContainer']//android.widget.ImageView[@resource-id='com.bigbasket.mobileapp:id/viewDecBasketQty']"))
									.click();
							wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
									"//XCUIElementTypeOther[@name=\"cartCount\"]/following-sibling::XCUIElementTypeStaticText | //android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/txtProductDesc' and contains(@text,'"
											+ productName
											+ "')]/following-sibling::android.widget.RelativeLayout[@resource-id='com.bigbasket.mobileapp:id/addToCartCustomLayoutContainer']//android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/txtInBasket']")));
							wait.until(ExpectedConditions.visibilityOf(iosDriver.findElement(By.xpath(
									"//XCUIElementTypeButton[@name=\"decrementProductButton\"] | //android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/txtProductDesc' and contains(@text,'"
											+ productName
											+ "')]/following-sibling::android.widget.RelativeLayout[@resource-id='com.bigbasket.mobileapp:id/addToCartCustomLayoutContainer']//android.widget.ImageView[@resource-id='com.bigbasket.mobileapp:id/viewDecBasketQty']"))));

						} else if (val1 == val2) {
							wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
									"//XCUIElementTypeOther[@name=\"cartCount\"]/following-sibling::XCUIElementTypeStaticText | //android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/txtProductDesc' and contains(@text,'"
											+ productName
											+ "')]/following-sibling::android.widget.RelativeLayout[@resource-id='com.bigbasket.mobileapp:id/addToCartCustomLayoutContainer']//android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/txtInBasket']")));
							res = true;
							break;
						}

						System.out.println("val1 : " + val1);
						System.out.println("val2 : " + val2);
					}   catch (Exception e) {
						System.out.println("Products limit got reached or Prdouct is removed");
						break;
					}

				}

			}

			if (res == true) {
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Successfully applied the Quantity to the Product");
			} else {
				nlpResponseModel.setStatus(CommonConstants.fail);
				nlpResponseModel.setMessage("Provide Valid inputs");
			}

		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to Apply Quantity " + e);
		}

		nlpResponseModel.getAttributes().put("result", res);
		return nlpResponseModel;
	}
}