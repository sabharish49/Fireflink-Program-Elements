package Business_LogicForAndroid;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import org.springframework.stereotype.Component;

@Component("LIC1710_PJT1014_PE_NLP3a8fb009-08fe-4dea-a30b-24dc52e8a0f9")
public class Mob_remove_all_productsinBag implements Nlp {


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

	public static Boolean retryingFindClick(String Xpath, AndroidDriver driver) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 2) {
			try {
				driver.findElement(By.xpath(Xpath)).click();
				result = true;
				break;
			} catch (StaleElementReferenceException e) {

			} catch (ElementClickInterceptedException e) {

			}
			attempts++;
		}
		return result;
	}

	@Override
	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

		NlpResponseModel nlpResponseModel = new NlpResponseModel();
		Map<String, Object> attributes = nlpRequestModel.getAttributes();

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("waitForIdleTimeout", 0);
	
		AndroidDriver driver = nlpRequestModel.getAndroidDriver();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));

		Duration impTime = driver.manage().timeouts().getImplicitWaitTimeout();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

		Boolean flag = null;
		Boolean isDisplayed = null;
		int count=0;
	
		try {

			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.HorizontalScrollView[@index='0']//android.widget.TextView[contains(@text,'Qty')]/../../../..//android.view.ViewGroup[@index='2']//android.view.ViewGroup[@index='1']//com.horcrux.svg.u")));
	
				if (driver.findElement(By.xpath(
						"//android.widget.HorizontalScrollView[@index='0']//android.widget.TextView[contains(@text,'Qty')]/../../../..//android.view.ViewGroup[@index='2']//android.view.ViewGroup[@index='1']//com.horcrux.svg.u"))
						.isDisplayed()) {
					isDisplayed = true;
					while (count<=25) {
						try {
							retryingFindClick(
									"//android.widget.HorizontalScrollView[@index='0']//android.widget.TextView[contains(@text,'Qty')]/../../../..//android.view.ViewGroup[@index='2']//android.view.ViewGroup[@index='1']//com.horcrux.svg.u",
									driver);
							
						} catch (NoSuchElementException g) {
							flag = false;
							if (driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'OOPS!')]"))
									.isDisplayed()) {
								Thread.sleep(2000);
								driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
								nlpResponseModel.setStatus(CommonConstants.pass);
								nlpResponseModel.setMessage("Succesfully deleted all products in bag");
							}
						} catch (Exception e) {
							nlpResponseModel.setStatus(CommonConstants.fail);
							nlpResponseModel.setMessage("Failed To Delete All The Products " + e);

						}
					}

				}
			}

			catch (NoSuchElementException e) {
				isDisplayed = false;
				driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'OOPS!')]")).isDisplayed();
				Thread.sleep(2000);
				driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("No Products Found");

			}
		}

		catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to remove Products from Myshopping bag" + e);
		}
		finally {
			driver.manage().timeouts().implicitlyWait(impTime);
		}
		return nlpResponseModel;

	}
}
