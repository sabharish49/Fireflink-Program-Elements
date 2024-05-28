
package Business_Logics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;

import org.springframework.stereotype.Component;

@Component("LIC19046_PJT1001_PE_NLP193f36e8-86aa-4123-91a4-739288fbb101")
public class selectStandardOfLiving implements Nlp {
	@InputParams({ @InputParam(name = "stringInput", type = "java.lang.String")})
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
		String stringInput = (String) attributes.get("stringInput");

		boolean res = false;
		try {
			AndroidDriver driver = nlpRequestModel.getAndroidDriver();
			ArrayList<String> list = new ArrayList<String>();

			String s = stringInput;

			String[] arr = s.split(":");

			List<WebElement> list1 = driver
					.findElements(By.xpath("//androidx.viewpager.widget.ViewPager//android.widget.TextView"));

			for (WebElement element : list1) {
				String text = element.getText();
				System.out.println(text);
				for (String o : arr) {
					if (text.contains(o)) {
						String xpath = "//android.widget.TextView[contains(@text,'" + text
								+ "')]/following-sibling::android.widget.LinearLayout//android.widget.RadioButton[@text='Yes']";
						System.out.println(xpath);
						driver.findElement(By.xpath(xpath)).click();
						System.out.println(o + " is selected");
						break;
					}
				}

			}
			res=true;
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Standard of living is set Perfectly");

		} catch (Exception e) {
			res=false;
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to set Standard of living  "+e);
		}

		nlpResponseModel.getAttributes().put("result", res);
		return nlpResponseModel;
	}
}