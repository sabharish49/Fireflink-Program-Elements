
package business_Logics;

import java.awt.Dimension;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
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

public class Signature1 implements Nlp {
	@InputParams({ @InputParam(name = "element", type = "org.openqa.selenium.WebElement"),
		@InputParam(name = "Signature", type = "java.lang.String"),})

      public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {

          Map<String, Object> programElementsInput = nlpRequestModel.getAttributes();
      	
          NlpResponseModel nlpResponseModel = new NlpResponseModel();
          WebElement canvas = (WebElement) programElementsInput.get("element");
          String sign = (String) programElementsInput.get("Signature");
          int txtLength = sign.length();
  		int partLength = (txtLength * 25) / 6;
  		int startX = 50;
  		int startY = 50;
          
          Integer returnValue=null;
          

          try {
        	  WebDriver driver = nlpRequestModel.getWebDriver();
        	/*	((JavascriptExecutor) driver)
        		.executeScript("var ctx = arguments[0].getContext('2d');" + "ctx.font = '40px Segoe Script';" + // Set
        				// font
        				// size
        				// and
        				// style
        				"ctx.fillStyle = 'green';" + // Set text color
        				"ctx.fillText('" + sign + "', " + startX + ", " + startY + ");" + // Draw the name at specified
        				// coordinates
        				"ctx.beginPath();" + "ctx.lineWidth = 3;" + // Set the line width
        				"ctx.strokeStyle = 'green';" + "ctx.moveTo(" + startX + ", " + (startY + 15) + ");" + // Starting
        				// point
        				// for
        				// the
        				// curve
        				"ctx.quadraticCurveTo(" + (startX + partLength) + ", " + (startY + 20) + ", "
        				+ (startX + (2 * partLength)) + ", " + (startY + 15) + ");" + // Define the quadratic Bézier
        				// curve
        				"ctx.quadraticCurveTo(" + (startX + (3 * partLength)) + ", " + (startY + 10) + ", "
        				+ (startX + (4 * partLength)) + ", " + (startY + 15) + ");" + // Define the quadratic Bézier
        				// curve
        				"ctx.quadraticCurveTo(" + (startX + (5 * partLength)) + ", " + (startY + 20) + ", "
        				+ (startX + (6 * partLength)) + ", " + (startY + 15) + ");" + // Define the quadratic Bézier
        				// curve
        				"ctx.stroke();", canvas);
        				*/

        		org.openqa.selenium.Dimension dim = canvas.getSize();
        		int height = dim.height;
        		int width = dim.width;
        		// Perform mouse actions to draw a line below the name
        		Actions builder = new Actions(driver);
        		Actions act = builder
        				.moveToElement(canvas, -(width / 2) + (startX + (6 * partLength) + 5), -(height / 2) + (startY + 15))
        				.clickAndHold();
        		act.moveToElement(canvas, -(width / 2) + (startX + (6 * partLength) + 10), -(height / 2) + (startY + 15))
        		.release().build().perform();


              nlpResponseModel.setStatus(CommonConstants.pass);
              nlpResponseModel.setMessage("Signature has done successfully");
          }
          catch(Exception e) {
          	 // Your program element Exception handling goes here ...
             nlpResponseModel.setStatus(CommonConstants.fail);
             nlpResponseModel.setMessage("Signature is unsuccessfull " + e);

             //Uncomment the below line and modify the message, only if you want to override the fail message defined for the program element.
             //nlpResponseModel.setMessage("Failed to add numbers");
          }

          // Your program element business logic ends here ...
         // nlpResponseModel.getAttributes().put("Sum of Two numbers", returnValue);
          return nlpResponseModel;
      }

  } 
