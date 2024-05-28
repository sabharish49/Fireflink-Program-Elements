
package Business_Logics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import org.springframework.stereotype.Component;

@Component("LIC19307_PJT1001_PE_NLP4b8cdefc-fae1-4825-817b-601c9b938045")
public class RGB_Colour_Validation implements Nlp {
    @InputParams({@InputParam(name = "element", type = "org.openqa.selenium.WebElement"),@InputParam(name = "ExpctedRGB", type = "java.lang.String")})
    //@ReturnType(name = "string3", type = "java.lang.String")
    
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
          String RGB = (String) attributes.get("ExpctedRGB");
          WebElement element = (WebElement) attributes.get("element");
          WebDriver driver = nlpRequestModel.getWebDriver();
          
          try {
        	  Actions act=new Actions(driver);
              act.moveToElement(element).perform();
              Thread.sleep(2000);
             String actualColor = element.getCssValue("text-decoration-color");
              int[] actualRGB = parseRGB(actualColor);
              String[] rgbArray = RGB.split(",");
              int[] expectedRGB = new int[3];   
              for (int i = 0; i < 3; i++) {
              	expectedRGB[i] = Integer.parseInt(rgbArray[i]);
              }
              if (compareColors(actualRGB, expectedRGB)) {
            	  nlpResponseModel.setStatus(CommonConstants.pass);
 	        	 nlpResponseModel.setMessage("Expected RGB "+RGB+" mathces Highlited Element "+actualColor+"");
              } else {
            	  nlpResponseModel.setStatus(CommonConstants.fail);
             	 nlpResponseModel.setMessage("Expected RGB "+RGB+" does not mathces Highlited Element "+actualColor+"");
              }
            
		} catch (Exception e) {
			 nlpResponseModel.setStatus(CommonConstants.fail);
        	 nlpResponseModel.setMessage("Expected RGB "+RGB+" does not mathces Highlited Element"+e);
		}
          return nlpResponseModel;
      }   
      private static int[] parseRGB(String color) {
          Pattern pattern = Pattern.compile("\\d+");
          Matcher matcher = pattern.matcher(color);
          int[] rgb = new int[3];
          for (int i = 0; i < 3; i++) {
              matcher.find();
              rgb[i] = Integer.parseInt(matcher.group());
          }
          return rgb;
      }
          private static boolean compareColors(int[] color1, int[] color2) {
          for (int i = 0; i < 3; i++) {
              if (color1[i] != color2[i]) {
                  return false;
              }
          }
          return true;
      }
  } 