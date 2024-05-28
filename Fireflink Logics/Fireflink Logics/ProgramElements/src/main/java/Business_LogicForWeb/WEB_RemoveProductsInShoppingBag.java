package Business_LogicForWeb;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

@Component("LIC1710_PJT1014_PE_NLP473a7537-a232-4201-a4af-5017b63d845c")
public class WEB_RemoveProductsInShoppingBag implements Nlp {
	 @InputParams({	@InputParam(name = "RemoveIconxpath", type = "java.lang.String")})
    

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
	public  static void HandleClickIntercepted(String xpath,WebDriver driver) {
		 //   boolean result = false;
		    int attempts = 0;
		    while(attempts < 2) {
		        try {
		           driver.findElement(By.xpath(xpath)).click();   
		            break;
		        } catch(ElementClickInterceptedException e) {
		        	
		        }
               catch(StaleElementReferenceException e) {
		        	
		        }
		        attempts++;
		    }
	}

	@Override
	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

		NlpResponseModel nlpResponseModel = new NlpResponseModel();
		Map<String, Object> attributes = nlpRequestModel.getAttributes();
		WebDriver driver = nlpRequestModel.getWebDriver();
		String xpath=(String)attributes.get("RemoveIconxpath");
	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	//	Duration impTime = driver.manage().timeouts().getImplicitWaitTimeout();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
	
		WebElement removeButton=null;
		Boolean isDisplayed = null;
		Actions ac = new Actions(driver);
		int count = 0;
			 while(count <= 25) {
				 try { 	
				     removeButton = driver.findElement(By.xpath(xpath));
			         HandleClickIntercepted(xpath, driver);
				//   wait.until(ExpectedConditions.elementToBeClickable(removeButton));
				//   removeButton.click();
				     wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@alt='loader']")));
				       for(int i=0;i<=3;i++) {
				       ac.sendKeys(Keys.ARROW_DOWN).build().perform();
				       }
				       for(int i=0;i<=2;i++) {
			           ac.sendKeys(Keys.PAGE_UP).build().perform();
				       }
				  }catch (NoSuchElementException g) {
					  isDisplayed=driver.findElement(By.xpath("//*[text()='OOPS!']")).isDisplayed();
					  isDisplayed=true;
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
				  }catch (Exception f) {
						nlpResponseModel.setStatus(CommonConstants.fail);
						nlpResponseModel.setMessage("Failed to delete the products" + f);
						break;
			      } 
				 count++;
				 if(count==25) {
					 driver.navigate().back();
					 nlpResponseModel.setStatus(CommonConstants.fail);
					 nlpResponseModel.setMessage("Terminated The Loop");
					 break;
				 }	 
			 }	
		return nlpResponseModel;
	}
}
