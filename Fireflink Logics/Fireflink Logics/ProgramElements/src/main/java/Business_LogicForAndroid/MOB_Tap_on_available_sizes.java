package Business_LogicForAndroid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("LIC1710_PJT1014_PE_NLP6ccce550-4938-42e7-80d9-58e64aba9e1a")
public class MOB_Tap_on_available_sizes implements Nlp {
	@InputParams({ @InputParam(name = "xpath", type = "java.lang.String"),
			@InputParam(name = "unavailableSize", type = "java.lang.String") })
	@ReturnType(name = "newList", type = "java.util.ArrayList")
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
		String xpath = (String) attributes.get("xpath");
		String unavailableSize = (String) attributes.get("unavailableSize");
		ArrayList<Object> arr = new ArrayList<Object>();
		ArrayList<Object> list1 = new ArrayList<Object>();
		ArrayList<Object> newList = new ArrayList<Object>();
		try {
			AndroidDriver driver = (AndroidDriver) nlpRequestModel.getDriver().getSpecificIDriver();
			List<WebElement> name = driver.findElements(By.xpath(xpath));

			for (WebElement text : name) {
				String size = text.getText();
				arr.add(size);
			}
			List<WebElement> unavailsize = driver.findElements(By.xpath(unavailableSize));

			for (WebElement text1 : unavailsize) {
				String size1 = text1.getText();
				list1.add(size1);
			}
			for (Object size : arr) {
				if (list1.contains(size)) {
					newList.remove(size);
				} else {
					newList.add(size);
				}
			}
			for (int i = 0; i < newList.size(); i++) {
//				log.info("Entered into for loop to click");
				Thread.sleep(3000);
				driver.findElement(By.xpath("//android.widget.TextView[@text='" + newList.get(i) + "']")).click();

				break;
			}
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully get the text from list of webelement" + newList);

		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to get the text" + e);
		}
		nlpResponseModel.getAttributes().put("newList", newList);
		return nlpResponseModel;
	}
}