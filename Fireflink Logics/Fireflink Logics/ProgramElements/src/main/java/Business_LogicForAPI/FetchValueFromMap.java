package Business_LogicForAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.checkerframework.checker.units.qual.K;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v114.network.Network;
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@Component("LIC1710_PJT1014_PE_NLPd93bea7c-2a52-4fdf-82eb-bc24583d0f18")
public class FetchValueFromMap implements Nlp {
	@InputParams({ @InputParam(name = "Map", type = "java.util.HashMap"),
		 @InputParam(name = "Key", type = "java.lang.String")})
	@ReturnType(name = "value", type = "java.lang.String")

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
		HashMap map = (HashMap) attributes.get("Map");
		String key = (String) attributes.get("Key");
		String value = "";
		try {
			value = map.get(key).toString();
			nlpResponseModel.setMessage("Value is "+ value);
			nlpResponseModel.setStatus(CommonConstants.pass);
		} catch (Exception e) {
//			log.error("Exception is: {}", e);
			nlpResponseModel.setMessage("Failed to fetch value");
			nlpResponseModel.setStatus("FAIL");
		}
		nlpResponseModel.getAttributes().put("value", value);
		return nlpResponseModel;
	}
}