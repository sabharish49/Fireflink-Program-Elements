
package Business_Logics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import org.springframework.stereotype.Component;

@Component("LIC4858_PJT1019_PE_NLPacee8e65-237f-4761-90b1-f6153e4c1f6e")
public class Calander implements Nlp {
    @InputParams({@InputParam(name = "Date", type = "java.lang.String"),@InputParam(name = "Header", type = "java.lang.String")})
    @ReturnType(name = "result", type = "java.lang.Boolean")

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
          String Date = (String) attributes.get("Date");
          String Header = (String) attributes.get("Header");
          boolean res=false;

          try {       	  
  		    String date=Date; 		    
  		    String[] dateArr = date.split(":");
  		    String month=dateArr[0].substring(0,3);
  	        int day = Integer.parseInt(dateArr[1]);
  	        int year =Integer.parseInt(dateArr[2]); 		
          System.out.println("Current Year: " + year);
          System.out.println("Current Month: " + month);
          System.out.println("Current Day: " + day);	
          WebDriver driver=nlpRequestModel.getWebDriver();	
  	      String header=Header; 		
  		driver.findElement(By.xpath("//label[text()='"+header+"']/following-sibling::div[@class='rdt']//input")).click();  	
  		for(int i=0;i<2;i++) {
  			driver.findElement(By.xpath("//label[text()='"+header+"']/following-sibling::div//th[@class='rdtSwitch']")).click();
  		}
  		
  		boolean test=false;
  		while(test==false) {
  		String startYear = driver.findElement(By.xpath("(//label[text()='"+header+"']/following-sibling::div//td[@class='rdtYear'])[1]")).getText();
  		Integer startYear1=Integer.parseInt(startYear);
  		System.out.println(startYear1);
  		
  		String endYear = driver.findElement(By.xpath("(//label[text()='"+header+"']/following-sibling::div//td[@class='rdtYear'])[last()]")).getText();
  		Integer endYear1=Integer.parseInt(endYear);
  		System.out.println(endYear1);
  		
  		if(year>=startYear1 && year<=endYear1){
  			driver.findElement(By.xpath("//label[text()='"+header+"']/following-sibling::div//td[contains(@class,'rdtYear')  and text()='"+year+"']")).click();
  			break;
  		}
  		
  		else if(startYear1>year) {
  			driver.findElement(By.xpath("//label[text()='"+header+"']/following-sibling::div//th[@class='rdtPrev']")).click();
  		}
  		
  		else {
  			driver.findElement(By.xpath("//label[text()='"+header+"']/following-sibling::div//th[@class='rdtNext']")).click();
  		}
  		}
  		
  		System.out.println("clicked on year");
  		driver.findElement(By.xpath("//label[text()='"+header+"']/following-sibling::div//td[contains(@class,'rdtMonth') and (text()='"+month+"')]")).click();
  		System.out.println("clicked on month"); 
  		driver.findElement(By.xpath("//label[text()='"+header+"']/following-sibling::div//td[@class='rdtDay' and text()='"+day+"']")).click();
  		System.out.println("clicked on day"); 
  		Thread.sleep(1000);  		
  		driver.findElement(By.xpath("//label[text()='"+header+"']")).click();
  		Thread.sleep(1000);  		
  		res=true;
  		nlpResponseModel.setStatus(CommonConstants.pass);
  		nlpResponseModel.setMessage("Desired date is set");        	  
          }
          
          catch (Exception e) {
        	  nlpResponseModel.setStatus(CommonConstants.fail);
        	  nlpResponseModel.setMessage("failed to set Desired date "+e);
		}
             
          nlpResponseModel.getAttributes().put("result", res);
          return nlpResponseModel;
      }
  } 