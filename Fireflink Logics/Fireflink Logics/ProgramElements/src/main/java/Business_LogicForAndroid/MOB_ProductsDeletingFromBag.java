package Business_LogicForAndroid;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
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
import io.appium.java_client.android.AndroidDriver;

import org.springframework.stereotype.Component;

@Component("LIC1710_PJT1014_PE_NLP6f021be5-cc87-4f61-9578-adcd00c29cbf")
public class MOB_ProductsDeletingFromBag implements Nlp {
	@InputParams({ @InputParam(name = "RemoveIconXpath", type = "java.lang.String") })


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

	public static void retryingFindClick(String Xpath, AndroidDriver driver) {
		int attempts = 0;
		while (attempts < 2) {
			try {
				driver.findElement(By.xpath(Xpath)).click();
				break;
			} catch (StaleElementReferenceException e) {

			} catch (ElementClickInterceptedException e) {

			} catch (ElementNotInteractableException e) {

			} catch (Exception e) {
				break;
			}
			attempts++;
		}
	}

	@Override
	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

		NlpResponseModel nlpResponseModel = new NlpResponseModel();
		Map<String, Object> attributes = nlpRequestModel.getAttributes();
		String RemoveIconXpath = (String) attributes.get("RemoveIconXpath");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("waitForIdleTimeout", 0);
		AndroidDriver driver = nlpRequestModel.getAndroidDriver();

		Duration impTime = driver.manage().timeouts().getImplicitWaitTimeout();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		WebElement removeButton = null;
		int count=0;
		try {
			 while(count<=25) { 
			           try { 	
			             removeButton = driver.findElement(By.xpath(RemoveIconXpath));
			             retryingFindClick(RemoveIconXpath, driver);
			         //    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/descendant::android.widget.FrameLayout/child::android.view.ViewGroup//android.widget.ImageView")));
			             wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//android.widget.TextView[contains(@text,'ITEM REMOVED FROM BAG')]")));
			             Thread.sleep(3000);
			             count++;
						   if(count==25) {
								 driver.navigate().back();
								 nlpResponseModel.setStatus(CommonConstants.fail);
								 nlpResponseModel.setMessage("Terminated The Loop");
								 break;
						   }	 
			           }
			           catch (Exception g) {
			        	   driver.navigate().back();
			        	   nlpResponseModel.setStatus(CommonConstants.pass);
			        	   nlpResponseModel.setMessage("Successfully Deleted All The Products And No Products Found");
			        	   break;	
					   }	         	      
			  }
		}
		catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Something Went Wrong "+e);
		}
		finally {
			driver.manage().timeouts().implicitlyWait(impTime);
		}
		return nlpResponseModel;

	}
}
