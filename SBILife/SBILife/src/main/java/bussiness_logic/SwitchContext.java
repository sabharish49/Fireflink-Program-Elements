package bussiness_logic;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;



import io.appium.java_client.android.AndroidDriver;


public class SwitchContext implements Nlp {

    @InputParams({
        @InputParam(name = "targetContext", type = "java.lang.String") 
    })
    @ReturnType(name = "resultMessage", type = "java.lang.String") 
    @Override
    public List<String> getTestParameters() throws NlpException {
        List<String> params = new ArrayList<>();
        params.add("targetContext");
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

        try {
           
            Map<String, Object> attributes = nlpRequestModel.getAttributes();
            String targetContext = (String) attributes.get("targetContext");

           
            AndroidDriver driver = (AndroidDriver) nlpRequestModel.getAndroidDriver();

            
            Set<String> contextHandles = driver.getContextHandles();
            String resultMessage;

            
            if (contextHandles.contains(targetContext)) {
                driver.context(targetContext);
                resultMessage = "Successfully switched to context: " + targetContext;
                nlpResponseModel.setStatus(CommonConstants.pass);
            } else {
                resultMessage = "Context not found: " + targetContext + ". Available contexts: " + contextHandles;
                nlpResponseModel.setStatus(CommonConstants.fail);
            }

           
            nlpResponseModel.getAttributes().put("resultMessage", resultMessage);

        } catch (Exception e) {
        	nlpResponseModel.setStatus(CommonConstants.fail);
            nlpResponseModel.setMessage("An error occurred: " + e.getMessage());
        }

        return nlpResponseModel;
    }
}
