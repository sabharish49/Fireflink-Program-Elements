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
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;




@Slf4j
@Component("LIC1710_PJT1014_PE_NLP3f1a4a68-9318-421e-9e72-54070d997b0f")
public class ClickBySize implements Nlp {
	@InputParams({ @InputParam(name = "stringXpath", type = "java.lang.String")})
	@ReturnType(name = "elementSize",      type = "java.lang.Integer")

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
		Integer elementSize = 0;
		String stringXpath = (String) attributes.get("stringXpath");
		//log.info("Entering in to the try Block");
		try 
		{
		//	log.info("Entered the try Block");
			
			WebDriver driver = (WebDriver) nlpRequestModel.getDriver().getSpecificIDriver();
			List<WebElement> ele = driver.findElements(By.xpath(stringXpath));
			elementSize = ele.size();
			
			for(WebElement eleNew : ele)
			{
			//	log.info("Entered the for loop");
				Thread.sleep(3000);
				eleNew.click();
				Thread.sleep(5000);
			//	log.info("for loop Executed completly");
			}
				nlpResponseModel.setMessage(" Size of Element is :   "+elementSize);
				nlpResponseModel.setStatus(CommonConstants.pass);
				//log.info("try Block has Executed completly");
		}
		catch (Exception e) 
		{
			nlpResponseModel.setMessage(" Something went wrong ");
			nlpResponseModel.setStatus(CommonConstants.fail);
		}
		nlpResponseModel.getAttributes().put("elementSize", elementSize);
		return nlpResponseModel;
	}
}
