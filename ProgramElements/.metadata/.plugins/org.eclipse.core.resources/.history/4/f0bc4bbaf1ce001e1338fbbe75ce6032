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


    
@Component("LIC14952_PJT1001_PE_NLPe8899a02-916f-4b2c-8dce-a7bf78240a17")
public class GetSizeofUserInputList implements Nlp {
	
	    @InputParams({@InputParam(name = "list", type = "java.util.List")})
	    @ReturnType(name = "list", type = "java.lang.Integer")

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
	          List list = (List) attributes.get("list");
	          //String string2 = (String) attributes.get("string2");

	          // Your program element business logic goes here ...
	          int size=0;
	          try {
	        	  size=list.size();
	        	  nlpResponseModel.setStatus(CommonConstants.pass);
		          nlpResponseModel.setMessage("size is obtained");
	          }
	          catch(Exception e) {
	        	  nlpResponseModel.setStatus(CommonConstants.fail);
		          nlpResponseModel.setMessage("failed");
	          }
	        //  String string3 = "Return Value";
	          nlpResponseModel.getAttributes().put("size", size);
	          return nlpResponseModel;
	      }
	  } 








