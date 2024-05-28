package Business_LogicForWeb;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
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

@Component("LIC1710_PJT1014_PE_NLP746fcdbe-9020-488f-90db-cae9cc7fe34f")
public class Web_DeleteProductsinWishlistpage implements Nlp {
// @InputParams({@InputParam(name = "brandDropdown", type = "java.lang.String"),
//	 @InputParam(name = "brandNames", type = "java.lang.String") })

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
	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Duration impTime = driver.manage().timeouts().getImplicitWaitTimeout();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		Boolean flag = true;
		WebElement removeButton=null;
		Actions ac = new Actions(driver);
		try {
		while(flag) { 
		    try { 	
		    removeButton = driver.findElement(By.xpath("//img[@alt=\"Delete\"]"));
	        HandleClickIntercepted("//img[@alt=\"Delete\"]", driver);
		//   wait.until(ExpectedConditions.elementToBeClickable(removeButton));
		//   removeButton.click();
		   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@alt='loader']")));
			/*
			 * for(int i=0;i<=3;i++) { ac.sendKeys(Keys.ARROW_DOWN).build().perform(); }
			 * for(int i=0;i<=3;i++) { ac.sendKeys(Keys.ARROW_UP).build().perform(); }
			 */
		   }catch (NoSuchElementException g) {
			    flag = false;
		        if(driver.findElement(By.xpath("//p[contains(text(),'There are no products in your Wishlist')]")).isDisplayed()) {
		    	driver.navigate().back();
		    	System.out.println("Successfully Deleted All The Products");		
		    	}
		        else {
		        	System.out.println("Failed To Delete The Products");
		    	
		        }
		    }
		    catch (Exception f) {
				System.out.println("Failed to delete the products " +f);
				break;
		}
		}
		}
		catch (Exception e) {
				
				
			}
		    
		
		finally {
			driver.manage().timeouts().implicitlyWait(impTime);
		}
		return nlpResponseModel;
	}
}
