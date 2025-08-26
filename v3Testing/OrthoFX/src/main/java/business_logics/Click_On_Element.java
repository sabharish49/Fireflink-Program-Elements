package business_logics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;




public class Click_On_Element implements Nlp {
	@InputParams({@InputParam(name = "element", type ="org.openqa.selenium.WebElement")})

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
        WebElement string1 = (WebElement) attributes.get("element");
       
      try {
          // Your program element business logic goes here ...

	     string1.click();
       nlpResponseModel.setStatus(CommonConstants.pass);
       nlpResponseModel.setMessage("Successfully Clicked on Element");

}
 catch (Exception e){
	nlpResponseModel.setStatus(CommonConstants.fail);
	nlpResponseModel.setMessage("Failed To Click on Element");
	
}
        

        return nlpResponseModel;
    }
} 


