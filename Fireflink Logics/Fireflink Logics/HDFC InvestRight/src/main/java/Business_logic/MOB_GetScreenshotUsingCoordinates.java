package Business_logic;




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

import java.util.Map;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.stereotype.Component;

@Component("LIC14172_PJT1001_PE_NLP5204d603-fc3e-4c33-88e0-8d3ad9c4d570")
public class MOB_GetScreenshotUsingCoordinates implements Nlp {
	@InputParams({@InputParam(name = "FilePath", type = "java.lang.String"),@InputParam(name = "Xcoordinate", type = "java.lang.Integer"), @InputParam(name = "Ycoordinate", type = "java.lang.Integer"),@InputParam(name = "X1", type = "java.lang.Integer"),@InputParam(name = "X2", type = "java.lang.Integer"),@InputParam(name = "Y1", type = "java.lang.Integer"),@InputParam(name = "Y2", type = "java.lang.Integer")})
	//@ReturnType(name = "Path", type = "java.lang.String")

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
		Integer Xcoordinate = (Integer) attributes.get("Xcoordinate");
		Integer Ycoordinate = (Integer) attributes.get("Ycoordinate");
		Integer X1 = (Integer) attributes.get("X1");
		Integer X2 = (Integer) attributes.get("X2");
		Integer Y1 = (Integer) attributes.get("Y1");
		Integer Y2 = (Integer) attributes.get("Y2");
		String FilePath=(String) attributes.get("FilePath");
		
		// Your program element business logic goes here ...
		AppiumDriver driver = (AndroidDriver) nlpRequestModel.getDriver().getSpecificIDriver();
		try {
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			
			int width = Math.abs(X2-X1);

		
			int height = Math.abs(Y2-Y1);
			// Replace x, y, width, and height with your desired coordinates
			

			// Crop the screenshot to the specified coordinates
			BufferedImage fullImg = ImageIO.read(screenshotFile);
			BufferedImage croppedImg = fullImg.getSubimage(Xcoordinate,Ycoordinate,width, height);

			// Save the cropped image to a file
			File outputFile = new File(FilePath);
			ImageIO.write(croppedImg, "png", outputFile);
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Sucessfully Captured Screenshot");
		}catch(Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to Capture Screenshot"); 
		}

		
	//	nlpResponseModel.getAttributes().put("string3", string3);
		return nlpResponseModel;
	}
} 