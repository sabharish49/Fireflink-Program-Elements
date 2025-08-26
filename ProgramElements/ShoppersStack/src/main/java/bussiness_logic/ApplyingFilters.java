package bussiness_logic;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import com.tyss.optimize.nlp.util.annotation.ReturnType;



import org.springframework.stereotype.Component;

@Component("LIC14952_PJT1001_PE_NLPf2606d11-2eb6-4d54-8927-b225a5bbc981")
public class ApplyingFilters implements Nlp {

	@InputParams({@InputParam(name = "filters", type = "java.lang.String")})
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
		String filters = (String) attributes.get("filters");
         

		// Your program element business logic goes here ...
		boolean result=true;
		
		
		try {
			WebDriver driver = nlpRequestModel.getWebDriver();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			String firstword;
			String secondword;
			String input = filters;

			String[] parts = input.split(";");

			for (int i = 0; i < parts.length; i++) {
				String sentence = parts[i];
				String[] words = sentence.split("[:]");

				int wordCount = words.length;

				if (wordCount == 1) {
					firstword=words[0];

					String firstXpath = "//p[text()='Filters']/following::label[text()='"+firstword+"']/../div/input";
					WebElement element= driver.findElement(By.xpath(firstXpath));

					if (element.isDisplayed()) {
						element.click();
					} 
					else {

						executor.executeScript("arguments[0].scrollIntoView(true);", element);

						element.click();
					}
				}

				else if (wordCount > 1) {

					for (int j = 1; j < words.length; j++) {
						firstword=words[0];

						secondword=words[j];

						try {

							String secondXpath="//div[contains(@class,'filter-container-wrapper')]/descendant::p[text()='"+firstword+"']/../descendant::span[text()='"+secondword+"']";
							WebElement type= driver.findElement(By.xpath(secondXpath));

							if (type.isDisplayed()) {

								executor.executeScript("arguments[0].click();", type);
							} 
						}
						catch (Exception e) {

							driver.findElement(By.xpath("//div[text()='"+firstword+"' ]/../..")).click();
							String thirdXpath="//div[contains(@class,'filter-container-wrapper')]/descendant::div[text()='"+firstword+"']/../../descendant::label[text()='"+secondword+"']";
							WebElement checkBox= driver.findElement(By.xpath(thirdXpath));
							executor.executeScript("arguments[0].click();", checkBox);

						}
					}
				}
		}
			result=true;
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("successfully applied");


		} 
		catch (Exception e) {
			result=false;
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("failed to apply filters"+e);

		}
		nlpResponseModel.getAttributes().put("result", result);
		return nlpResponseModel;
	}
} 

