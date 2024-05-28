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


@Component("LIC1710_PJT1014_PE_NLPceb527fa-1ce1-4d9b-b3a8-b79209e4a3bd")
public class To_Fetch_ProductAttributeId implements Nlp {
	public String v="";
	
	@InputParams({@InputParam(name = "Response Body", type = "java.lang.String")})
	@ReturnType(name = "ID", type = "java.lang.String")

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
		
		String response = (String) attributes.get("Response Body");
		 String jsonData= response.replaceAll("\\\\", "");
		//String string2 = (String) attributes.get("string2");
		
		// Your program element business logic goes here ...
		try {
			JSONObject jsonObject = new JSONObject(jsonData);
	        JSONArray sizes = jsonObject.getJSONObject("results")
	        		.getJSONObject("productInfo")
	                .getJSONObject("body")
	                .getJSONObject("hits")
	                .getJSONArray("hits")
	                .getJSONObject(0)
	                .getJSONObject("_source")
	                .getJSONArray("Sizes");

	       
	        for (int i = 0; i < sizes.length(); i++) {
	            JSONObject size = sizes.getJSONObject(i);
	            int quantity = size.getInt("Quantity");
	            int id = size.getInt("ID");
	           
	            if (quantity > 0) {
	               v=""+id;
	                break;
	            }
	        }

			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("ID is fetched Successfully");
			//System.out.println(v);

		}
		catch(Exception e)
		{
			e.printStackTrace();
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("OUT OF STOCK");
		}

		//String string3 = "Return Value";
		nlpResponseModel.getAttributes().put("ID", v);
		return nlpResponseModel;
	}
	
	
}  
