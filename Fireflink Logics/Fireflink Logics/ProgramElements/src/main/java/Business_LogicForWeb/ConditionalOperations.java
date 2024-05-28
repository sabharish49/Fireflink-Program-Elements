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

@Component("LIC1710_PJT1014_PE_NLP0315f048-a9ac-4d75-9c1f-13dd938f4c07")
public class ConditionalOperations implements Nlp 
{
	@InputParams({	@InputParam(name = "Set Value 1",    type = "java.lang.Integer"), 
					@InputParam(name = "Set Value 2",    type = "java.lang.Integer"),
					@InputParam(name = "Set Conditional Operator", type = "java.lang.String")})
					@ReturnType(name = "condition",      type = "java.lang.Boolean")
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
		Boolean condition = false;
		try 
		{
			Integer setNum1 = (Integer) attributes.get("Set Value 1");
			Integer setNum2 = (Integer) attributes.get("Set Value 2");
			String setConditionalOperator = (String) attributes.get("Set Conditional Operator");
			
			if(setConditionalOperator.equals("<"))
			{
				if(setNum1 < setNum2)
				{
					condition=true;
					nlpResponseModel.setStatus(CommonConstants.pass);
					nlpResponseModel.setMessage("First Value is Less Than Second Value");
				}
				else
				{
					nlpResponseModel.setStatus(CommonConstants.pass);
					nlpResponseModel.setMessage("First Value is Not Less Than Second Value");
				}

			}
			
			else if(setConditionalOperator.equals(">"))
			{
				if(setNum1 > setNum2)
				{
					condition=true;
					nlpResponseModel.setStatus(CommonConstants.pass);
					nlpResponseModel.setMessage("First Value is Greater Than Second Value");
				}
				else
				{
					nlpResponseModel.setStatus(CommonConstants.pass);
					nlpResponseModel.setMessage("First Value is Not Greater Than Second Value");
				}

			}
			
			else if(setConditionalOperator.equals("<="))
			{
				if(setNum1 <= setNum2)
				{
					condition=true;
					nlpResponseModel.setStatus(CommonConstants.pass);
					nlpResponseModel.setMessage("First Value is Less Than OR Equal To Second Value");
				}
				else
				{
					nlpResponseModel.setStatus(CommonConstants.pass);
					nlpResponseModel.setMessage("First Value is Not Less Than OR Equal To Second Value");
				}

			}
			
			else if(setConditionalOperator.equals(">="))
			{
				if(setNum1 >= setNum2)
				{
					condition=true;
					nlpResponseModel.setStatus(CommonConstants.pass);
					nlpResponseModel.setMessage("First Value is Greater Than OR Equal To Second Value");
				}
				else
				{
					nlpResponseModel.setStatus(CommonConstants.pass);
					nlpResponseModel.setMessage("First Value is Not Greater Than OR Equal To Second Value");
				}

			}
			
			else if(setConditionalOperator.equals("=="))
			{
				if(setNum1 == setNum2)
				{
					condition=true;
					nlpResponseModel.setStatus(CommonConstants.pass);
					nlpResponseModel.setMessage("First Value is Equal To Equal To Second Value");
				}
				else
				{
					nlpResponseModel.setStatus(CommonConstants.pass);
					nlpResponseModel.setMessage("First Value is Not Equal To Equal To Second Value");
				}

			}
			
			else if(setConditionalOperator.equals("!="))
			{
				if(setNum1 != setNum2)
				{
					condition=true;
					nlpResponseModel.setStatus(CommonConstants.pass);
					nlpResponseModel.setMessage("First Value is Not Equal To Second Value");
				}
				else
				{
					nlpResponseModel.setStatus(CommonConstants.pass);
					nlpResponseModel.setMessage("First Value is Equal To Second Value");
				}

			}
			else
			{
				
			}
			
		} 
		catch (Exception e) 
		{
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Something went worng with NLP");
		}
		nlpResponseModel.getAttributes().put("condition", condition);
		return nlpResponseModel;
	}
} 