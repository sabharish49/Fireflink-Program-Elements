package Business_Logics;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;

@Component("LIC19046_PJT1001_PE_NLP2b8f37ea-15bb-4a2f-9746-a9da2995a7c6")
public class PDF_Generator implements Nlp {
	  // @InputParams({@InputParam(name = "appPackage", type = "java.lang.String")})
	    @ReturnType(name = "isDisplay", type = "java.lang.Boolean")
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
	          AndroidDriver driver = nlpRequestModel.getAndroidDriver();
	          Map<String, Object> attributes = nlpRequestModel.getAttributes();   
	         	String string = generateFixedString(4);
	            String filePath = "C:\\Users\\User\\Downloads\\"+string+".pdf";
	            Boolean created=true;
	            try {
	                PDDocument document = new PDDocument();
	                document.save(filePath);
	                document.close();
	                nlpResponseModel.setStatus(CommonConstants.pass);
		        	nlpResponseModel.setMessage("Successfully closed all apps");
		        	created=true;
	            } catch (IOException e) {
	            	nlpResponseModel.setStatus(CommonConstants.fail);
		        	 nlpResponseModel.setMessage("Failed to close all apps"+e); 
		        	 created=false;
	            }      
	            nlpResponseModel.getAttributes().put("isDisplay", created);
	          return nlpResponseModel;
	      }
	      public static String generateFixedString(int length) {
	      	String Alphabets="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz";
	      	   StringBuilder s = new StringBuilder(length);
	      	   for (int i=0; i<length; i++) {
	      		   int ch = (int)(Alphabets.length() * Math.random());
	      		   s.append(Alphabets.charAt(ch));
	      		  }
	      		    return s.toString();
	      }      

	      }