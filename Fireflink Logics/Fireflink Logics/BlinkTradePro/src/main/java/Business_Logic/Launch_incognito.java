package Business_Logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
@Component("LIC17078_PJT1001_PE_NLP80666e9f-1acb-4e8e-9e31-bdd9d16d4e27")
public class Launch_incognito implements Nlp {

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
                   
      	  try {
      		ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            //ChromeDriver driver = (ChromeDriver)nlpRequestModel.getWebDriver();
            ChromeDriver driver1=new ChromeDriver(options);
            driver1.get("");
      	  }
      	  
   		catch(Exception e)
   		{
   		    nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Not a valid Number " +e);
   		}
      	//nlpResponseModel.getAttributes().put("value", result);
          return nlpResponseModel;
      }
 }


