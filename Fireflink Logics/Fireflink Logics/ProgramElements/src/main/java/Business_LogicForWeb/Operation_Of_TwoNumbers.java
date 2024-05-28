package Business_LogicForWeb;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;



@Component("LIC1710_PJT1014_PE_NLPbeb1e090-fe53-4882-af78-c82d9170a0e9")
public class Operation_Of_TwoNumbers implements Nlp 
{
	@InputParams({	@InputParam(name = "setNum_1",    type = "java.lang.Integer"), 
					@InputParam(name = "setNum_2",    type = "java.lang.Integer"),
					@InputParam(name = "setOperator", type = "java.lang.String")})
					@ReturnType(name = "getNum",      type = "java.lang.Integer")
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
	public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException 
	{
		NlpResponseModel nlpResponseModel = new NlpResponseModel();
		Map<String, Object> attributes = nlpRequestModel.getAttributes();
		Integer setNum_1 = (Integer) attributes.get("setNum_1");
		Integer setNum_2 = (Integer) attributes.get("setNum_2");
		String setOperator = (String) attributes.get("setOperator");
		Integer getNum = 0;
		try 
		{
			if(setOperator.equals("+"))
			{
				getNum=setNum_1+setNum_2;
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Addition of the given Number is : "+getNum);
			}
			else if(setOperator.equals("-"))
			{
				getNum=setNum_1-setNum_2;
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Subtraction of the given Number is : "+getNum);
			}
			else 
			{
				nlpResponseModel.setStatus(CommonConstants.fail);
				nlpResponseModel.setMessage("Operation of given Number is not done ");
			}
		} 
		catch (Exception e) 
		{
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Operation of given Number is not done ");
		}
		nlpResponseModel.getAttributes().put("getNum", getNum);
		return nlpResponseModel;
	}
} 