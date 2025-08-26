package bussiness_logic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;



import org.springframework.stereotype.Component;

@Component("LIC14952_PJT1001_PE_NLP37173789-12f6-49cf-9016-2c849b829ea2")
public class TextInQr implements Nlp {

    @InputParams({@InputParam(name = "qrCodePath", type = "java.lang.String")})
    @ReturnType(name = "text", type = "java.lang.String")

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
          String qrCodePath = (String) attributes.get("qrCodePath");
          //String string2 = (String) attributes.get("string2");

          // Your program element business logic goes here ...
          String text="";
          try {
        	  
        	  BufferedImage image = ImageIO.read(new File(qrCodePath));

	            // Create a BinaryBitmap from the image
	            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));

	            // Initialize the QRCodeReader
	            QRCodeReader reader = new QRCodeReader();

	            // Read the QR code
	            Result result = reader.decode(binaryBitmap);

	            // Print the text from the QR code
	            text= result.getText();
        	  
        	  nlpResponseModel.setStatus(CommonConstants.pass);
        	  nlpResponseModel.setMessage("successfull");
			
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
      	  nlpResponseModel.setMessage("failed");
			// TODO: handle exception
		}
          //String string3 = "Return Value";
          nlpResponseModel.getAttributes().put("text", text);
          return nlpResponseModel;
      }
  } 
