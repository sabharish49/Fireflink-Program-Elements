package Business_LogicForAPI;

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
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;


@Component("LIC1710_PJT1014_PE_NLPf29b6218-e188-42eb-9d83-c63b7de624b0")
public class RemoveAllProductsFromWishlist implements Nlp {
	
	 @InputParams({@InputParam(name = "Response Body", type = "java.lang.String")})
    @ReturnType(name = "ID", type = "java.util.ArrayList")
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
		List<String> l1=new ArrayList<>();

		NlpResponseModel nlpResponseModel = new NlpResponseModel();
		Map<String, Object> attributes = nlpRequestModel.getAttributes();
		
		String response = (String) attributes.get("Response Body");
		String jsonData= response.replaceAll("\\\\", "");
		
		
		// Your program element business logic goes here ...
		
		try {
			JSONObject jsonObject = new JSONObject(jsonData);
	        JSONArray placement = jsonObject.getJSONArray("results");
	       
	        for (int i = 0; i < placement.length(); i++) {
	        	
	            JSONObject placements = placement.getJSONObject(i);
	            int id = placements.getInt("id_product");
	          
	                	l1.add(""+id);
	                 
	            }		              

			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("ProductIds is fetched Successfully");
		      
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to fetch productIds");
		}

		nlpResponseModel.getAttributes().put("ID", l1);
		return nlpResponseModel;
	}
	
}  