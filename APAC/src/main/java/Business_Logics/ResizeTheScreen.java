package Business_Logics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;




public class ResizeTheScreen implements Nlp {
	@InputParams({ @InputParam(name = "zoom level", type = "java.lang.String") })
	@ReturnType(name = "isZoomed", type = "java.lang.Boolean")

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
		String level = (String) attributes.get("zoom level");

		boolean isZoomed = false;

		WebDriver driver = nlpRequestModel.getWebDriver();
		
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.body.style.zoom='"+level+"'");
			isZoomed = true;
			
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Page is Zoomed");
		} catch (Exception e) {
			isZoomed = false;
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Page is not Zoomed");

		}

		
		nlpResponseModel.getAttributes().put("isZoomed", isZoomed);
		return nlpResponseModel;
	}

}