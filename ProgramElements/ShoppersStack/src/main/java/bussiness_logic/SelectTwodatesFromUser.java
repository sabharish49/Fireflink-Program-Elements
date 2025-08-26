package bussiness_logic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

	
@Component("LIC14952_PJT1001_PE_NLPc2b79048-99d7-4eab-a500-55265d615b2d")
public class SelectTwodatesFromUser implements Nlp {
	    @InputParams({@InputParam(name = "inputdate1", type = "java.lang.String"),
	    @InputParam(name = "inputdate2", type = "java.lang.String")})
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
	          String inputdate1 = (String) attributes.get("inputdate1");
	          String inputdate2 = (String) attributes.get("inputdate2");
	          

	          // Your program element business logic goes here ...
	          boolean result=true; 
	         WebDriver driver=nlpRequestModel.getWebDriver();
	       try {
	    	   SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	           Date date = dateFormat.parse(inputdate1);
	           int year = date.getYear() + 1900;
	           String y = Integer.toString(year);
	          
	           SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
	           Date date2 = dateFormat2.parse(inputdate2);
	           int year2 = date.getYear() + 1900;
	           String y2 = Integer.toString(year);
	           
	           
	           
	           
	           
	           int month = date.getMonth() + 1; 
	           String[] months = {
	                   "January", "February", "March", "April", "May", "June",
	                   "July", "August", "September", "October", "November", "December"};
	               
	           String m="";
	               // Ensure that the input is within a valid range (1 to 12)
	               try {
						if (month >= 1 && month <= 12) {
						m=	months[month - 1]; // Array is zero-based
						} else {
						    // Handle invalid input
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	               
	               int month2 = date2.getMonth() + 1; 
		           String[] months2 = {
		                   "January", "February", "March", "April", "May", "June",
		                   "July", "August", "September", "October", "November", "December"};
		               
		           String m2="";
		               // Ensure that the input is within a valid range (1 to 12)
		               try {
							if (month2 >= 1 && month2 <= 12) {
							m=	months2[month - 1]; // Array is zero-based
							} else {
							    // Handle invalid input
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}    
	               
	             
	           
	           int day = date.getDate();
	           String d = Integer.toString(day);
	           
	           
	           int day2 = date.getDate();
	           String d2 = Integer.toString(day2);
	           
	           
	           
	           
	           
	        WebElement years= driver.findElement(By.name("years"));
	       	Select s= new Select(years);
	       	s.selectByVisibleText(y);
	       	WebElement months1= driver.findElement(By.name("months"));
	       	s= new Select(months1);
	       	s.selectByVisibleText(m);
	        driver.findElement(By.xpath("(//*[text()="+d+"])[1]")).click();
	        
	        
	        
	        WebElement years2= driver.findElement(By.xpath("(//*[@name='years'])[2]"));
	       	Select s1= new Select(years2);
	       	s1.selectByVisibleText(y2);
	       	WebElement month4= driver.findElement(By.xpath("(//*[@name='months'])[2]"));
	        Select s2= new Select(month4);
	       	s2.selectByVisibleText(m2);
	        driver.findElement(By.xpath("(//*[text()="+d2+"])[2]")).click();
	        
	      

	          result=true;
			   nlpResponseModel.setStatus(CommonConstants.pass);
			   nlpResponseModel.setMessage("date selected");
	              }
	     catch(Exception e)
	      {
	    	   result=false;
			   nlpResponseModel.setStatus(CommonConstants.fail);
			   nlpResponseModel.setMessage("failed to select date"+e);
	        }
	       nlpResponseModel.getAttributes().put("result", result);
	       return nlpResponseModel;
	}
	  } 





