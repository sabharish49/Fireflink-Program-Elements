
package Business_Logics;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;
import lombok.extern.slf4j.Slf4j;

public class GetCount implements Nlp {
	@InputParams({ @InputParam(name = "Product Value", type = "java.lang.String")})
		@ReturnType(name = "count", type = "java.lang.String")

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
		String input = (String) attributes.get("Product Value");
		AndroidDriver driver = nlpRequestModel.getAndroidDriver();
		 double count = 0;
		try {
	String getText = driver.findElement(By.xpath("//*[@resource-id='com.c2info.wellnessforever:id/description']")).getText();
	Pattern pattern = Pattern.compile("\\d+");
    Matcher matcher = pattern.matcher(extractFirstLine(getText));
    List<Integer> numbers = new ArrayList<>();
    while (matcher.find()) {
        numbers.add(Integer.parseInt(matcher.group()));
    }
    input=input.replace('₹', ' ').trim();
    double parsedInt=Double.parseDouble(input);
   // System.out.println("The Parsed value is : "+parsedInt);
    double subtractedValue = numbers.get(1)-parsedInt;
     count = subtractedValue/parsedInt+1;
	
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Buy not found after Swiping multiple times");
			
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString(); 
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to get the target" + exceptionAsString);
		}

		nlpResponseModel.getAttributes().put("count", count);
		return nlpResponseModel;
	}
	 public static String extractFirstLine(String text) {
	        String[] lines = text.split("\n");
	        return lines.length > 1 ? lines[1].trim() : lines[0].trim();  // Return second line, since it's the  Rs.150 one
	    }
	

}