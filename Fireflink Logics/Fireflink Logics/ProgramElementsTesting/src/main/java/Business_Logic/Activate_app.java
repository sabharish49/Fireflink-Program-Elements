package Business_Logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

@Component("LIC12620_PJT1003_PE_NLP47e9cde7-f110-4d55-bc13-5b113fd2e85e")
public class Activate_app implements Nlp {

    @InputParams({ @InputParam(name = "xpath", type = "java.lang.String") })
    @Override
    public List<String> getTestParameters() throws NlpException {
        List<String> params = new ArrayList<>();
        params.add("xpath");
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
        String xpath = (String) attributes.get("xpath");
        RemoteWebDriver driver = null;
        try {
            if (nlpRequestModel.getAndroidDriver() != null) {
                driver = nlpRequestModel.getAndroidDriver();
            } else if (nlpRequestModel.getIosDriver() != null) {
                driver = nlpRequestModel.getIosDriver();
            }  
            click(driver, xpath);
    		nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Successfully clicked on element");
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("failed to click on element"+e);
		}

        return nlpResponseModel;
    }

    public void click(RemoteWebDriver driver, String xpath) {
         WebElement element = driver.findElement(By.xpath(xpath));
        element.click();
    }
}
