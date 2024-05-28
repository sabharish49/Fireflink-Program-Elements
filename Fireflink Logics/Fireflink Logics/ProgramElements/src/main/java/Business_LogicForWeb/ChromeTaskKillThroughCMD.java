package Business_LogicForWeb;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




import org.springframework.stereotype.Component;

@Component("LIC1710_PJT1014_PE_NLP10a498f8-047e-466c-ac9f-7b5fa7797531")
public class ChromeTaskKillThroughCMD implements Nlp {

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
		try 
		{
			Runtime.getRuntime().exec("taskkill /F /IM chrome.exe /T");
			Thread.sleep(2000);
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exc /T");
			Thread.sleep(2000);
	        Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	        Thread.sleep(2000);
	        Runtime.getRuntime().exec("taskkill /F /IM WindowsTerminal.exe");
	        Thread.sleep(2000);
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully closed all Chrome Instance ");
		} 
		catch (Exception e)
		{
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to close");
		}
		return nlpResponseModel;
	}
}