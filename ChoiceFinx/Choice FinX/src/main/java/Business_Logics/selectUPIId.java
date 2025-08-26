
package Business_Logics;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import org.springframework.stereotype.Component;

@Component("LIC20369_PJT1002_PE_NLPa794dfd6-190b-4e84-946b-b54263f2394c")
public class selectUPIId implements Nlp {
	@InputParams({ @InputParam(name = "Domain", type = "java.lang.String") })
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
		String Domain = (String) attributes.get("Domain");

		boolean b = false;

		AndroidDriver driver = nlpRequestModel.getAndroidDriver();
		Duration implicitWait = driver.manage().timeouts().getImplicitWaitTimeout();
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			String upiId = Domain;

			String parentXpath = null;
			String childXpath = null;
			String currentParentXpath = null;
			String currentChildXpath = null;

			for (int count = 0; count < 10; count++) {

				try {
					parentXpath = "(//android.widget.ListView//android.widget.LinearLayout//android.widget.LinearLayout)";

					List<WebElement> parentElements = driver.findElements(By.xpath(parentXpath));
					int sizeOfList = parentElements.size();
					int startIndex;
					int endIndex;

					try {
						childXpath = parentXpath + "[1]//android.widget.TextView";
						currentParentXpath = parentXpath + "[1]";
						if (driver.findElement(By.xpath(currentParentXpath)).isDisplayed()
								&& driver.findElement(By.xpath(childXpath)).isDisplayed()
								&& driver.findElement(By.xpath(childXpath)).getText().contains("")) {
							startIndex = 1;
						} else {
							startIndex = 2;
						}
					} catch (Exception e) {
						startIndex = 2;
					}

					Point destLoc = driver
							.findElement(By.xpath(parentXpath + "[" + startIndex + "]//android.widget.TextView"))
							.getLocation();
					System.out.println(destLoc);
					try {
						childXpath = parentXpath + "[last()]//android.widget.TextView";
						currentParentXpath = parentXpath + "[last()]";
						if (driver.findElement(By.xpath(currentParentXpath)).isDisplayed()
								&& driver.findElement(By.xpath(childXpath)).isDisplayed()
								&& driver.findElement(By.xpath(childXpath)).getText().contains("")) {
							endIndex = sizeOfList;
						} else {
							endIndex = sizeOfList - 1;
						}
					} catch (Exception e) {
						endIndex = sizeOfList - 1;
					}

					Point sourceLoc = driver
							.findElement(By.xpath(parentXpath + "[" + endIndex + "]//android.widget.TextView"))
							.getLocation();
					System.out.println(sourceLoc);

					for (int i = startIndex; i <= endIndex; i++) {
						currentChildXpath = parentXpath + "[" + i + "]//android.widget.TextView";
						if (driver.findElement(By.xpath(currentChildXpath)).getText().contains(upiId)) {
							System.out.println("Element found");
							driver.findElement(By.xpath(currentChildXpath)).click();
							b = true;
							break;
						} else {
							System.out.println(driver.findElement(By.xpath(currentChildXpath)).getText());
						}
					}
					if (b == true) {
						break;
					} else {
						swipeUp(driver, sourceLoc, destLoc);
					}

				}

				catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (b == true) {
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Successfully selected the required upi Domain");
			}
			else {
				nlpResponseModel.setStatus(CommonConstants.fail);
				nlpResponseModel.setMessage("Entered input Domain is not present in the Dropdown");
			}
				
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to select the Domain from Dropdown "+e);
		}
		finally {
			driver.manage().timeouts().implicitlyWait(implicitWait);
		}

		
		nlpResponseModel.getAttributes().put("result", b);
		return nlpResponseModel;
	}

	private static void swipeUp(AndroidDriver driver, Point sourceLoc, Point destLoc) {
		TouchAction touchAction = new TouchAction(driver);
		touchAction.press(PointOption.point(sourceLoc)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
				.moveTo(PointOption.point(destLoc)).release().perform();

	}
}