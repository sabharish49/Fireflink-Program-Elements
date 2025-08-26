
package logics;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
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

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;



import org.springframework.stereotype.Component;

@Component("LIC20665_PJT1001_PE_NLPa441deca-8596-4c60-9f5e-1d46c8837adb")
public class MOB_SetTheQuantityOfTheProduct implements Nlp {
	@InputParams({ @InputParam(name = "Quantity", type = "java.lang.String") })
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

		AndroidDriver androidDriver = nlpRequestModel.getAndroidDriver();
		IOSDriver iosDriver = nlpRequestModel.getIosDriver();
		boolean res = false;
		
		try {
			if (androidDriver != null) {
				String inputValue = Quantity;
				int val1 = Integer.parseInt(inputValue);

				WebDriverWait wait = new WebDriverWait(androidDriver, Duration.ofSeconds(5));

				WebElement incrementButton = androidDriver
						.findElement(By.xpath("//android.widget.ImageView[@content-desc='incrementProductButton']"));
				WebElement decrementButton = androidDriver
						.findElement(By.xpath("//android.widget.Button[@content-desc='decrementProductButton']"));

				
				while (res == false) {
					String cartValue = androidDriver
							.findElement(By.xpath("//android.view.View[@content-desc='cartCount']//android.view.View"))
							.getAttribute("content-desc");
					int val2 = Integer.parseInt(cartValue);
					if (val1 > val2) {
						incrementButton.click();
						wait.until(ExpectedConditions.presenceOfElementLocated(
								By.xpath("//android.view.View[@content-desc='cartCount']//android.view.View")));

					} else if (val1 < val2) {
						decrementButton.click();
						wait.until(ExpectedConditions.presenceOfElementLocated(
								By.xpath("//android.view.View[@content-desc='cartCount']//android.view.View")));
					} else if (val1 == val2) {
						wait.until(ExpectedConditions.presenceOfElementLocated(
								By.xpath("//android.view.View[@content-desc='cartCount']//android.view.View")));
						res = true;
						break;
					}
					else if(incrementButton.isDisplayed()==false && res==false) {
						break;
					}

				}
			} else {

			}
			
			if(res==true) {
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully applied the Quantity to the Product");
			}
			else {
				nlpResponseModel.setStatus(CommonConstants.fail);
				nlpResponseModel.setMessage("Provide Valid inputs");
			}

		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to Apply Quantity "+ e);
		}

		nlpResponseModel.getAttributes().put("result", res);
		return nlpResponseModel;
	}
}