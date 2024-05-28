package Business_LogicForWeb;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

@Component("LIC1710_PJT1014_PE_NLP7fea0a4b-d4f7-48c7-bc50-04572a277336")
public class ResetChromeDebuggerBrowser implements Nlp {
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
          try
          {
            WebDriver driver=(WebDriver)nlpRequestModel.getWebDriver();
      		JavascriptExecutor jse= (JavascriptExecutor) driver;
      		String url1							= "chrome://settings/resetProfileSettings?origin=userclick";
    		String url2							= "chrome://settings/content/popups";
    		String url3							= "chrome://settings/content/siteDetails?site=https%3A%2F%2Fprofile.abfrl.in";
    		String url4							= "chrome://settings/content/siteDetails?site=https%3A%2F%2Fcheckout.abfrl.in";
    		String resetSettingsButton			= "document.querySelector(\"body > settings-ui\").shadowRoot.querySelector(\"#main\").shadowRoot.querySelector(\"settings-basic-page\").shadowRoot.querySelector(\"#advancedPage > settings-section:nth-child(12) > settings-reset-page\").shadowRoot.querySelector(\"#reset-pages > div > settings-reset-profile-dialog\").shadowRoot.querySelector(\"#reset\")";
    		String allowPopUpRadioButton		= "document.querySelector(\"body > settings-ui\").shadowRoot.querySelector(\"#main\").shadowRoot.querySelector(\"settings-basic-page\").shadowRoot.querySelector(\"#basicPage > settings-section.expanded > settings-privacy-page\").shadowRoot.querySelector(\"#pages > settings-subpage > settings-category-default-radio-group\").shadowRoot.querySelector(\"#enabledRadioOption\").shadowRoot.querySelector(\"#button\")";
    		String locationDropdown				= "document.querySelector(\"body > settings-ui\").shadowRoot.querySelector(\"#main\").shadowRoot.querySelector(\"settings-basic-page\").shadowRoot.querySelector(\"#basicPage > settings-section.expanded > settings-privacy-page\").shadowRoot.querySelector(\"#pages > settings-subpage > site-details\").shadowRoot.querySelector(\"div.list-frame > site-details-permission:nth-child(1)\").shadowRoot.querySelector(\"#permission\")";
    		String locationAllowDropdownOption	= "document.querySelector(\"body > settings-ui\").shadowRoot.querySelector(\"#main\").shadowRoot.querySelector(\"settings-basic-page\").shadowRoot.querySelector(\"#basicPage > settings-section.expanded > settings-privacy-page\").shadowRoot.querySelector(\"#pages > settings-subpage > site-details\").shadowRoot.querySelector(\"div.list-frame > site-details-permission:nth-child(1)\").shadowRoot.querySelector(\"#allow\")";
    		
    		driver.get(url1);
    		try {Thread.sleep(2000);} catch (InterruptedException e) {}
    		WebElement element1 = (WebElement) jse.executeScript("return "+resetSettingsButton);
    		try {element1.click();}catch (WebDriverException e) {}
    		try {Thread.sleep(3000);} catch (InterruptedException e) {}
    		driver.get(url2);
    		try {Thread.sleep(1000);} catch (InterruptedException e) {}
    		WebElement element2 = (WebElement) jse.executeScript("return "+allowPopUpRadioButton);
    		try {element2.click();}catch (WebDriverException e) {}
    		try {Thread.sleep(2000);} catch (InterruptedException e) {}
    		driver.get(url3);
    		try {Thread.sleep(1000);} catch (InterruptedException e) {}
    		WebElement element3 = (WebElement) jse.executeScript("return "+locationDropdown);
    		try {element3.click();}catch (WebDriverException e) {}
    		try {Thread.sleep(1000);} catch (InterruptedException e) {}
    		WebElement element4 = (WebElement) jse.executeScript("return "+locationAllowDropdownOption);
    		try {Thread.sleep(1000);} catch (InterruptedException e) {}
    		try {element4.click();}catch (WebDriverException e) {}
    		try {Thread.sleep(1000);} catch (InterruptedException e) {}
    		try {Thread.sleep(2000);} catch (InterruptedException e) {}
    		driver.get(url4);
    		try {Thread.sleep(1000);} catch (InterruptedException e) {}
    		WebElement element5 = (WebElement) jse.executeScript("return "+locationDropdown);
    		try {element5.click();}catch (WebDriverException e) {}
    		try {Thread.sleep(1000);} catch (InterruptedException e) {}
    		WebElement element6 = (WebElement) jse.executeScript("return "+locationAllowDropdownOption);
    		try {Thread.sleep(1000);} catch (InterruptedException e) {}
    		try {element6.click();}catch (WebDriverException e) {}
    		try {Thread.sleep(1000);} catch (InterruptedException e) {}
  	        nlpResponseModel.setStatus(CommonConstants.pass);
  	        nlpResponseModel.setMessage("Element is clicked");
          } 
          catch (Exception e) 
          {
        	nlpResponseModel.setStatus(CommonConstants.pass);
  			nlpResponseModel.setMessage("Something Went Wrong");
          }
          return nlpResponseModel;
      }
  } 