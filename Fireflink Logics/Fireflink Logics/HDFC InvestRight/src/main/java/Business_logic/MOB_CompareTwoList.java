package Business_logic;


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

@Component("LIC14172_PJT1001_PE_NLP83a8df1d-06af-41a4-b9ef-357d5e54f83e")
public class MOB_CompareTwoList implements Nlp {
	@InputParams({@InputParam(name = "List Of Stocks", type = "java.util.List"),
		@InputParam(name = "ExpectedString", type = "java.lang.String")})
	

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
		List<String> ListOfStocks = (List) attributes.get("List Of Stocks");
		String ExpectedString = (String) attributes.get("ExpectedString");


		try {
			int listsize=ListOfStocks.size();
			int add=0;

			for (int i=0; i<ListOfStocks.size(); i++) {
				String value=  ListOfStocks.get(i);
				if(value.contains(ExpectedString)) {

					add++;
				}
				
			}if(listsize==add) {
			
			nlpResponseModel.setMessage("Succesfully compared the stock "+listsize+" with the expectedString "+add);
			nlpResponseModel.setStatus(CommonConstants.pass);
			}else {
				nlpResponseModel.setMessage("Failed to compare the stock "+listsize+" with the expectedString"+add);
				nlpResponseModel.setStatus(CommonConstants.fail);
			}


		}catch (Exception e) {
			nlpResponseModel.setMessage("Failed to compare the stock with the expectedString  "+e);
			nlpResponseModel.setStatus(CommonConstants.fail);
		}


		return nlpResponseModel;
	}
} 
