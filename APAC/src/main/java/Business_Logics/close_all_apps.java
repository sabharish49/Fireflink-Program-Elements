package Business_Logics;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;


import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;

import io.appium.java_client.android.AndroidDriver;


public class close_all_apps implements Nlp {
	  //  @InputParams({@InputParam(name = "element", type = "org.openqa.selenium.WebElement")})
	    //@ReturnType(name = "isDisplay", type = "java.lang.Boolean")
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
	          AndroidDriver driver = nlpRequestModel.getAndroidDriver();
	         try {       	
	             Runtime.getRuntime().exec("adb shell input keyevent KEYCODE_APP_SWITCH");       
	             if (isElementPresent(driver, By.xpath("//android.widget.Button[@text='Close all']"))) {
	                 driver.findElement(By.xpath("//android.widget.Button[@text='Close all']")).click();
	                 System.out.println("Closed all recent apps");
	             } else {
	                 System.out.println("All apps are already closed");
	             }  
	             nlpResponseModel.setStatus(CommonConstants.pass);
	        	 nlpResponseModel.setMessage("Successfully closed all apps");
	         }
	         catch(Exception e) {
	        	 nlpResponseModel.setStatus(CommonConstants.fail);
	        	 nlpResponseModel.setMessage("Failed to close all apps"+e);  	 
	         }
	          return nlpResponseModel;
	      }
	      
	      public static boolean isElementPresent(AndroidDriver driver, By locator) {
	          try {
	              driver.findElement(locator);
	              return true;
	          } catch (org.openqa.selenium.NoSuchElementException e) {
	              return false;
	          }
	      }
}