package Business_LogicForWeb;


import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import io.github.bonigarcia.wdm.WebDriverManager;



import org.springframework.stereotype.Component;

@Component("LIC1710_PJT1014_PE_NLP4cdffc6b-6134-46a2-b4e9-a9f90079937f")
public class Authentication implements Nlp {
	@InputParams({@InputParam(name = "Filepath", type = "java.lang.String")})
	//    @ReturnType(name = "string3", type = "java.lang.String")

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
		         String filepath = (String) attributes.get("Filepath");
		//          String string2 = (String) attributes.get("string2");
		// Your program element business logic goes here ...
		try {
			/*
			 * // WebDriver driver = new ChromeDriver();
			 * WebDriverManager.chromedriver().setup(); ChromeOptions opt=new
			 * ChromeOptions(); opt.addArguments("--remote-allow-origins=*");
			 * DesiredCapabilities caps = new DesiredCapabilities();
			 * caps.setBrowserName("chrome"); caps.setCapability(ChromeOptions.CAPABILITY,
			 * opt);
			 * 
			 */
//			WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),caps);
			 WebDriverManager.chromedriver().setup();
			 ChromeOptions opt=new ChromeOptions();
			 opt.addArguments("--remote-allow-origins=*");
			 opt.addExtensions(new File(filepath));
			WebDriver driver = new ChromeDriver(opt);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10000));
			driver.manage().window().maximize();
			String UserName="testbranduser";
			String PWD="P@ssword123";
			

	((HasAuthentication) driver).register(UsernameAndPassword.of(UserName, PWD));
			String url ="https://test.allensolly.abfrl.in/";
			/*
			 * String URL = "http://" + UserName+ ":" + PWD + "@" + url; driver.get(URL);
			 * Alert alert = driver.switchTo().alert(); alert.accept();
			 */
			driver.get(url);

			nlpResponseModel.setMessage("Pass");
			nlpResponseModel.setStatus(CommonConstants.pass);
		} catch (Exception e) {
			nlpResponseModel.setMessage("Failed"+e);
			nlpResponseModel.setStatus(CommonConstants.fail);
			// TODO: handle exception
		}
		//          String string3 = "Return Value";
		//          nlpResponseModel.getAttributes().put("string3", string3);
		return nlpResponseModel;
	}
} 