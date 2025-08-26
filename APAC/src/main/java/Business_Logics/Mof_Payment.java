package Business_Logics;

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

import io.appium.java_client.android.AndroidDriver;




public class Mof_Payment implements Nlp {
	   @InputParams({@InputParam(name = "Minutes", type = "java.lang.String")})
	    //@ReturnType(name = "isDisplay", type = "java.lang.Boolean"
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
	          String Minutes=(String) attributes.get("Minutes");
	          AndroidDriver driver = nlpRequestModel.getAndroidDriver();
	          int loopDurationMinutes = Integer.parseInt(Minutes); 
	          long duration = loopDurationMinutes *60*1000; 
	          long startTime = System.currentTimeMillis();   
	          WebDriverWait wait=null;
	          try
	          {       	 
	             long elapsedTime;
	             while ((elapsedTime = System.currentTimeMillis() - startTime) < duration) {  	 
	                 try {
	                    WebElement mof = driver.findElement(By.xpath("//android.widget.Button[@text='MOF']"));       
	     				if (mof.isDisplayed()) {
	     					mof.click();
	     				    wait = new WebDriverWait(driver, Duration.ofSeconds(3));
	     					Thread.sleep(2000);
	     					WebElement prospectAlert = driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'ProspectId and Prospect No is not generated')]"));
		                     if (prospectAlert.isDisplayed()) {
		                         driver.findElement(By.xpath("//android.widget.Button[@text='OK']")).click();
		                         System.out.println("Alert handled.");
		                     }		                     
	     				}
	     			}
	                catch (Exception e) {  
	       					      WebElement add = wait.until(
	       					      ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ImageButton[@resource-id=\"com.apacfin.newalpha:id/btnAddMof\"]")));
	       					      add.click(); 
	       						  break;
		         
	     			}              
	                 long remainingTime = duration - elapsedTime;
	                 if (remainingTime < 5000)
	                    break;
	                 Thread.sleep(Math.min(5000, remainingTime)); 
	                 System.out.println(". Exiting loop.");
		        	 nlpResponseModel.setStatus(CommonConstants.pass);
		        	 nlpResponseModel.setMessage("Mof Payment Initiated Successfully");             
	             }
	             }          
	            catch(Exception e) {
		        	 nlpResponseModel.setStatus(CommonConstants.fail);
		        	 nlpResponseModel.setMessage("Failed to initiate Mof Payment"+e);		        	 
		         }	         
	         //.getAttributes().put("isDisplay", isDisplay);
	          return nlpResponseModel;
	      }
}