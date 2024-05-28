package Business_LogicForWeb;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;


@Component("LIC1710_PJT1014_PE_NLP048f6825-d1f5-41c5-b5aa-80696925d800")
public class WebPageZoomChange implements Nlp {
    @InputParams({@InputParam(name = "Percentage", type = "java.lang.String")})

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
         
          String percentage = (String) attributes.get("Percentage");
          try 
          {
        	  WebDriver driver= (WebDriver) nlpRequestModel.getWebDriver();
        	  double size=(double) Integer.parseInt(percentage);
              double toDecimal=size/100;
        	  JavascriptExecutor executor = (JavascriptExecutor)driver;
              executor.executeScript("document.body.style.zoom = '"+toDecimal+"'");
              nlpResponseModel.setMessage("Zoom Size Set to: "+percentage+ " %");
  	      	  nlpResponseModel.setStatus(CommonConstants.pass);
          }
          catch(Exception e) 
          {
        	  nlpResponseModel.setMessage("Failed to Zoom Because  "+e);
    	      nlpResponseModel.setStatus(CommonConstants.fail);
          }
          return nlpResponseModel;
      }
  } 
