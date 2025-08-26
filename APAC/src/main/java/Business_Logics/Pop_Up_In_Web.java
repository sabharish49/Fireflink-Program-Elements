package Business_Logics;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

public class Pop_Up_In_Web implements Nlp {

    @ReturnType(name = "isDisplay", type = "java.lang.Boolean")
    @Override
    public List<String> getTestParameters() throws NlpException {
        return new ArrayList<>();
    }

    @Override
    public StringBuilder getTestCode() throws NlpException {
        return new StringBuilder();
    }

    @Override
    public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {        
        NlpResponseModel nlpResponseModel = new NlpResponseModel();
        WebDriver driver = nlpRequestModel.getWebDriver();
        Map<String, Object> attributes = nlpRequestModel.getAttributes();
        boolean isAlertDisplayed = false;

        try {
            isAlertDisplayed = isAlertPresent(driver, 20); // Default timeout 10 seconds
            nlpResponseModel.setStatus(CommonConstants.pass);
            nlpResponseModel.setMessage(isAlertDisplayed ? 
                "Alert popup is present on the current screen" : 
                "Popup is not present on the current screen");
        } catch (Exception e) {
            nlpResponseModel.setStatus(CommonConstants.pass);
            nlpResponseModel.setMessage("Popup is not present on the current screen");
            e.printStackTrace(); // Log error for debugging
        }

        nlpResponseModel.getAttributes().put("isDisplay", isAlertDisplayed);
        return nlpResponseModel;
    }

    public static boolean isAlertPresent(WebDriver driver, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
