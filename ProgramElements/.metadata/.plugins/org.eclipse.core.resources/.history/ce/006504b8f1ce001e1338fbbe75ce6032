package bussiness_logic;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.springframework.stereotype.Component;

@Component("LIC14952_PJT1001_PE_NLP66b5d01e-c08e-41bd-a91b-393c87bd5feb")
public class OpenNewTabUsingSwitchTo implements Nlp {
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
		WebDriver driver = nlpRequestModel.getWebDriver();
		Boolean result = true;
		// Your program element business logic goes here ...

		try {
			driver.switchTo().newWindow(WindowType.TAB);

			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("New Tab is Opened");
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("New Tab Failed to Open");
			result = false;
		}

		// String string3 = "Return Value";
		nlpResponseModel.getAttributes().put("result", result);
		return nlpResponseModel;
	}
}