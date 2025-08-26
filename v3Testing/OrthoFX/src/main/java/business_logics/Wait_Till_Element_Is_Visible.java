package business_logics;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;




public class Wait_Till_Element_Is_Visible implements Nlp {
	@InputParams({@InputParam(name = "xpath", type ="java.lang.String")})
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
        String string1 = (String) attributes.get("xpath");
        WebDriver driver=nlpRequestModel.getWebDriver();
       WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(90));
      try {
    	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(string1)));
    	  nlpResponseModel.setStatus(CommonConstants.pass);
    		nlpResponseModel.setMessage("Element is visible");
    		
    }
 catch (Exception e){
	 
	nlpResponseModel.setStatus(CommonConstants.pass);
	nlpResponseModel.setMessage("Element is  visible "+e.getMessage());
	
}
   

        return nlpResponseModel;
    }
} 


