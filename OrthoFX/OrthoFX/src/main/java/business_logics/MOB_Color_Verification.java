package business_logics;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import java.util.Map;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;


@Component("LIC14172_PJT1001_PE_NLPec483f9a-5832-4362-b324-a393b3998b02")
public class MOB_Color_Verification implements Nlp {
	@InputParams({ @InputParam(name = "FilePath", type = "java.lang.String"),
		@InputParam(name = "XPixel", type = "java.lang.Integer"),
		@InputParam(name = "YPixel", type = "java.lang.Integer") })
	@ReturnType(name = "RGB Color", type = "java.lang.String")

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
		String hex="";
		try {
			String FilePath = (String) attributes.get("FilePath");
			Integer XPixel = (Integer) attributes.get("XPixel");
			Integer YPixel = (Integer) attributes.get("YPixel");
			File file = new File(FilePath);
			BufferedImage image = ImageIO.read(file);
			Color c = new Color(image.getRGB(XPixel, YPixel));
			int red = c.getRed();
			int green = c.getGreen();
			int blue = c.getBlue();
			hex = String.format("#%02X%02X%02X", red, green, blue);
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully Fetch The HexaCode"+" "+hex);			
		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed To Get The Colour Of The Image due to exception"+" "+e);
		}
		nlpResponseModel.getAttributes().put("RGB Color",hex );
		return nlpResponseModel;
	}
}