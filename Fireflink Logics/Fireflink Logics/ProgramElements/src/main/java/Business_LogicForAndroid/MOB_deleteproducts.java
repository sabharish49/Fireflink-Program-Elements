package Business_LogicForAndroid;

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

import io.appium.java_client.android.AndroidDriver;

import org.springframework.stereotype.Component;

@Component("LIC1710_PJT1014_PE_NLP3c593a4b-3eed-4cc3-8d92-8ccd3d61eb8a")
public class MOB_deleteproducts implements Nlp {
	// @InputParams({@InputParam(name = "waitTime", type = "java.lang.String")})

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
		// String waitTime = (String) attributes.get("locatorValue");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("waitForIdleTimeout", 0);

		AndroidDriver driver = nlpRequestModel.getAndroidDriver();
		Duration impWait = driver.manage().timeouts().getImplicitWaitTimeout();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//android.view.ViewGroup/following-sibling::android.view.ViewGroup[2]/child::com.horcrux.svg.SvgView//com.horcrux.svg.l")));
			WebElement bagIcon = null;
			int trying = 1, tryForTimes = 5;
			while (trying <= tryForTimes) {
				try {
					bagIcon = driver.findElement(By.xpath(
							"//android.view.ViewGroup/following-sibling::android.view.ViewGroup[2]/child::com.horcrux.svg.SvgView//com.horcrux.svg.l"));
					break;
				} catch (Exception e) {
					trying++;
					Thread.sleep(500);
				}
			}
			bagIcon.click();

			// wait for spinner to disappear
			try {
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
						"//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageView")));
			} catch (Exception e) {

			}

			Thread.sleep(3000);
			boolean flag = true;
			while (flag) {
				try {
					WebElement deleteIcon = driver.findElement(By.xpath(
							"//android.widget.HorizontalScrollView[@index='0']//android.widget.TextView[contains(@text,'Qty')]/../../../..//android.view.ViewGroup[@index='2']//android.view.ViewGroup[@index='1']//com.horcrux.svg.u"));
					if (deleteIcon.isDisplayed()) {
						deleteIcon.click();

						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
								"//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageView")));
					}
				} catch (Exception e) {
					Thread.sleep(3000);

					// WebElement
					// oops=driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'OOPS!')]"));
					if (driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'OOPS!')]")).isDisplayed()
							|| flag == false) {
						// Thread.sleep(2000);
						WebElement backkey = driver.findElement(By.xpath(
								"(//android.widget.TextView[contains(@text,\"MY SHOPPING BAG\")]/../..//com.horcrux.svg.SvgView/child::com.horcrux.svg.l)[1]"));
						backkey.click();
					}
					nlpResponseModel.setStatus(CommonConstants.pass);
					nlpResponseModel.setMessage("No Products in My Bag");

				}
			}

		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to Remove Products From My Bag");
		} finally {
			driver.manage().timeouts().implicitlyWait(impWait);
		}
		return nlpResponseModel;
	}
}
