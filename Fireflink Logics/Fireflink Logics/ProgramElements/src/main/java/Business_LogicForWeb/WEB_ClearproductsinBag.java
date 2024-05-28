package Business_LogicForWeb;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

@Component("LIC1710_PJT1014_PE_NLPb44d6cee-55fe-4d53-9e19-f02d4b76406e")
public class WEB_ClearproductsinBag implements Nlp {
	@InputParams({ @InputParam(name = "DeleteIcon", type = "java.lang.String") })

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
		String DeleteIcon = (String) attributes.get("DeleteIcon");
		WebDriver driver = nlpRequestModel.getWebDriver();

		// Your program element business logic goes here ...
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> numDeleteIcons = driver.findElements(By.xpath(DeleteIcon));
		int size = numDeleteIcons.size();
		int i = 0;
		try {
			if (numDeleteIcons.size() > 0) {
				for (i = 1; i <= numDeleteIcons.size(); i++) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DeleteIcon)));
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath(DeleteIcon))).click();
					Thread.sleep(3000);
					nlpResponseModel.setMessage(numDeleteIcons.size() + "  Product Deleted Successfully");
					nlpResponseModel.setStatus(CommonConstants.pass);
				}
			} else {
				driver.navigate().refresh();
				if (driver.findElement(By.xpath("//h2[contains(text(),'Sorry, your shopping bag is empty!')]"))
						.isDisplayed()) {
					nlpResponseModel.setMessage("Oops sorry no products found");
					nlpResponseModel.setStatus(CommonConstants.pass);
				}
			}
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to delete the product from bag " + size + " and " + i + " " + e);
		}

		return nlpResponseModel;
	}
}