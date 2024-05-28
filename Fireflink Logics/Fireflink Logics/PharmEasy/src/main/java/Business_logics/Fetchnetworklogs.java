package Business_logics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import org.springframework.stereotype.Component;

@Component("LIC14952_PJT1005_PE_NLP25278be1-7aa8-45f6-b183-52ae8d28efd7")
public class Fetchnetworklogs implements Nlp {
	@ReturnType(name = "filteredLogs", type = "java.lang.String")

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
		List<String> filteredLogs = new ArrayList<>();
		String bearerToken="";

		try {

			// Retrieve network logs
			WebDriver driver = nlpRequestModel.getWebDriver();
			LogEntries logEntries = driver.manage().logs().get(LogType.PERFORMANCE);
				for (LogEntry entry : logEntries) {
					String message = entry.getMessage();
			if (message.contains("\"Authorization\":\"bearer ")) {
						filteredLogs.add(message);					
					}
				}
				Pattern pattern = Pattern.compile("bearer\\s(.*?)\"");
		        Matcher matcher = pattern.matcher(filteredLogs.get(0));
		        if (matcher.find()) {
		            bearerToken = matcher.group(1);
		            System.out.println(bearerToken);
		        } else {
		            System.out.println("Bearer token not found.");
		        }	
			
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully Fetched Network Logs");

		} catch (Exception e) {

			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Failed To Fetch Network Logs");
		}
		nlpResponseModel.getAttributes().put("filteredLogs", bearerToken);
		return nlpResponseModel;
	}
}