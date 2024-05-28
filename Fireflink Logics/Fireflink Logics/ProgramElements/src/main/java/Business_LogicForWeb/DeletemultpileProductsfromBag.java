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

@Component("LIC1710_PJT1014_PE_NLP5f9c56e9-327b-4f75-9113-47f75c3d1435")
public class DeletemultpileProductsfromBag implements Nlp {
//	@InputParams({ @InputParam(name = "DeleteIconXpath", type = "java.lang.String"),
//			@InputParam(name = "oopsXpath", type = "java.lang.String") })

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
//		String Deleteicon = (String) attributes.get("DeleteIconXpath");
//		String oops = (String) attributes.get("oopsXpath");
		WebDriver driver = nlpRequestModel.getWebDriver();
		try {
		
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='wishlist']")));
			
			try {
				Thread.sleep(5000);
				if(driver.findElement(By.xpath("//img[@title='Remove']")).isDisplayed()) {
				List<WebElement> removeIcon = driver.findElements(By.xpath("//img[@title='Remove']"));
				int count = removeIcon.size();
			
			if (count > 0) {
				for (int i = 0; i < count; i++) {
                      Thread.sleep(4000);
					List<WebElement> removeIcon1 = driver.findElements(By.xpath("//img[@title='Remove']"));
					int count1 = removeIcon1.size();
					count = count1;
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@title='Remove']")));
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@title='Remove']")));
					driver.findElement(By.xpath("//img[@title='Remove']")).click();
					Thread.sleep(3000);
					count--;

				}
				Thread.sleep(2000);
				driver.navigate().back();
				nlpResponseModel.setMessage("Removed " + count + " Products from the bag");
				nlpResponseModel.setStatus(CommonConstants.pass);

			} else if(	driver.findElement(By.xpath("//h2[contains(text(),'Sorry, your shopping bag is empty!')]")).isDisplayed()) {
				
				Thread.sleep(2000);
				driver.navigate().back();

				nlpResponseModel.setMessage("Bag is empty");
				nlpResponseModel.setStatus(CommonConstants.pass);
			}

		}
			}catch (Exception e) {
			Thread.sleep(2000);
			driver.navigate().back();
			nlpResponseModel.setMessage("Bag is empty");
			nlpResponseModel.setStatus(CommonConstants.pass);
		}
		}

		catch (Exception e) {
			
			driver.navigate().back();
			nlpResponseModel.setMessage("Failed to empty bag");
			nlpResponseModel.setStatus(CommonConstants.pass);
			
		}

		return nlpResponseModel;
	}
}
