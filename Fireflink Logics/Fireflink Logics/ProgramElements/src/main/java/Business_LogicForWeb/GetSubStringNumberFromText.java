package Business_LogicForWeb;

import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;  
import com.tyss.optimize.nlp.util.annotation.InputParam; 
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import com.tyss.optimize.common.util.CommonConstants;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Component;

@Component("LIC1710_PJT1014_PE_NLP6c885c7c-3248-462e-8259-b3934f372449")
public class GetSubStringNumberFromText  implements Nlp {
	@InputParams({	@InputParam(name = "setValue", type = "java.lang.String")})
	@ReturnType(name = "getNum", type = "java.lang.Integer")

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
		String setValue = (String) attributes.get("setValue");
		Integer getNum = 0;
		try 
		{
			setValue = setValue.replaceAll("[^0-9?]", "");
			getNum = Integer.parseInt(setValue.substring(0, setValue.length()-2));
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Fetched value is: "+getNum);
		} 
		catch (Exception e) 
		{
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to Fetch");
		}
		nlpResponseModel.getAttributes().put("getNum", getNum);
		return nlpResponseModel;
	}
} 
