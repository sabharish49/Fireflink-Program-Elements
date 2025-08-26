
package bussiness_logic;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import java.util.Map;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;




@Component("LIC20128_PJT1001_PE_NLPa4f1d1f0-46bd-4fc6-8815-ca9f06b17dd8")
public class HandlingDatePicker implements Nlp {
    @InputParams({@InputParam(name = "Calender Name", type = "java.lang.String"), @InputParam(name = "minusDate", type = "java.lang.Integer")})
 

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
          String calenderName = (String) attributes.get("Calender Name");
          Integer minusDate = (Integer) attributes.get("minusDate");
          WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(10));

          // Your program element business logic goes here ...
          
          try {
        	 
        	//int currentDate =0;
         	 String str1 = minusDate.toString();
         	 String substringToReplace = "-";
         	 
         	String replacedstr1 = str1.replace(substringToReplace, "");
         	minusDate = Integer.parseInt(replacedstr1);
        	driver.findElement(By.xpath("//label[contains(text(),'"+calenderName+"')]/..//input")).click();
      		LocalDate day=LocalDate.now();
      		int calenderDate=day.minusDays(minusDate).getDayOfMonth();
      		String month=day.minusDays(minusDate).getMonth().toString();
      		month=month.substring(0,1)+month.substring(1).toLowerCase();
      		while(!(driver.findElement(By.xpath("//label[contains(text(),'"+calenderName+"')]/following-sibling::div//input/following-sibling::div//th[@class='rdtSwitch']")).getText().contains(month))){
      			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'"+calenderName+"')]/following-sibling::div//input/following-sibling::div//th[@class='rdtPrev']")));
      			 driver.findElement(By.xpath("//label[contains(text(),'"+calenderName+"')]/following-sibling::div//input/following-sibling::div//th[@class='rdtPrev']")).click();
      			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'"+calenderName+"')]/following-sibling::div//input/following-sibling::div//th[@class='rdtSwitch']")));
      			 }    	
             driver.findElement(By.xpath("//label[contains(text(),'"+calenderName+"')]/following-sibling::div//input/following-sibling::div//td[@class='rdtDay' and text()='"+calenderDate+"']")).click();
      	
      		 nlpResponseModel.setStatus(CommonConstants.pass);
			 nlpResponseModel.setMessage("Date selected sucessfuly");
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Unable to select Date"+e.getMessage());
		}
     
         // nlpResponseModel.getAttributes().put("dateofMonth",dateOfMonth);
          return nlpResponseModel;
      }
  } 