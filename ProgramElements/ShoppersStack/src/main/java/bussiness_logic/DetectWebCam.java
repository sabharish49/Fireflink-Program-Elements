package bussiness_logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.github.sarxos.webcam.Webcam;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.ReturnType;


import org.springframework.stereotype.Component;

@Component("LIC14952_PJT1001_PE_NLPedc99f84-4af1-444a-ac8d-ebf7dd9d276a")
public class DetectWebCam implements Nlp {
		
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
		          // Your program element business logic goes here ...
		          Boolean result=true;
		          try {
			        	Webcam webcam = Webcam.getDefault();
			            if (webcam != null) {
			                System.out.println("Webcam: " + webcam.getName());
			               result=true;
		        		   nlpResponseModel.setStatus(CommonConstants.pass);
		        		   nlpResponseModel.setMessage("successfully detected");
			        } 
			        }
			            catch (Exception e) {
			        	
			        	result=false;
						nlpResponseModel.setStatus(CommonConstants.fail);
			 		    nlpResponseModel.setMessage("failed to detect " +e);
			            
			        }
		       
		          nlpResponseModel.getAttributes().put("result", result);
		          return nlpResponseModel;
		      }
}
			        
	

