package bussiness_logic;

	import java.awt.Desktop;
	import java.io.IOException;
	import java.net.URI;
	import java.net.URISyntaxException;
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

@Component("LIC14952_PJT1001_PE_NLP78a4a1f4-dfd6-4105-ba46-2a917d2707b3")
public class launchDefaultBrowser implements Nlp {
		
		    @InputParams({@InputParam(name = "url", type = "java.lang.String")})
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
		          String url = (String) attributes.get("url");
		          

		          // Your program element business logic goes here ...
		          boolean result=true;
		        
		          try {
		              
		              URI uri = new URI(url);

		              
		              if (Desktop.isDesktopSupported()) {
		                  
		                  Desktop desktop = Desktop.getDesktop();

		                  
		                  desktop.browse(uri);
		                  nlpResponseModel.setStatus(CommonConstants.pass);
		                  nlpResponseModel.setMessage("launched successfull");
		              } else {
		            	  nlpResponseModel.setStatus(CommonConstants.fail);
		                  nlpResponseModel.setMessage("failed to launch");
		              }
		          } catch (IOException | URISyntaxException e) {
		              
		        	  nlpResponseModel.setStatus(CommonConstants.fail);
	                  nlpResponseModel.setMessage("failed to launch");
		          }
		          nlpResponseModel.getAttributes().put("result", result);
				  return nlpResponseModel;
	}
	}

