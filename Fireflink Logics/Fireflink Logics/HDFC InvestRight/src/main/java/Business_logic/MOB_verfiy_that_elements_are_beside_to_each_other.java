package Business_logic;



import com.tyss.optimize.common.util.CommonConstants;

import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;
import io.burt.jmespath.antlr.v4.runtime.tree.xpath.XPath;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

@Slf4j


@Component("LIC14172_PJT1001_PE_NLPfdaa014d-45ff-4ff9-8e72-39aacae8c667")
public class MOB_verfiy_that_elements_are_beside_to_each_other implements Nlp {
    @InputParams({@InputParam(name = "Static", type = "org.openqa.selenium.WebElement"),@InputParam(name = "Reference", type = "org.openqa.selenium.WebElement"),@InputParam(name = "maxYDifference", type = "java.lang.Integer")})
  // @ReturnType(name = "result", type = "java.lang.String")
   

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
          WebElement Static = (WebElement) attributes.get("Static");
          WebElement Reference = (WebElement) attributes.get("Reference");
          Integer maxYDifference = (Integer) attributes.get("maxYDifference");
         
          
          try {
        		AndroidDriver driver = nlpRequestModel.getAndroidDriver();
     	        // Get the X and Y coordinates of the elements
     	        int element1X = Static.getLocation().getX();
     	        int element1Y = Static.getLocation().getY();
     	        int element2X = Reference.getLocation().getX();
     	        int element2Y = Reference.getLocation().getY();

     	       
     	        int maxYDifferences = maxYDifference; // Adjust this value as needed

     	        // Check if the elements are beside each other
     	        boolean areBesideEachOther = Math.abs(element2Y - element1Y) <= maxYDifference && element2X > element1X;

     	        if (areBesideEachOther) {
     	        	nlpResponseModel.setStatus(CommonConstants.pass);
        			nlpResponseModel.setMessage("Static and Reference elements are beside each other");
     	        } else {
     	        	nlpResponseModel.setStatus(CommonConstants.fail);
        			nlpResponseModel.setMessage("Static and Reference elements are not beside each other.");
     	        }
          }
          catch(Exception e) {
        	  nlpResponseModel.setStatus(CommonConstants.fail);
  			nlpResponseModel.setMessage("Fail to verify due to exception" + e);
          }
    

       // nlpResponseModel.getAttributes().put("result", result);
          return nlpResponseModel;
      }
  } 