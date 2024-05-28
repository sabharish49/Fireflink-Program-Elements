
package Logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

@Component("LIC19620_PJT1001_PE_NLPd86d5c86-fcbd-4368-b28f-143af5a665fc")
public class Select_Date implements Nlp {
    @InputParams({@InputParam(name = "Date", type = "java.lang.String")})
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
          String Date = (String) attributes.get("Date");
         AndroidDriver driver = nlpRequestModel.getAndroidDriver();
          try {
        	  String[] parts = Date.split("-");
              int k = 1;
              List<String> dateList = Arrays.asList(parts);
              while (true) {
                  if (k == 4) {
                  	 WebElement ele = driver.findElement(By.xpath("(//android.view.View[@content-desc=\"Okay\"]//android.widget.SeekBar)[1]"));
                       int bottomY = ele.getLocation().getY() + ele.getSize().getHeight()+100;
                       System.out.println("Bottom coordinate Y: " + bottomY);
                      int x = ele.getLocation().getX();
                      Thread.sleep(2000);
                      TouchAction<?> touchAction = new TouchAction<>(driver);
                      touchAction.tap(PointOption.point(x, bottomY)).perform();
                      System.out.println("click performed");
                      break;
                  }
                  
                  String month = dateList.get(0);
                  String day = dateList.get(1);
                  String year = dateList.get(2);
                  System.out.println("k value"+k);
             	  WebElement ele = driver.findElement(By.xpath("(//android.view.View[@content-desc='Okay']//android.widget.SeekBar)["+k+"]"));
                  String contentDesc = ele.getAttribute("content-desc");
                  System.out.println("Content Description: " + contentDesc);
                  if (contentDesc.equals(month) || contentDesc.equals(day) || contentDesc.equals(year)) {
                      k++;
                      continue;
                  }
                  int centerX = ele.getLocation().getX() + ele.getSize().getWidth() / 2;
                  int centerY = ele.getLocation().getY() + ele.getSize().getHeight() / 2;
                  int swipeDistance = 100;
                  TouchAction<?> touchAction1 = new TouchAction<>(driver);
                  touchAction1.press(PointOption.point(centerX, centerY - swipeDistance))
                             .moveTo(PointOption.point(centerX, centerY))
                             .release()
                             .perform();
                  System.out.println("Swiped down");
              }           
              nlpResponseModel.setStatus(CommonConstants.pass);
          	  nlpResponseModel.setMessage("Selected date Successfully");
		}catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.pass);
	     	nlpResponseModel.setMessage("Failed to select date"+e);
		}return nlpResponseModel;
      }
  } 