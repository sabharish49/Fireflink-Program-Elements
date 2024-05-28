package Business_LogicForWeb;

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


@Component("LIC1710_PJT1014_PE_NLP9d218252-0de8-4de9-8af0-e37c90c0200c")
public class GetSumOfListPrice implements Nlp {
	@InputParams({ @InputParam(name = "listofText1", type = "java.util.ArrayList")})
	@ReturnType(name = "integerValue", type = "java.lang.Integer")

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
		double doubleValue1		=	0.0;
		double doubleValue2		=	0.0;
		Integer integerValue	=	0;
		try 
		{
			@SuppressWarnings("unchecked")
			ArrayList<String> 	listofText1 = (ArrayList<String>) attributes.get("listofText1");
			ArrayList<Double>	listofText2 = new ArrayList<>();
			for(int i = 0; i<= listofText1.size()-1; i++)
			{
				listofText2.add(i, Double.parseDouble(listofText1.get(i).replaceAll("[^0-9]", "")));
			}
			for(int i = 0; i<= listofText1.size()-1; i++)
			{
				doubleValue1	=	listofText2.get(i)/100;
				doubleValue2	=	doubleValue2+doubleValue1;
			}
			int intValue	=	(int) Math.round(doubleValue2);
			integerValue	=	intValue;
			nlpResponseModel.setMessage(" The Sum of all the Prices in the List is :  " + integerValue);
			nlpResponseModel.setStatus(CommonConstants.pass);
		} 
		catch (Exception e)
		{

			nlpResponseModel.setMessage(" Something Went Wrong " + e);
			nlpResponseModel.setStatus(CommonConstants.fail);
		}
		nlpResponseModel.getAttributes().put("integerValue", integerValue);
		return nlpResponseModel;
	}
}
