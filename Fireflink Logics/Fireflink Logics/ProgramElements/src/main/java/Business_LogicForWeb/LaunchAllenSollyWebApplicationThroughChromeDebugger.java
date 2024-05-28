package Business_LogicForWeb;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import io.github.bonigarcia.wdm.WebDriverManager;


import org.springframework.stereotype.Component;

@Component("LIC1710_PJT1014_PE_NLP02e0ef1b-c68e-4778-bd6e-baf66323aec6")
public class LaunchAllenSollyWebApplicationThroughChromeDebugger implements Nlp {

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
		
		try 
		{
			String dirFilePath		= "C:\\DataOfDebugger";
			String port				= "9292";
			String chromePath		= "C:\\Program Files\\Google\\Chrome\\Application";
			String cmdCommand1A		= "cmd /c start cmd.exe /K ";
			String cmdCommand1B		= "npx kill-port ";
			String cmdCommand1		= cmdCommand1A+cmdCommand1B+port;
			String cmdCommand2A		= "cmd /c start cmd.exe /K ";
			String cmdCommand2B		= "chrome.exe -remote-debugging-port=";
			String cmdCommand2C		= " --no-first-run --no-default-browser-check --user-data-dir=";
			String cmdCommand2		= cmdCommand2A+cmdCommand2B+port+cmdCommand2C+dirFilePath;
			String expOptionName	= "debuggerAddress";
			String expOptionValue	= "localhost:"+port;
			String url1				= "chrome://settings/resetProfileSettings?origin=userclick";
			String url2				= "chrome://settings/content/popups";
			String resetSettingsButton = "document.querySelector(\"body > settings-ui\").shadowRoot.querySelector(\"#main\").shadowRoot.querySelector(\"settings-basic-page\").shadowRoot.querySelector(\"#advancedPage > settings-section:nth-child(13) > settings-reset-page\").shadowRoot.querySelector(\"#reset-pages > div > settings-reset-profile-dialog\").shadowRoot.querySelector(\"#reset\")";
			String allowPopUpRadioButton = "document.querySelector(\"body > settings-ui\").shadowRoot.querySelector(\"#main\").shadowRoot.querySelector(\"settings-basic-page\").shadowRoot.querySelector(\"#basicPage > settings-section.expanded > settings-privacy-page\").shadowRoot.querySelector(\"#pages > settings-subpage > settings-category-default-radio-group\").shadowRoot.querySelector(\"#enabledRadioOption\").shadowRoot.querySelector(\"#button > div.disc-border\")";			
			
			try 
			{
				Runtime.getRuntime().exec(cmdCommand1);
				try {Thread.sleep(2000);} catch (InterruptedException e) {}
				Runtime.getRuntime().exec(cmdCommand2, null, new File(chromePath));
				try {Thread.sleep(2000);} catch (InterruptedException e) {}
			} 	catch (IOException e) {}

			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption(expOptionName, expOptionValue);
			WebDriverManager.chromedriver().setup();
			ChromeDriver driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			JavascriptExecutor jse=(JavascriptExecutor)driver;
			
			try 
			{
				try {Thread.sleep(2000);} catch (InterruptedException e) {}
		        Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
				try {Thread.sleep(2000);} catch (InterruptedException e) {}
		        Runtime.getRuntime().exec("taskkill /F /IM WindowsTerminal.exe");
			} 	catch (IOException e) {}	
			
			driver.get(url1);
			try {Thread.sleep(2000);} catch (InterruptedException e) {}
			WebElement element1 = (WebElement) jse.executeScript("return "+resetSettingsButton);
			try {element1.click();}catch (WebDriverException e) {}
			try {Thread.sleep(2000);} catch (InterruptedException e) {}
			driver.get(url2);
			try {Thread.sleep(2000);} catch (InterruptedException e) {}
			WebElement element2 = (WebElement) jse.executeScript("return "+allowPopUpRadioButton);
			try {element2.click();}catch (WebDriverException e) {}
			try {Thread.sleep(2000);} catch (InterruptedException e) {}			
			nlpResponseModel.setMessage("Browser is Launched Successfully");
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setWebDriver(driver);
		} 
		
		catch (Exception e) 
		{
			nlpResponseModel.setStatus(CommonConstants.fail);
		}
		return nlpResponseModel;
	}
}
