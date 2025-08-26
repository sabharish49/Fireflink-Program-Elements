
package default_package;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;





public class HandlingDatePicker implements Nlp {
    @InputParams({@InputParam(name = "Days", type = "java.lang.Integer")})
    @ReturnType(name = "datePicked", type = "java.lang.Boolean")

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
          WebDriver driver=nlpRequestModel.getWebDriver();
         
          Integer Days = (Integer) attributes.get("Days");
          WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(10));

          
          
          try {
        	 
        	  Integer daysToAdd=Days;
         	  LocalDate currentDate = LocalDate.now();
              LocalDate futureDate = currentDate.plusDays(daysToAdd);
              int futureDay = futureDate.getDayOfMonth();
              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM");
              String futureMonth = futureDate.format(formatter).toUpperCase();
              driver.findElement(By.xpath("//button[contains(@aria-label,'Open calendar')][last()]")).click();
        	
              while(!(driver.findElement(By.xpath("(//button[contains(@aria-label,'Open calendar')][last()]/ancestor::app-root/following-sibling::div)[1]/descendant::div[@class='mat-calendar-header']//button[@aria-label='Choose month and year']//span[@class='mdc-button__label']//span")).getText().contains(futureMonth))){
       			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[contains(@aria-label,'Open calendar')][last()]/ancestor::app-root/following-sibling::div)[1]/descendant::div[@class='mat-calendar-header']//button[@aria-label='Choose month and year']/following-sibling::button[@aria-label='Next month']")));
       			 driver.findElement(By.xpath("(//button[contains(@aria-label,'Open calendar')][last()]/ancestor::app-root/following-sibling::div)[1]/descendant::div[@class='mat-calendar-header']//button[@aria-label='Choose month and year']/following-sibling::button[@aria-label='Next month']")).click();
       			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[contains(@aria-label,'Open calendar')][last()]/ancestor::app-root/following-sibling::div)[1]/descendant::div[@class='mat-calendar-header']//button[@aria-label='Choose month and year']//span[@class='mdc-button__label']//span")));
       		
       		}  
              driver.findElement(By.xpath("(//button[contains(@aria-label,'Open calendar')][last()]/ancestor::app-root/following-sibling::div)[1]/descendant::div[@class='mat-calendar-header']/../following-sibling::div//descendant::span[contains(text(),'"+futureDay+"')]")).click();
      	
      		 nlpResponseModel.setStatus(CommonConstants.pass);
			 nlpResponseModel.setMessage("Date selected sucessfuly");
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Unable to select Date"+e.getMessage());
		}
     
         
          return nlpResponseModel;
      }
  } 