package Business_LogicForAndroid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import org.springframework.stereotype.Component;

@Component("LIC1710_PJT1014_PE_NLP3ed9b424-cae1-4060-b063-7a9a69332d1f")
public class Mob_SelectValueFromList implements Nlp {
	@InputParams({ @InputParam(name = "SetList", type = "java.util.ArrayList"),
				   @InputParam(name = "SetIndex", type = "java.lang.Integer")})
	@ReturnType(name = "GetValue", type = "java.lang.String")

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
		String GetValue = "";
		try 
		{
			@SuppressWarnings("unchecked")
			ArrayList<String> SetList = (ArrayList<String>) attributes.get("SetList");
			Integer SetIndex = (Integer) attributes.get("SetIndex");
			GetValue = SetList.get(SetIndex);
			nlpResponseModel.setMessage(" Selected Value is :  " + GetValue);
			nlpResponseModel.setStatus(CommonConstants.pass);
		} 
		catch (Exception e)
		{

			nlpResponseModel.setMessage(" Something Went Wrong " + e);
			nlpResponseModel.setStatus(CommonConstants.fail);
		}
		nlpResponseModel.getAttributes().put("GetValue", GetValue);
		return nlpResponseModel;
	}
}
