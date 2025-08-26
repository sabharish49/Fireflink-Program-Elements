package bussiness_logic;

import java.io.File;
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

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;



	import org.springframework.stereotype.Component;

@Component("LIC14952_PJT1001_PE_NLP6ae77da8-b9e0-41ee-8cc5-7bf50dc9fb1b")
public class ExtractTextFromImage implements Nlp {
		
		    @InputParams({@InputParam(name = "imagePath", type = "java.lang.String"),
		    @InputParam(name = "tesseractPath", type = "java.lang.String")})
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
		          String imagePath = (String) attributes.get("imagePath");
		          String tesseractPath = (String) attributes.get("tesseractPath");
		          
		          // Your program element business logic goes here ...
		           
		             ITesseract tesseract = new Tesseract();
			        tesseract.setDatapath(tesseractPath);
			        String extractedText=null; ;
		           try {
		        	   
		        	    extractedText = tesseract.doOCR(new File(imagePath));
		   	        
		        		   nlpResponseModel.setStatus(CommonConstants.pass);
		        		   nlpResponseModel.setMessage("text extracted");
		           }  
		        		   
		        		   
				 
		           catch (Exception e) {
					
					nlpResponseModel.setStatus(CommonConstants.fail);
		 		   nlpResponseModel.setMessage("text not extracted" +e);
				}
		         // String string3 = "Return Value";
		          nlpResponseModel.getAttributes().put("result", extractedText);
		          return nlpResponseModel;
		      }
		  } 






		
		





