
package Logics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

@Component("LIC8151_PJT1002_PE_NLP410f83eb-888b-4d42-9b1e-4ce8f21dc664")
public class TimePicker implements Nlp {
	
    @InputParams({@InputParam(name = "StartTime / EndTime", type = "java.lang.String"), @InputParam(name = "InputTime", type = "java.lang.String")})
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
          String DateType = (String) attributes.get("StartTime / EndTime");
          String InputDate = (String) attributes.get("InputTime");
         WebDriver driver = nlpRequestModel.getWebDriver();
          try {
      		String[] d=InputDate.split(":");
      		driver.findElement(By.xpath("//label[text()='"+DateType+"']/..//input")).click();
      	    String hours = driver.findElement(By.xpath("//label[text()='"+DateType+"']/following-sibling::div//div[@class='rdtCounter']//div")).getText();
      	    String min = driver.findElement(By.xpath("//label[text()='"+DateType+"']/following-sibling::div//div[@class='rdtCounter'][2]//div")).getText();
      	    String format=driver.findElement(By.xpath("//label[text()='"+DateType+"']/following-sibling::div//div[@class='rdtCounter'][3]//div")).getText();
      	    int actualHour = Integer.parseInt(hours);
      	    int actualMin= Integer.parseInt(min);
      	    //Hours
      	    if (d.length >= 1) {   	
      	    	String hour = d[0];
      	    	int ExpectedHour = Integer.parseInt(hour);
      	    	if(actualHour<=ExpectedHour) {
      	    		int i=1;
      	         while(i<=12) {
      	        	hours = driver.findElement(By.xpath("//label[text()='"+DateType+"']/following-sibling::div//div[@class='rdtCounter']//div")).getText();
      	     	    int currenthour = Integer.parseInt(hours);
      	        	i++;
      	     	    if(currenthour==ExpectedHour) {
      	     	    	break;
      	     	    }
      	     	    else {
      	     	    	driver.findElement(By.xpath("//label[text()='"+DateType+"']/following-sibling::div//div[@class='rdtCounter']//span[2]")).click();
      	     	    }
      	         }
      	    	}
      	    	
      		    else {
      		    	int i=1;
      		         while( i<=12) {
      		        	hours = driver.findElement(By.xpath("//label[text()='"+DateType+"']/following-sibling::div//div[@class='rdtCounter']//div")).getText();
      		     	    int currenthour = Integer.parseInt(hours);
      		        	i++;
      		     	    if(currenthour==ExpectedHour) {
      		     	    	break;
      		     	    }
      		     	    else {
      		     	    	driver.findElement(By.xpath("//label[text()='"+DateType+"']/following-sibling::div//div[@class='rdtCounter']//span[2]")).click();
      		     	    }
      		         }
      		    	}
      	    }
      	    
      	//Minutes
      	    if (d.length >= 1) {   	
      	    	String Min = d[1];
      	    	int ExpectedMin = Integer.parseInt(Min);
      	    	System.out.println(ExpectedMin);
      	    	System.out.println(actualMin);
      	    	if(actualMin<=ExpectedMin) {
      	    		int i=1;
      	         while(i<=12) {
      	        	  min = driver.findElement(By.xpath("(//label[text()='"+DateType+"']/following-sibling::div//div[@class='rdtCounter'])[2]//div")).getText();	    
      	     	    int CurrentMin = Integer.parseInt(min);
      	        	i++;      	
      	     	    if(CurrentMin==ExpectedMin) {
      	     	    	break;
      	     	    }
      	     	    else {
      	     	    	driver.findElement(By.xpath("//label[text()='"+DateType+"']/following-sibling::div//div[@class='rdtCounter'][2]//span[2]")).click();
      	     	    }
      	         }
      	    	}
      		    else {
      		    	int i=1;
      		         while( i<=12) {
      		        	 min = driver.findElement(By.xpath("//label[text()='"+DateType+"']/following-sibling::div//div[@class='rdtCounter'][2]//div")).getText();	    
      		        	int CurrentMin = Integer.parseInt(min);
      		        	i++;
      		     	    if(CurrentMin==ExpectedMin) {
      		     	    	break;
      		     	    }
      		     	    else {
      		     	    	driver.findElement(By.xpath("//label[text()='"+DateType+"']/following-sibling::div//div[@class='rdtCounter'][2]//span")).click();
      		     	    }
      		         }
      		    	}
      	    }
      	    
      	    //DateFormat
      	    String expectedFormat  = d[2];
      	    if(format.equals(expectedFormat)) {
      	    	driver.findElement(By.xpath("//label[text()='"+DateType+"']")).click();
      	    }
      	    else {
       	    	driver.findElement(By.xpath("//label[text()='"+DateType+"']/following-sibling::div//div[@class='rdtCounter'][3]//span")).click();
       	    	driver.findElement(By.xpath("//label[text()='"+DateType+"']")).click();
      	    }
      		nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Time Picked Successfully");
			
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to pick Time from timePicker"+e);
		}
          return nlpResponseModel;
      }
  } 