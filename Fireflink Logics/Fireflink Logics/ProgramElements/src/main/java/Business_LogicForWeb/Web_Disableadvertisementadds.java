package Business_LogicForWeb;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import io.github.bonigarcia.wdm.WebDriverManager;

@Component("LIC1710_PJT1014_PE_NLP703e7df6-c1be-4ebd-8aae-ac344600a156")
public class Web_Disableadvertisementadds implements Nlp {
	@InputParams({@InputParam(name = "File path", type = "java.lang.String") ,
		@InputParam(name = "URL", type = "java.lang.String")})
	//	@ReturnType(name = "BrowserCapablity", type = "openqa.selenium.remote.DesiredCapabilities")

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
		//		DesiredCapabilities cap = (DesiredCapabilities) attributes.get("Desired Capabilities");
		String path = (String) attributes.get("File path");
		String url = (String) attributes.get("URL");

		// Your program element business logic goes here ...
		try {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options=new ChromeOptions();
			options.addExtensions (new File(path));
			options.addArguments("--remote-allow-origins=*");
			WebDriver driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
			driver.manage().window().maximize();

			driver.get(url);

			Set<String> windows = driver.getWindowHandles();
			//			List l=new ArrayList();
			for (String window : windows) {
				driver.switchTo().window(window);
				String url1=driver.getCurrentUrl();
				if(!url.equals(url1))
				{

					driver.close();

				}

			}
			windows = driver.getWindowHandles();
			for (String windo : windows) {
				driver.switchTo().window(windo);
			}
			nlpResponseModel.setWebDriver(driver);
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Chrome Extension is added Successfully");

		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to add the Chrome Extension"+e);
		}
		//		nlpResponseModel.setDesiredCapabilities(cap);
		//		nlpResponseModel.getAttributes().put("BrowserCapablity", cap);
		return nlpResponseModel;
	}
}
