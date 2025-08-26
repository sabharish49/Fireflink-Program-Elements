package Business_Logics;
import java.time.Duration;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;

import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;


public class GetQuantity implements Nlp {

	@ReturnType(name = "quantity", type = "java.lang.Integer")

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

		

		AndroidDriver driver = nlpRequestModel.getAndroidDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		int multiplier=0;
		try {

			String individualAmtText = driver.findElement(By.xpath("//android.widget.TextView[@text='Your Order']/../following-sibling::android.widget.ScrollView/descendant::android.widget.TextView[@resource-id='com.c2info.wellnessforever:id/productShellPrice']")).getText();
			String individualAmt1 = individualAmtText.replaceAll("[^\\d.]", "");
			double individualAmt = Double.parseDouble(individualAmt1);
			
			
			driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Use coupons')]")).click();
	    	driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'VIEW DETAILS')]")).isDisplayed();
			driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'VIEW DETAILS')]")).click();
	     
	        
	     String descrp = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.c2info.wellnessforever:id/description']")).getText();
	     String regex = "\\b\\d+\\b";
	     Pattern pattern = Pattern.compile(regex);
	     Matcher matcher = pattern.matcher(descrp);
	     List<Integer> numbers = new ArrayList<>();
	     while (matcher.find()) {
	         int number = Integer.parseInt(matcher.group());
	         numbers.add(number);
	     }
	     
	     int value = numbers.get(1);
	  
	     multiplier = (int) Math.ceil(value / individualAmt);
	    
	     
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Sub Total after adding "+multiplier+" quantities is "+(individualAmt * multiplier));


		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to get Quantity");

		} 
		nlpResponseModel.getAttributes().put("quantity", multiplier);
		return nlpResponseModel;
	}

	

	

}

