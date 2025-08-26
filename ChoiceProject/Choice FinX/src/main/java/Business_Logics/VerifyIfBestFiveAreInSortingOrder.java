
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

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class VerifyIfBestFiveAreInSortingOrder implements Nlp {
    @InputParams({@InputParam(name = "Xpath", type = "java.lang.String"), @InputParam(name = "SortingType", type = "java.lang.String")})
    @ReturnType(name = "result", type = "java.lang.Boolean")

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
          String Xpath = (String) attributes.get("Xpath");
          String SortingType = (String) attributes.get("SortingType");

          AndroidDriver driver = nlpRequestModel.getAndroidDriver();
          boolean result=false;
         try {
        	 List<WebElement> listOfElements = driver.findElements(By.xpath(Xpath));

     		ArrayList<Double> list = new ArrayList<Double>();
     		for (WebElement webElement : listOfElements) {
     			String value = webElement.getText();
     			if (value.contains("Bid") || value.contains("Ask") || value.contains("Total Qty")) {
     				continue;
     			}
     			value=value.replaceAll(",", "");
     			list.add(Double.parseDouble(value));
     			
     		}
     		
     		for (Double double1 : list) {
     			System.out.println(double1);
     		}

     		String SortingOrder = "Descending";
     		result = checkSortingOrder(SortingOrder, list);
     		System.out.println(result);
     		
     		if(result==true) {
     			nlpResponseModel.setStatus(CommonConstants.pass);
     			nlpResponseModel.setMessage("Successfully verified if all the values in Sorting order ");
     		}
     		else {
     			nlpResponseModel.setStatus(CommonConstants.fail);
     			nlpResponseModel.setMessage("Failed to validate if all the values in Sorting order ");
     		}
        	 
			
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
 			nlpResponseModel.setMessage("Failed to validate if all the values in Sorting order ");
		}

          
          nlpResponseModel.getAttributes().put("result", result );
          return nlpResponseModel;
      }
      private static boolean checkSortingOrder(String SortingOrder, ArrayList<Double> list) {
  		boolean result = true;
  		
  		if (SortingOrder.equalsIgnoreCase("Ascending")) {
  			for (int i = 0; i < list.size() - 1; i++) {
  				if (list.get(i) <= list.get(i + 1)) {
  					continue;
  				} else {
  					result = false;
  					break;
  				}
  			}

  		}

  		if (SortingOrder.equalsIgnoreCase("Descending")) {
  			for (int i = 0; i < list.size() - 1; i++) {
  				if (list.get(i) >= list.get(i + 1)) {
  					continue;
  				} else {
  					result = false;
  					break;
  				}
  			}

  		}

  		return result;
  	}
  } 