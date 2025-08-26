package Business_Logic;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import org.springframework.stereotype.Component;


@Component("LIC23707_PJT1008_PE_NLP5bad41da-4c09-4104-b322-e72178966df1")
public class Policy_holder implements Nlp {
	@InputParams({@InputParam(name = "Policy Insure Details", type = "java.lang.String")})
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
		// WebElement Element = (WebElement) attributes.get("Value");
		String insureType = (String) attributes.get("Policy Insure Details"); 
		List<String> relationshipList = new ArrayList<>();
		List<Integer> ageList = new ArrayList<>();
		List<Integer> countList = new ArrayList<>();
		WebDriver driver = nlpRequestModel.getWebDriver();
		Pattern pattern = Pattern.compile("(\\w+):(\\d+)(?:\\|(\\d+))?,?\\s*");
		Matcher matcher = pattern.matcher(insureType);

		while (matcher.find()) {
			String relationship = matcher.group(1);
			int age = Integer.parseInt(matcher.group(2));
			int count = matcher.group(3) != null ? Integer.parseInt(matcher.group(3)) : 1;

			relationshipList.add(relationship);
			ageList.add(age);
			countList.add(count);
		}         
		try {
			driver.findElement(By.xpath("//h2[text()='Show others ']")).click();
			for (int i = 0; i < relationshipList.size(); i++) {
				WebElement element=null;
				String insureName = relationshipList.get(i);
				try {
	                 element = driver.findElement(By.xpath("(//span[@class='member-name' and text()='"+insureName+"']/..)[1]"));
				}catch (Exception e) {
					element=driver.findElement(By.xpath("//h2[text()='Show others ']/../..//span[@class='member-name' and contains(text(),'"+insureName+"')]"));
				}
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView(true);", element);
				if(insureName.equals("SELF") || insureName.equals("SPOUSE")) {
					element.click();
				}
				else if (insureName.equals("SON") || insureName.equals("DAUGHTER")) {
					driver.findElement(By.xpath("//span[@class=\"member-name\" and contains(text(),'"+insureName+"')]/..//div//img"))
					.click();
				}
				else {
					driver.findElement(By.xpath("//h2[text()='Show others ']/../..//span[@class='member-name' and contains(text(),'"+insureName+"')]")).click();
				}          
				Integer age = ageList.get(i);
				try {													
					driver.findElement(By.xpath("//span[@class=\"member-name\" and contains(text(),'"+insureName+"')]/following-sibling::input"))
					.sendKeys(String.valueOf(age));
				} catch (Exception e) {
					driver.findElement(By.xpath("//h2[text()='Show others ']/../..//span[@class='member-name' and contains(text(),'"+insureName+"')]/following-sibling::input"))
					.sendKeys(String.valueOf(age));
				}
			}      
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Sucessfully selected the Insurer");
		}
		catch(Exception e)
		{
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to select insurer" +e);
		}
		return nlpResponseModel;
	}

}


