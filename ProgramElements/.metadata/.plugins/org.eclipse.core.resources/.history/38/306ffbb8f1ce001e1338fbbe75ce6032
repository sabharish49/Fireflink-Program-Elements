package bussiness_logic;

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

@Component("LIC14952_PJT1001_PE_NLP9fab1f47-9689-46c3-bae0-8790fcb1e24b")
public class ReplaceAllvowelsBySpecialCharacters implements Nlp {
		
		    @InputParams({@InputParam(name = "string", type = "java.lang.String")})
		    @ReturnType(name = "result", type = "java.lang.String")

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
		          String string = (String) attributes.get("string");
		          

		          // Your program element business logic goes here ...
		          StringBuilder result = new StringBuilder();
		        
		          try {
		        	  char[] charArray = string.toCharArray();
		              
		              for (int i=0;i<charArray.length;i++) {
		              	 char vowel=charArray[i];
		              	if (vowel == 'a' || vowel == 'e' || vowel == 'i' || vowel == 'o' || vowel == 'u')
		              	{
		              		vowel='$';
		              	}
		              	result.append(vowel);
		              }
		              
		              
		                  nlpResponseModel.setStatus(CommonConstants.pass);
		                  nlpResponseModel.setMessage("replaced successfull");
		              
		              }
		           catch (Exception e) {
		              
		        	  nlpResponseModel.setStatus(CommonConstants.fail);
	                  nlpResponseModel.setMessage("failed to replace");
		          }
		          nlpResponseModel.getAttributes().put("result", result);
				  return nlpResponseModel;
	}
	}
