
package Business_Logics;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

@Component("LIC19307_PJT1001_PE_NLPfc36fb04-4d3d-4cdb-b030-11a05e6bdf7e")
public class Validate_Content_in_PPT implements Nlp {
	@InputParams({ @InputParam(name = "FilePath", type = "java.lang.String"),
			@InputParam(name = "ExpectedContent", type = "java.lang.String") })
	@ReturnType(name = "string3", type = "java.lang.Boolean")

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
		String FilePath = (String) attributes.get("FilePath");
		String ExpectedContent = (String) attributes.get("ExpectedContent");
		int slideCount=0;
		Boolean slide=true;
		try {
			FileInputStream fis = new FileInputStream(FilePath);
			XMLSlideShow ppt = new XMLSlideShow(fis);
			for (XSLFSlide slide1 : ppt.getSlides()) {
				if (slide1.getShapes().stream().anyMatch(shape -> shape instanceof XSLFTextShape
						&& ((XSLFTextShape) shape).getText().contains(ExpectedContent))) {
					slideCount++;
					nlpResponseModel.setStatus(CommonConstants.pass);
					nlpResponseModel.setMessage(
							"Expected Content " + ExpectedContent + " found in slide" + slide1.getSlideNumber());
					slide=true;
					
					} else {
					System.out.println("Validation failed for slide: " + slide1.getSlideNumber());
				}			
			}
			if (slideCount==0) {
				nlpResponseModel.setStatus(CommonConstants.fail);
				nlpResponseModel.setMessage(
						"Expected Content " + ExpectedContent + " not found in given file");
			}
			fis.close();
			ppt.close();
		} catch (Exception e) {
			
		}
		//String string3 = "Return Value";
		//nlpResponseModel.getAttributes().put("string3", string3);
		return nlpResponseModel;
	}
}