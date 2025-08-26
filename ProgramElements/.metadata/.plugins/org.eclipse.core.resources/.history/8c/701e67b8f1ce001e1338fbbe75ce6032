package bussiness_logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.ReturnType;


	import org.springframework.stereotype.Component;

@Component("LIC14952_PJT1001_PE_NLP2632a802-e00e-4b25-ac9e-2bafe2047c67")
public class OpenIncognitoTab implements Nlp {
			
			   // @InputParams({@InputParam(name = "url", type = "java.lang.String"),
			   // @InputParam(name = "seconds", type = "java.lang.Integer")})
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
			          //String url = (String) attributes.get("url");
			         // Integer seconds = (Integer) attributes.get("seconds");

			          // Your program element business logic goes here ...
			          boolean result=true;
			          WebDriver driver=nlpRequestModel.getWebDriver();
			          try {
			        	 
			      
			        	  JavascriptExecutor js =(JavascriptExecutor) driver;
			  	        js.executeScript("window.open('', '_blank', 'noopener,noreferrer')");
			              
			              
			                  nlpResponseModel.setStatus(CommonConstants.pass);
			                  nlpResponseModel.setMessage("launched successfull");
			              
			              
			          } catch (Exception e) {
			              
			        	  nlpResponseModel.setStatus(CommonConstants.fail);
		                  nlpResponseModel.setMessage("failed to launch");
			          }
			          nlpResponseModel.getAttributes().put("result", result);
					  return nlpResponseModel;
		}
		}







