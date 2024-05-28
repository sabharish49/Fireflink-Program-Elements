
package Logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;

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

@Component("LIC19620_PJT1001_PE_NLP3093d299-4265-4cbb-bd41-45ca75774a67")
public class applyFiltersVetic implements Nlp {
	@InputParams({ @InputParam(name = "Input", type = "java.lang.String") })
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
		String Input = (String) attributes.get("Input");

		// Your program element business logic goes here ...
		boolean res = false;
		try {
			AndroidDriver driver = nlpRequestModel.getAndroidDriver();
			String exp = Input;

			String[] exp1 = exp.split(":");

			for (String string : exp1) {
				String[] expression = string.split("-");

				String category = expression[0];
				driver.findElement(By.xpath("//android.view.View[@content-desc='" + category + "']")).click();

				for (int i = 1; i < expression.length; i++) {
					String subcategory = expression[i];
					driver.findElement(By.xpath("//android.view.View[@content-desc='" + subcategory + "']")).click();
				}
			}
			driver.findElement(By.xpath("//android.view.View[@content-desc=\"Apply\"]")).click();
			res = true;

			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Filters applied successfully");

		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to set filters");

		}

		nlpResponseModel.getAttributes().put("result", res);
		return nlpResponseModel;
	}
}