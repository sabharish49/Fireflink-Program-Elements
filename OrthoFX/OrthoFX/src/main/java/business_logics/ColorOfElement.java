	
package business_logics;

import com.tyss.optimize.common.util.*;
import com.tyss.optimize.nlp.util.*;
import com.tyss.optimize.nlp.util.annotation.*;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ColorOfElement implements Nlp {
    @InputParams({@InputParam(name = "Element", type = "org.openqa.selenium.WebElement")})
    @ReturnType(name = "colorString", type = "java.lang.String")

      public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {
        
          NlpResponseModel nlpResponseModel = new NlpResponseModel();
          Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
          WebElement element = (WebElement) programElementsInput.get("Element");
          String hex="";
          String color=null;
          try {
            File screenshot = element.getScreenshotAs(OutputType.FILE);
            int height = element.getSize().getHeight();
            int width=element.getSize().getWidth();
                    // Convert the screenshot file to a BufferedImage
                    BufferedImage elementScreenshot = ImageIO.read(screenshot);

                    // Get RGB color of a specific pixel (e.g., the top-left pixel)
                    int pixelColor = elementScreenshot.getRGB(width/2, height/2);
                    //int pixelColor = elementScreenshot.getRGB(20, 20);

                    // Extract the RGB values
                    int red = (pixelColor >> 16) & 0xFF;
                    int green = (pixelColor >> 8) & 0xFF;
                    int blue = pixelColor & 0xFF;
        		hex = String.format("#%02X%02X%02X", red, green, blue);
        		color=getColorName(hex);
        		nlpResponseModel.setMessage("The Color is : "+color);
              nlpResponseModel.setStatus(CommonConstants.pass);
              }
          catch(Exception e) {
        	  StringWriter sw = new StringWriter();
    			e.printStackTrace(new PrintWriter(sw));
    			String exceptionAsString = sw.toString();
              nlpResponseModel.setMessage("Failed to find color : "+exceptionAsString);
              nlpResponseModel.setStatus(CommonConstants.fail);
              }

          // Your program element business logic ends here ...
          nlpResponseModel.getAttributes().put("colorString", color);
          return nlpResponseModel;
      }

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
      public static String getColorName(String hexCode) {
  		try {
  			URL url = new URL("https://www.crispedge.com/color/" + hexCode.substring(1));
  			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
  			connection.setRequestMethod("GET");
  			int responseCode = connection.getResponseCode();
  			if (responseCode == HttpURLConnection.HTTP_OK) {
  				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
  				String inputLine;
  				StringBuilder response = new StringBuilder();
  				while ((inputLine = in.readLine()) != null) {
  					response.append(inputLine);
  				}
  				in.close();
  				String jsonString = response.toString();
  				String regex = "#[a-fA-F0-9]{6} hex color - [\\w\\s-]+";
  				Pattern pattern = Pattern.compile(regex);
  				Matcher matcher = pattern.matcher(jsonString);
  				  String result="";
  				if (matcher.find()) {
  				 result  = matcher.group(0);
  				    System.out.println(result);
  				}
  				return result;
  			} else {
  				return "Unknown";
  			}
  		} catch (IOException e) {
  			e.printStackTrace();
  			return "Error";
  		}
  	}
  } 