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


import org.springframework.stereotype.Component;

@Component("LIC1710_PJT1014_PE_NLP510dce86-d6d9-42e5-bd0b-f4c3ba7bd481")
public class MathFunctions implements Nlp 
{
	@InputParams({	@InputParam(name = "setNum1",    type = "java.lang.Integer"), 
					@InputParam(name = "setNum2",    type = "java.lang.Integer"),
					@InputParam(name = "setMatOperator", type = "java.lang.String")})
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
		Integer getNum = 0;
		try 
		{
			Integer setNum1 = (Integer) attributes.get("setNum1");
			Integer setNum2 = (Integer) attributes.get("setNum2");
			String setMatOperator = (String) attributes.get("setMatOperator");
			
			if(setMatOperator.equals("+"))
			{
				getNum=setNum1+setNum2;
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Addition of the given Numbers is : "+getNum);
			}
			else if(setMatOperator.equals("-"))
			{
				getNum=setNum1-setNum2;
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Subtraction of the given Numbers is : "+getNum);
			}
			else if(setMatOperator.equals("*"))
			{
				getNum=setNum1*setNum2;
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Multiplication of the given Numbers is : "+getNum);
			}
			else if(setMatOperator.equals("/"))
			{
				getNum=setNum1/setNum2;
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Division of the given Numbers is : "+getNum);
			}
			else if(setMatOperator.equals("%"))
			{
				double doubleValue1	=	(double) setNum1;
				double doubleValue2	=	(double) setNum2;
				getNum	=	(int) Math.round((doubleValue2/doubleValue1)*100);
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Percentage of the given Numbers is : "+getNum);
			}
			else if(setMatOperator.equals("%Value"))
			{
				double doubleValue1	=	(double) setNum1;
				double doubleValue2	=	(double) setNum2;
				getNum	=	(int) Math.round((doubleValue1/100)*doubleValue2);
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("OutputValue for the given Percentage is : "+getNum);
			}
			else if(setMatOperator.equals("%Diff"))
			{
				double doubleValue1	=	(double) setNum1;
				double doubleValue2	=	(double) setNum2;
				getNum	=	(int) Math.round(((doubleValue1-doubleValue2)/doubleValue1)*100);
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Percentage Difference in b/w two numbres is : "+getNum);
			}
			else 
			{
				nlpResponseModel.setStatus(CommonConstants.fail);
				nlpResponseModel.setMessage("Operation of given Numbers is not done ");
			}
			
		} 
		catch (Exception e) 
		{
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Something went worng with NLP");
		}
		nlpResponseModel.getAttributes().put("getNum", getNum);
		return nlpResponseModel;
	}
} 