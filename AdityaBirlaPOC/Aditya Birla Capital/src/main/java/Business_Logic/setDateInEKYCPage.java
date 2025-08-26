
package Business_Logic;

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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

@Component("LIC19774_PJT1002_PE_NLP7e1fbee0-8aef-4459-8bfe-e31725940f75")
public class setDateInEKYCPage implements Nlp {
	@InputParams({ @InputParam(name = "inputDate", type = "java.lang.String") })
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
		String inputDate = (String) attributes.get("inputDate");
		boolean res = false;

		LinkedHashMap<String, String> monthMap = new LinkedHashMap<>();

		monthMap.put("01", "January");
		monthMap.put("02", "February");
		monthMap.put("03", "March");
		monthMap.put("04", "April");
		monthMap.put("05", "May");
		monthMap.put("06", "June");
		monthMap.put("07", "July");
		monthMap.put("08", "August");
		monthMap.put("09", "September");
		monthMap.put("10", "October");
		monthMap.put("11", "November");
		monthMap.put("12", "December");

		try {
			WebDriver driver = nlpRequestModel.getWebDriver();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			Actions action = new Actions(driver);

			String[] values = inputDate.split("/");
			String Date = values[0];
			while (true) {
				if (driver.findElement(By.xpath("//div[@id='hv-form-date-list-dob']//div[@children='" + Date + "']"))
						.isDisplayed()) {
					driver.findElement(By.xpath("//div[@id='hv-form-date-list-dob']//div[@children='" + Date + "']"))
							.click();
					break;
				} else {
					action.moveToElement(driver
							.findElement(By.xpath("//div[@id='hv-form-date-list-dob']//div[@children='" + Date + "']")))
							.perform();
				}
			}

			String monthNumber = values[1];
			String month = monthMap.get(monthNumber);
			while (true) {
				if (driver.findElement(By.xpath("//div[@id='hv-form-month-list-dob']//div[@children='" + month + "']"))
						.isDisplayed()) {
					driver.findElement(By.xpath("//div[@id='hv-form-month-list-dob']//div[@children='" + month + "']"))
							.click();
					break;
				} else {
					action.moveToElement(driver.findElement(
							By.xpath("//div[@id='hv-form-month-list-dob']//div[@children='" + month + "']"))).perform();
				}
			}

			String year = values[2];
			while (true) {
				if (driver.findElement(By.xpath("//div[@id='hv-form-year-list-dob']//div[@children='" + year + "']"))
						.isDisplayed()) {
					driver.findElement(By.xpath("//div[@id='hv-form-year-list-dob']//div[@children='" + year + "']"))
							.click();
					break;
				} else {
					action.moveToElement(driver
							.findElement(By.xpath("//div[@id='hv-form-year-list-dob']//div[@children='" + year + "']")))
							.perform();
				}
			}

			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@children='Done']"))));
			driver.findElement(By.xpath("//div[@children='Done']")).click();
			
			res=true;
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully picked and Set Date in Calendar");

		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to Set Date in Calendar "+ e);

		}

		
		nlpResponseModel.getAttributes().put("result", res);
		return nlpResponseModel;
	}
}