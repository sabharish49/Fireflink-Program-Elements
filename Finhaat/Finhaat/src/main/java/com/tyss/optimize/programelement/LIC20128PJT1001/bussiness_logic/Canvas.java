package bussiness_logic;


import static io.appium.java_client.touch.WaitOptions.waitOptions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Slf4j
@Component("LIC20128_PJT1001_PE_NLP0c4aecdf-f7d3-4344-94c2-7dc8404c0c12")
public class Canvas implements Nlp {
    @InputParams({@InputParam(name = "Tenure", type = "java.lang.String"),@InputParam(name = "Canvas Element", type = "org.openqa.selenium.WebElement"),@InputParam(name = "Segment", type = "java.lang.String")})
    @ReturnType(name = "output", type = "java.lang.String")


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
		String	tenure=(String)attributes.get("Tenure");
		String	segment=(String)attributes.get("Segment");
		WebElement canvas=(WebElement) attributes.get("Canvas Element");
		Integer total=0;
		int partition=0;
		try {
			if(tenure.equalsIgnoreCase("3M")) {
				partition=3;
			}
			else if(tenure.equalsIgnoreCase("6M")) {
				partition=6;
			}
else if(tenure.equalsIgnoreCase("1M")) {
	partition=1;	
			}
else if(tenure.equalsIgnoreCase("1Y")) {
	partition=12;
}
			
	WebDriver driver = nlpRequestModel.getWebDriver();
	int width = canvas.getSize().width;
	int diff=width/partition;
	System.out.println("Diff is-- "+diff);
	Actions actions = new Actions(driver);
	 for (int i = ((width/2)-6); i >= -(width/2); i-=diff) {
		 System.out.println(i);
		 actions.moveToElement(canvas, i, 170).perform();
		 Thread.sleep(800);
			String s=driver.findElement(By.xpath("//label[.='"+segment.toLowerCase()+"']/../following-sibling::strong")).getText();
			total +=Canvas.dummy(s, 0);  
        }

			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Total claims in "+segment+" is " +total);

		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			//log.info(exceptionAsString);
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed calculate the claims count " + exceptionAsString);

		}
		nlpResponseModel.getAttributes().put("output",total.toString() );
		return nlpResponseModel;
	}
	public static int  dummy(String s,int b) {
		try {
			return Integer.parseInt(s);
		}
		catch(Exception e) {
			return b;
		}
		
	}
	
}
