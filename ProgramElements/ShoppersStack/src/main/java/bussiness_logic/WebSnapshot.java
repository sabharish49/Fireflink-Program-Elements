package bussiness_logic;
	
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

	import org.springframework.stereotype.Component;

@Component("LIC14952_PJT1001_PE_NLP435f6621-adb6-4537-82d7-8fe1b65827cf")
public class WebSnapshot implements Nlp {

		    @InputParams({@InputParam(name = "filePath", type = "java.lang.String")})
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
		          String filePath = (String) attributes.get("filePath");
		          
		          // Your program element business logic goes here ...
		          boolean result=true;
		          try {
		        	  Webcam webcam = Webcam.getDefault();

		  	        if (webcam != null) {
		  	            webcam.setViewSize(WebcamResolution.VGA.getSize());
		  	            webcam.open();

		  	            JFrame frame = new JFrame("Webcam Capture");
		  	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		  	            WebcamPanel panel = new WebcamPanel(webcam);          
		  	            frame.add(panel);
		  	            frame.pack();
		  	            frame.setVisible(true);

		  	           
		  	        BufferedImage image = webcam.getImage();
		      		ImageIO.write(image, "JPG", new File(filePath));
		        	  result=true;
		        	  nlpResponseModel.setStatus(CommonConstants.pass);
		        	  nlpResponseModel.setMessage("successfull");
		  	        }
		          }
				 catch (Exception e) {
					result=false;
					nlpResponseModel.setStatus(CommonConstants.fail);
		      	  nlpResponseModel.setMessage("failed"+e);
					// TODO: handle exception
				}
		          
		          nlpResponseModel.getAttributes().put("result", result);
		          return nlpResponseModel;
		          }
	}
		  
