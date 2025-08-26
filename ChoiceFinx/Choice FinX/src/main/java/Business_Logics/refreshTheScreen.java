
package Business_Logics;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

import java.util.Map;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.springframework.stereotype.Component;

@Component("LIC20369_PJT1002_PE_NLP5e1b4f94-f212-4415-bd65-6cc777d2afba")
public class refreshTheScreen implements Nlp {
//    @InputParams({@InputParam(name = "string1", type = "java.lang.String"), @InputParam(name = "string2", type = "java.lang.String")})
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


          AndroidDriver driver = nlpRequestModel.getAndroidDriver();
          	try {
          		
          		refreshScreen(driver);
          		nlpResponseModel.setStatus(CommonConstants.pass);
          		nlpResponseModel.setMessage("Successfully refreshed the screen");
          		
			} catch (Exception e) {
				
          		nlpResponseModel.setStatus(CommonConstants.fail);
          		nlpResponseModel.setMessage("Failed to refresh the screen "+e);
			}


          return nlpResponseModel;
      }
      public static void refreshScreen(AndroidDriver driver) {
          // Define a finger as a pointer input
          PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

          // Define the start and end points of the swipe
          int startX=500;
          int startY=1000;
          int endX=500;
          int endY=1300;
         
          PointOption start = PointOption.point(500, 1000);
          PointOption end = PointOption.point(500, 1500);

          // Define the sequence of actions for the swipe
          Sequence swipe = new Sequence(finger, 1)
                  .addAction(finger.createPointerMove(Duration.ZERO, Origin.viewport(), startX,startY))
                  .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                  .addAction(finger.createPointerMove(Duration.ofMillis(1000), Origin.viewport(), endX,endY))
                  .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

          // Perform the swipe action
           driver.perform(Arrays.asList(swipe));
           
      }
  } 