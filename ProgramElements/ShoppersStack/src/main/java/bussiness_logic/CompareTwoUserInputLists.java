package bussiness_logic;

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


	
	
@Component("LIC14952_PJT1001_PE_NLPe4bc0330-cb7e-4b25-9add-59ec73ee510c")
public class CompareTwoUserInputLists implements Nlp {
	
	    @InputParams({@InputParam(name = "List", type = "java.util.ArrayList")})
	    @ReturnType(name = "sum", type = "java.lang.Integer")

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
	          ArrayList<String> List = (ArrayList) attributes.get("List");
	         List<Integer> intList = new ArrayList<>();
	         Integer sum=0;
	          try {
	        	  
	              for (String str : List) {
	                  try {
	                      intList.add(Integer.parseInt(str));
	                      
	                  } 
	                  catch (NumberFormatException e) {
	                      
	                  }
	              }
                         for (int num : intList) {
	        	              sum += num;
	        	          }
                         nlpResponseModel.setStatus(CommonConstants.pass);
			  			 nlpResponseModel.setMessage("successfully cacalculated the sum of list");
	        	      }
	        
	          catch(Exception e) {
	        	  nlpResponseModel.setStatus(CommonConstants.fail);
	  			  nlpResponseModel.setMessage("failed to get sum of list"+e);
	          }

	         
	          nlpResponseModel.getAttributes().put("sum", sum);
	          return nlpResponseModel;
	      }

}
