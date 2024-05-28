package Business_logics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;


import org.springframework.stereotype.Component;

@Component("LIC14952_PJT1005_PE_NLPb4c14778-f671-4c2c-a770-548a3b44a544")
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
        if (nlpRequestModel.getAndroidDriver() != null) {
            driver = nlpRequestModel.getAndroidDriver();
        } else if (nlpRequestModel.getIosDriver() != null) {
            driver = nlpRequestModel.getIosDriver();          
        }
        click(driver, xpath);
        return nlpResponseModel;
    }

    public void click(RemoteWebDriver driver, String xpath) {
         WebElement element = driver.findElement(By.xpath(xpath));
        element.click();
    }
}
