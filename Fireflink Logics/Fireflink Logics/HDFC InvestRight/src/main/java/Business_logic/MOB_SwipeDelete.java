package Business_logic;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Point;
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

import io.appium.java_client.android.AndroidDriver;

import org.springframework.stereotype.Component;

@Component("LIC14172_PJT1001_PE_NLPa616fb17-5946-4c78-bbcd-d041d41002dd")
public class MOB_SwipeDelete implements Nlp {
		@InputParams({@InputParam(name = "Element", type = "org.openqa.selenium.WebElement"),@InputParam(name = "xCoordinate", type = "java.lang.Integer"),
			@InputParam(name = "yCoordinate", type = "java.lang.Integer")})
	    

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
	          int yCoordinate = (Integer) attributes.get("yCoordinate");
	          int xCoordinate = (Integer) attributes.get("xCoordinate");
	          WebElement Element= (WebElement)attributes.get("Element");
	         
	 
	      		try {
	      			 AndroidDriver driver=(AndroidDriver)nlpRequestModel.getAndroidDriver();
	      			Point point = Element.getLocation();
	      			//int x=point.getX();
	      			int y=point.getY();
	      			PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger");
	      			Sequence tap = new Sequence(input, 0);
	      			tap.addAction(input.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(),xCoordinate ,y+yCoordinate ));
	      			tap.addAction(input.createPointerDown(0));
	      			tap.addAction(input.createPointerUp(0));
	      			driver.perform(Arrays.asList(tap));
	         
	        nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully deleted the Stock " +tap);
	    			
	      		}
	      		catch (Exception e) 
	      		{
	      			nlpResponseModel.setStatus(CommonConstants.fail);
	    			nlpResponseModel.setMessage("Failed to delete the stock " +e);
	      		}
	 
	          return nlpResponseModel;
	      }
	  } 