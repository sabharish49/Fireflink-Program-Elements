package WebBusinessLogics;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;



public class GetRecentDateandTime implements Nlp {
	@ReturnType(name = "GetindexValue", type = "java.lang.Integer")

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

		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(10));

		int mostRecentIndex = -1;

		try {
			//GET CURRENT SYSTEM TIME
			//      	    LocalTime currentTime = LocalTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			//              String LocalMachineTime = currentTime.format(timeFormatter);
			//              System.out.println("Current system time: " + LocalMachineTime);

			Thread.sleep(20000);
			//GET LIST OF ELEMENTS and extract only Time
			List<WebElement> listOfElements=driver.findElements(By.xpath("//mat-header-cell[contains(text(),'Trip Created Date')] /../following-sibling::mat-row/mat-cell[contains(text(),':')]"));


			// Initialize variables to track the most recent time value
			LocalDateTime mostRecentDate = null;


			// Loop through the list of WebElements
			for (int i = 0; i < listOfElements.size(); i++) {
				// Get the time text from the WebElement
				String timeText = listOfElements.get(i).getText();
				//String time = timeText.split(" ")[1];

				try {
					// Parse the time text to LocalTime
					LocalDateTime elementTime = LocalDateTime.parse(timeText, formatter);

					// Compare with the current time and check if it's more recent
					if (mostRecentDate == null || elementTime.isAfter(mostRecentDate)) {
						mostRecentDate = elementTime;
						mostRecentIndex = i;
					}
				}
				catch(Exception e) {
					System.out.println("Error parsing time: " + timeText);
					e.printStackTrace(); 
				}

				// Print the index of the most recent time value
				if (mostRecentIndex != -1) {

					nlpResponseModel.setStatus(CommonConstants.pass);
					nlpResponseModel.setMessage("Date selected sucessfuly"+listOfElements.get(mostRecentIndex).getText());

				} else {

					nlpResponseModel.setStatus(CommonConstants.fail);
					nlpResponseModel.setMessage("No matching time value found");
				}
			}

		}  

		catch (Exception e) {

			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Unable to select Date and time"+e.getMessage());
		}

		nlpResponseModel.getAttributes().put("GetindexValue", mostRecentIndex);
		return nlpResponseModel;

	}
}
