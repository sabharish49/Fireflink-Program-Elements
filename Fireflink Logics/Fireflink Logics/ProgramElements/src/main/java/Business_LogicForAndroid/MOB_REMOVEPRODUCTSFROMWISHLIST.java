package Business_LogicForAndroid;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import io.appium.java_client.android.AndroidDriver;

import org.springframework.stereotype.Component;

@Component("LIC1710_PJT1014_PE_NLP47d22c44-0538-4690-aae2-c892d0dcd277")
public class MOB_REMOVEPRODUCTSFROMWISHLIST implements Nlp {
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
		// Your program element business logic goes here ...
		AndroidDriver driver = nlpRequestModel.getAndroidDriver();
	//	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		Duration impTime = driver.manage().timeouts().getImplicitWaitTimeout();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		
		Boolean isDisplayed = null;
		WebElement deleteIcon = null;
		int count = 0;
		try {
			 if(driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'No product found for') or contains(@text,'There are no products in your Wish list')]")).isDisplayed()){
			 isDisplayed=true; 
			 driver.navigate().back();
			 nlpResponseModel.setStatus(CommonConstants.pass);
			 nlpResponseModel.setMessage("No Products Found");
			}
		}
		catch (NoSuchElementException e) {
			    isDisplayed=false;
			    while(count<=25) {
				                  try {
				                  deleteIcon = driver.findElement(By.xpath(RemoveIconXpath));
				                  retryingFindClick(RemoveIconXpath, driver);
				 //               wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='REMOVED FROM WISH LIST']")));
				                  }catch (NoSuchElementException g) {
				    	          isDisplayed=driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'No product found for') or contains(@text,'There are no products in your Wish list')]")).isDisplayed();
				               	  if(isDisplayed==true) {
				    	          driver.navigate().back();
				    	          nlpResponseModel.setStatus(CommonConstants.pass);
				    	          nlpResponseModel.setMessage("Successfully Deleted All The Products And No Products Found");	
				    	          break;
				                  }
				    	          else {
				    		      isDisplayed=false;
				    	  	      nlpResponseModel.setStatus(CommonConstants.fail);
				    		      nlpResponseModel.setMessage("Failed To Delete All The Products "+g);
				    	          }
				                 }catch (Exception g) {
						          nlpResponseModel.setStatus(CommonConstants.fail);
						          nlpResponseModel.setMessage("Failed To Delete All The Products "+g);
				             	  break;
				   	             }
				                 count++;
				                 if(count==25) {
				    	         driver.navigate().back();
				    	         nlpResponseModel.setStatus(CommonConstants.fail);
				    	         nlpResponseModel.setMessage("Terminated The Loop");
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
