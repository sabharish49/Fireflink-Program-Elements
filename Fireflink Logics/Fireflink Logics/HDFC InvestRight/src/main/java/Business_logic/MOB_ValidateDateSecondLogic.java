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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;


@Component("LIC14172_PJT1001_PE_NLP8cf2fad9-4b91-42b7-9d6a-5fa32a5c9c69")
public class MOB_ValidateDateSecondLogic implements Nlp {
    @InputParams({@InputParam(name = "DateTime", type = "java.lang.String"), @InputParam(name = "DateFormat", type = "java.lang.String")})
	@ReturnType(name = "result", type = "java.lang.Boolean")

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
          String DateTime = (String) attributes.get("DateTime");
          String DateFormat = (String) attributes.get("DateFormat");
          Boolean result=true;

          // Your program element business logic goes here ...
          try {
		        // Create a SimpleDateFormat object with the expected format
		        SimpleDateFormat sdf = new SimpleDateFormat(DateFormat);

		        try {
		            // Parse the current date string into a Date object
		            java.util.Date date = sdf.parse(DateTime);

		            // Format the Date object back to a string
		            String formattedDate = sdf.format(date);

		            // Verify if the formatted date and time matches the expected format
		            if (formattedDate.equals(DateTime)) {
		            	result=true;
		            	nlpResponseModel.setStatus(CommonConstants.pass);
		            	nlpResponseModel.setMessage("Date and time format is valid"); 
		            } 
		            else {
		            	result=false;
		            	nlpResponseModel.setStatus(CommonConstants.fail);
		            	nlpResponseModel.setMessage("Date and time format is Invalid"); 
		            }
		        } 
		        catch (ParseException e) {
		        	nlpResponseModel.setStatus(CommonConstants.fail);
	            	nlpResponseModel.setMessage("Date and time format is Invalid"); 
		        }
		    
          }
          
          catch (Exception e) {
        	  result=false;
        	 nlpResponseModel.setStatus(CommonConstants.fail);
         	 nlpResponseModel.setMessage("Failed to validate the Date and Time Format due to exception"+" "+e);
		}
          
          

         // String string3 = "Return Value";
          nlpResponseModel.getAttributes().put("result", result);
          return nlpResponseModel;
      }
  } 