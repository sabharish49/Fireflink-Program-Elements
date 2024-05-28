package Mobileweb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;

import io.appium.java_client.android.AndroidDriver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("LIC1710_PJT1014_PE_NLP993b729b-b49c-4c53-98c6-e7b360b7224b")
public class MWEB_SwitchFromWebViewToNativeView implements Nlp {

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
			AndroidDriver driver = (AndroidDriver) nlpRequestModel.getAndroidDriver();
			Set<String> allContextHandles = driver.getContextHandles();
//            log.info("Available contexts are {}", allContextHandles);
            String nativeContext = allContextHandles.stream().filter(context -> context.contains("NATIVE"))
                    .map(Object::toString).findAny().orElse(null);
            if (Objects.nonNull(nativeContext)) {
                driver.context(nativeContext);
                nlpResponseModel.setMessage("Successfully switched to NATIVE_APP view");
                nlpResponseModel.setStatus(CommonConstants.pass);
            } else {
                nlpResponseModel.setMessage("Context NATIVE_APP is not available to switch");
                nlpResponseModel.setStatus(CommonConstants.fail);
            }
		} catch (Exception e) {
//			log.error("Exception is: ", e);
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to switch to native view");
		}
		return nlpResponseModel;
	}
}