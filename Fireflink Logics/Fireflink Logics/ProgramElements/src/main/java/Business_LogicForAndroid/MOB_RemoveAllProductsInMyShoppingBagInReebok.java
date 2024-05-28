package Business_LogicForAndroid;

import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import java.util.Map;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.burt.jmespath.antlr.v4.runtime.tree.xpath.XPath;
import io.appium.java_client.TouchAction;
import org.springframework.stereotype.Component;
import com.tyss.optimize.nlp.util.*;
import com.tyss.optimize.common.util.CommonConstants;
import java.util.Set;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.functions.ExpectedCondition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



@Component("LIC1710_PJT1014_PE_NLP50527523-704d-415d-89a1-c5d09d4c21e4")
public class MOB_RemoveAllProductsInMyShoppingBagInReebok implements Nlp {
	// @InputParams({@InputParam(name = "waitTime", type = "java.lang.String")})

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
		// String waitTime = (String) attributes.get("locatorValue");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("waitForIdleTimeout", 0);
		
		  try {
		        AndroidDriver driver= nlpRequestModel.getAndroidDriver();
		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
				Thread.sleep(5000);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ImageView/../following-sibling::android.view.ViewGroup/android.view.ViewGroup[@index=2]")));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.ImageView/../following-sibling::android.view.ViewGroup/android.view.ViewGroup[@index=2]")));
				driver.findElement(By.xpath("//android.widget.ImageView/../following-sibling::android.view.ViewGroup/android.view.ViewGroup[@index=2]")).click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/descendant::android.widget.FrameLayout/child::android.view.ViewGroup//android.widget.ImageView")));
				Thread.sleep(5000);
				//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView/../following-sibling::android.view.ViewGroup[1]/android.view.ViewGroup[2]/com.horcrux.svg.SvgView")));
				List items = driver.findElements(By.xpath("//android.widget.TextView/../following-sibling::android.view.ViewGroup[1]/android.view.ViewGroup[2]/com.horcrux.svg.SvgView"));
		        int count=items.size();
		        if(count>0) {
		        try {
		        while(count>0) {
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView/../following-sibling::android.view.ViewGroup[1]/android.view.ViewGroup[2]/com.horcrux.svg.SvgView")));
				if(driver.findElement(By.xpath("//android.widget.TextView/../following-sibling::android.view.ViewGroup[1]/android.view.ViewGroup[2]/com.horcrux.svg.SvgView")).isDisplayed()) {
					Thread.sleep(2000);
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView/../following-sibling::android.view.ViewGroup[1]/android.view.ViewGroup[2]/com.horcrux.svg.SvgView")));
		        	driver.findElement(By.xpath("//android.widget.TextView/../following-sibling::android.view.ViewGroup[1]/android.view.ViewGroup[2]/com.horcrux.svg.SvgView")).click();
		        	Thread.sleep(2000);
		        }
		        
				else {
					break;
				}
		        }
		        
		        }catch(Exception e) {
		        	driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		        	nlpResponseModel.setStatus(CommonConstants.pass);
		            nlpResponseModel.setMessage("Products are Removed From My Bag");
		        	
		        }
		        }else {
		        	driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		        	nlpResponseModel.setStatus(CommonConstants.pass);
		            nlpResponseModel.setMessage("No Products in My Bag");
		        	
		        }  
		        
		        } catch(Exception e) {
		                  nlpResponseModel.setStatus(CommonConstants.fail);
					      nlpResponseModel.setMessage("Failed to Remove Products From My Bag");
		        }
		        return nlpResponseModel;
		    }
		} 
