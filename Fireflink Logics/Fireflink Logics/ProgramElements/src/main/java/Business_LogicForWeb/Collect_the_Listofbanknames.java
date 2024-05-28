package Business_LogicForWeb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import org.springframework.stereotype.Component;

@Component("LIC1710_PJT1014_PE_NLPb1803417-4e2d-40c3-9096-f20977090358")
public class Collect_the_Listofbanknames implements Nlp {
	@InputParams({ @InputParam(name = "listofBankXpath", type = "java.lang.String")})
	@ReturnType(name = "Getlistofbanknames", type = "java.util.List")

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
		String string1 = (String) attributes.get("listofBankXpath");
		List<String> listofbank = new ArrayList<>();
		try {
			WebDriver driver = (WebDriver) nlpRequestModel.getDriver().getSpecificIDriver();
			List<WebElement> banks = driver.findElements(By.xpath(string1));
			for (WebElement b : banks) {
				listofbank.add(b.getText());
			}
	
			nlpResponseModel.setMessage("Collected the list of bank names and clicked on one bank" + listofbank);
			nlpResponseModel.setStatus(CommonConstants.pass);
		} catch (Exception e) {

			nlpResponseModel.setMessage("Fail to collect the list of bank names and clicked on one bank" + e);
			nlpResponseModel.setStatus(CommonConstants.fail);

		}
		nlpResponseModel.getAttributes().put("Getlistofbanknames", listofbank);
		return nlpResponseModel;
	}
}

