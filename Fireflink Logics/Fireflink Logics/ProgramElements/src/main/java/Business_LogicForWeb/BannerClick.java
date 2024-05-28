package Business_LogicForWeb;




import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;


@Component("LIC1710_PJT1014_PE_NLPef6dc84f-ab61-42bb-acda-967892368b59")
public class BannerClick implements Nlp {
    @InputParams({@InputParam(name = "bannerxpath", type = "java.lang.String"), @InputParam(name = "clickablexpath", type = "java.lang.String")})
    @ReturnType(name = "bannerCount", type = "java.lang.Integer")

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
          String bannerxpath = (String) attributes.get("bannerxpath");
          String clickablexpath = (String) attributes.get("clickablexpath");
          int size=0;
          try {
			
        	  WebDriver driver=(WebDriver)nlpRequestModel.getDriver().getSpecificIDriver();
        	  List<WebElement> list=driver.findElements(By.xpath(bannerxpath));
        	  WebElement clickablEelement=driver.findElement(By.xpath(clickablexpath));
        	  size=list.size();
        	  for(int i=1;i<=size;i++)
        	  {
        		  clickablEelement.click();
        	  }
        	  nlpResponseModel.setStatus(CommonConstants.pass);
        	  nlpResponseModel.setMessage("Banner loop completed and it clicked "+size+" times");
          	} 
          catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Banner loop Failed");
		}
          nlpResponseModel.getAttributes().put("bannerCount", size);
          return nlpResponseModel;
      }
  } 