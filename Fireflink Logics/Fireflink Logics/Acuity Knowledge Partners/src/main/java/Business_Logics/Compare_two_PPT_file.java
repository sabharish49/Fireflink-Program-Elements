
package Business_Logics;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import org.springframework.stereotype.Component;

@Component("LIC19307_PJT1001_PE_NLPe5d99a4f-23d7-47cd-8ca2-b2c6de49ab05")
public class Compare_two_PPT_file implements Nlp {
	@InputParams({ @InputParam(name = "PPT1", type = "java.lang.String"),
			@InputParam(name = "PPT2", type = "java.lang.String") })
	@ReturnType(name = "string3", type = "java.lang.String")

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
		String PPT1 = (String) attributes.get("PPT1");
		String PPT2 = (String) attributes.get("PPT2");
		 String notMatched=null;
		try {
			FileInputStream fis1 = new FileInputStream(PPT1);
            FileInputStream fis2 = new FileInputStream(PPT2);           
            XMLSlideShow ppt1 = new XMLSlideShow(fis1);
            XMLSlideShow ppt2 = new XMLSlideShow(fis2);           
            List<XSLFSlide> slides1 = ppt1.getSlides();
            List<XSLFSlide> slides2 = ppt2.getSlides();      
            int numOfSlides = Math.min(slides1.size(), slides2.size());       
            for (int i = 0; i < numOfSlides; i++) {
                XSLFSlide slide1 = slides1.get(i);
                XSLFSlide slide2 = slides2.get(i);      
                System.out.println("Comparing slide " + (i + 1) + ":");        
                String contentSlide1 = getContent(slide1);
                String contentSlide2 = getContent(slide2);          
                if (contentSlide1.equals(contentSlide2)) { 	
                    String matched=("Content of slide " + (i + 1) + " is identical.");
                    nlpResponseModel.setMessage("Content of slide " + (i + 1) + " is identical.");
        			nlpResponseModel.setStatus(CommonConstants.pass);
                } else {
                    notMatched=("Content of slide " + (i + 1) + " is different.");  
                    nlpResponseModel.setMessage("Content of slide " + (i + 1) + " is different.");
        			nlpResponseModel.setStatus(CommonConstants.fail);
                    break;
                }
            }
		} catch (Exception e) {
			
		}
		String string3 = notMatched;
		//nlpResponseModel.getAttributes().put("notMatched",string3 );
		return nlpResponseModel;
	}
   
    private static String getContent(XSLFSlide slide) {
        StringBuilder content = new StringBuilder();       
        for (org.apache.poi.xslf.usermodel.XSLFShape shape : slide.getShapes()) {      	
            if (shape instanceof org.apache.poi.xslf.usermodel.XSLFTextShape) {
                org.apache.poi.xslf.usermodel.XSLFTextShape textShape = (org.apache.poi.xslf.usermodel.XSLFTextShape) shape;
                content.append(textShape.getText());
            }
        }
        return content.toString();
    }
}