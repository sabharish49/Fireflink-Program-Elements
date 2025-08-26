package Business_Logics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

public class CustomizedCapabilities implements Nlp {
	@InputParams({
		@InputParam(name = "Desired Capability", type = "org.openqa.selenium.remote.DesiredCapabilities"),
		@InputParam(name = "Key", type = "java.lang.String"),
		@InputParam(name = "Value", type = "java.lang.String"),
		@InputParam(name = "Value Type", type = "java.lang.String")})
	@ReturnType(name = "cap", type = "org.openqa.selenium.remote.DesiredCapabilities")

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
		DesiredCapabilities cap = (DesiredCapabilities) attributes.get("Desired Capability");
		String key = (String) attributes.get("Key");
		String value = (String) attributes.get("Value");
		String valueType = (String) attributes.get("Value Type");


		try {
			DesiredCapabilities d = new DesiredCapabilities();
			switch (valueType) {
			case "String":{
				d.setCapability(key, value);
				break;
			}
			case "Integer":{
				int intValue = Integer.parseInt(value);
				d.setCapability(key, intValue);
				break;
			}
			case "Boolean":{
				boolean boolValue = Boolean.parseBoolean(value); 
				d.setCapability(key, boolValue);
				break;
			}

			default:
				System.out.println("Unknown type");
				break;
			}

			cap.merge(d);
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Capability is added in Desired Capability");
		}
		catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to set the capability"+e);
		}

		nlpResponseModel.setDesiredCapabilities(cap);
		nlpResponseModel.getAttributes().put("cap", cap);

		return nlpResponseModel;

	}


}