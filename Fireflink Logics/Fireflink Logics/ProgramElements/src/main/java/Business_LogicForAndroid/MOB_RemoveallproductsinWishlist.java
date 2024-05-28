package Business_LogicForAndroid;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
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

@Component("LIC1710_PJT1014_PE_NLP9dc94346-3aee-4088-adb0-2a39d58f4ba2")
public class MOB_RemoveallproductsinWishlist implements Nlp {
//	 @InputParams({@InputParam(name = "brandDropdown", type = "java.lang.String"),
//		 @InputParam(name = "brandNames", type = "java.lang.String") })

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
		// Your program element business logic goes here ...
		AndroidDriver driver = nlpRequestModel.getAndroidDriver();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		Duration impTime = driver.manage().timeouts().getImplicitWaitTimeout();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

		Boolean flag = true;
		Boolean isDisplayed = null;
		int count=0;
		// WebElement deleteIcon = null;
		try {

			try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='ADD TO BAG']/../../..//com.horcrux.svg.u")));
				
				if (driver
						.findElement(
								By.xpath("//android.widget.TextView[@text='ADD TO BAG']/../../..//com.horcrux.svg.u"))
						.isDisplayed()) {
					isDisplayed = true;
					while (count<=25) {
						try {
							retryingFindClick(
									"//android.widget.TextView[@text='ADD TO BAG']/../../..//com.horcrux.svg.u",
									driver);
							// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='REMOVED
							// FROM WISH LIST']")));
						} catch (NoSuchElementException g) {
							flag = false;
							if (driver
									.findElement(
											By.xpath("//android.widget.TextView[contains(@text,'No product found')]"))
									.isDisplayed()) {
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
				driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'No product found')]"))
						.isDisplayed();
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("No Products Found");
				driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));

			}
		}

		catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to remove Products from Wishlist" + e);
		}
		finally {
			driver.manage().timeouts().implicitlyWait(impTime);
		}

		return nlpResponseModel;

	}
}
