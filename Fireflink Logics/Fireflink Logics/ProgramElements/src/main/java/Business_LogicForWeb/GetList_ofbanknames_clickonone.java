package Business_LogicForWeb;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
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

@Component("LIC1710_PJT1014_PE_NLP573e772e-89c6-4ca0-8e49-d9f21e52ff1d")
public class GetList_ofbanknames_clickonone implements Nlp {
	@InputParams({ @InputParam(name = "listofBanks", type = "java.util.List"),
		@InputParam(name = "bankname", type = "java.Lang.String")})


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
		String string1 = (String) attributes.get("bankname");
		List listofbank = (List)attributes.get("listofBanks");
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


