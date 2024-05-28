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

import ch.qos.logback.classic.Logger;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Slf4j

@Component("LIC1710_PJT1014_PE_NLP47d92e12-20d8-4a30-934f-06f432d4b2d5")
public class SelectProductOfAvailableSize implements Nlp {
	
	 @InputParams({@InputParam(name = "Response Body", type = "java.lang.String"), @InputParam(name = "NoOfProducts", type = "java.lang.Integer")})
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
		//String string2 = (String) attributes.get("string2");
		//System.out.println(jsonData);
		
		// Your program element business logic goes here ...
		 // log.info("Start of Try Block");
		try {
			 JSONObject jsonObject = new JSONObject(jsonData);
		        JSONArray placement = jsonObject.getJSONObject("results")
		        		.getJSONObject("sort_desktop")
		                .getJSONArray("recommendedProducts");
		        Integer noOfProduct=(Integer) attributes.get("NoOfProducts");      
		        int num=0;
		       
		        for (int i = 0; i < placement.length(); i++) {
		        	
		            JSONObject placements = placement.getJSONObject(i);
		            
		            int quantity = placements.getJSONObject("_source").getInt("Quantity");
		            int id = placements.getInt("key");
		           
		            if (quantity > 10) {
		            	
		               if(num<noOfProduct) {
		                	l1.add(""+id);
		                //	System.out.println(id);
		                	num++;
		               } 
		            
		        }
		            
		        }
		        System.out.println(l1);
		        

			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("ProductId is fetched Successfully");
		      
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to fetch productId");
		}

		//String string3 = "Return Value";
		nlpResponseModel.getAttributes().put("ID", l1);
		return nlpResponseModel;
	}
}