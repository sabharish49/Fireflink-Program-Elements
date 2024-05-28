package Business_LogicForWeb;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.PlatformType;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.Platform;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

@Platform(value = PlatformType.WEB)


@Component("LIC1710_PJT1014_PE_NLP2747f11f-9381-48d8-bf9c-732af7eb63ad")
public class ScrollUntilElementVisibleByCoordinatesAndScrollCount implements Nlp {
		@InputParams({@InputParam(name = "element", type = "org.openqa.selenium.WebElement"),
		@InputParam(name = "elementType", type = "java.lang.String"),
		@InputParam(name = "elementName", type = "java.lang.String"),
		@InputParam(name = "setYcoordinates", type = "java.lang.String"),
		@InputParam(name = "setScrollCount", type = "java.lang.Integer")})
		@ReturnType(name = "getBoolean",      type = "java.lang.Boolean")

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
		
		WebElement element 		= (WebElement) attributes.get("element");
		String setYcoordinates 	= (String) attributes.get("setYcoordinates");
		Integer setScrollCount 	= (Integer) attributes.get("setScrollCount");
		WebDriver driver = (WebDriver) nlpRequestModel.getWebDriver();
		Duration impWait=driver.manage().timeouts().getImplicitWaitTimeout();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		int count = 0;
		boolean getBoolean = false;
		String str = "window.scrollBy(0,";
		str = str.concat(setYcoordinates+")");
		try 
		{

			while (count < setScrollCount) 
			{
				try 
				{
					wait.until(ExpectedConditions.visibilityOf(element));
					getBoolean= true;
					break;
				}
				catch (Exception e) 
				{
					jse.executeScript(str, "");
					count++;
				}
			}
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Web Page has been Scrolled Until Element Visible");
		} 
		catch (Exception e) 
		{
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Web Page has been Scrolled But Element Not Found");
		}
        finally 
        {
      	  driver.manage().timeouts().implicitlyWait(impWait);
        }
		nlpResponseModel.getAttributes().put("getBoolean", getBoolean);
		return nlpResponseModel;
	}
}