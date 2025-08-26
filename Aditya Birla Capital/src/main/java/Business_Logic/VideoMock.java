package Business_Logic;



import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.data.models.dto.drivers.WebDriver;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
 

@Component("LIC19774_PJT1002_PE_NLP01d361f1-6c91-4477-961e-fe864720012e")
public class VideoMock implements Nlp {
	@InputParams({ @InputParam(name = "Desired Capabilities", type = "org.openqa.selenium.remote.DesiredCapabilities"),@InputParam(name = "File Stream", type = "java.io.InputStream") })
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
		DesiredCapabilities cap = (DesiredCapabilities) attributes.get("Desired Capabilities");
		InputStream	inputStream	=(InputStream)attributes.get("File Stream");
		try {
		     
		        
		    	 DesiredCapabilities dc=new DesiredCapabilities();
		    	  String outputFile = System.getProperty("user.dir")+"\\"+"outputout.y4m";
		    	  FileOutputStream outputStream = new FileOutputStream(outputFile);

		    	    byte[] buffer = new byte[1024];
		    	    int bytesRead;
		    	    while ((bytesRead = inputStream.read(buffer)) != -1) {
		    	        outputStream.write(buffer, 0, bytesRead);
		    	    }

		    	    inputStream.close();
		    	    outputStream.close();
		    	    ChromeOptions options = new ChromeOptions();
		      		LoggingPreferences logPrefs = new LoggingPreferences();
		      		options.addArguments("--remote-allow-origins=*");
		    		options.addArguments("--allow-file-access-from-files", "--use-fake-ui-for-media-stream",
		    		"--allow-file-access");
		    		options.addArguments("--use-fake-device-for-media-stream");
		    		options.addArguments("--use-file-for-fake-video-capture=" + outputFile);
		      		options.setCapability("goog:loggingPrefs", logPrefs);
		      	   dc.setCapability(ChromeOptions.CAPABILITY, options);
                   cap.merge(dc);
           
		      
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Success");

		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed To Add Arguments");
		}
		return nlpResponseModel;
	}
}