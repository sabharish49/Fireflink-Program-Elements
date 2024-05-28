package Business_Logic;


import com.tyss.optimize.common.util.CommonConstants;

import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
@Slf4j


@Component("LIC17078_PJT1001_PE_NLP45581770-1340-4155-b135-916108196f23")
public class MOB_EnterInputIntoElement implements Nlp {
	@InputParams({@InputParam(name = "input", type = "java.lang.String")})
	// @ReturnType(name = "string3", type = "java.lang.String")

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
		String input = (String) attributes.get("input");
		//   String string2 = (String) attributes.get("string2");
		// String store=otp;
		try {
			AndroidDriver driver=(AndroidDriver)nlpRequestModel.getAndroidDriver();
			for(int i=0;i<input.length();i++) {
				char ch=input.charAt(i);
				int value=ch-'0';
				switch(value) {
				case 0:

					driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_0));
					break;
				case 1:

					driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_1));
					break;
				case 2:

					driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_2));
					break;
				case 3:

					driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_3));
					break;
				case 4:

					driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_4));
					break;
				case 5:

					driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_5));
					break;
				case 6:

					driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_6));
					break;
				case 7:

					driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_7));
					break;
				case 8:

					driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_8));
					break;
				case 9:

					driver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_9));
					break;
				default:
					System.out.println("none");
				}
			}

			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("succesfully Entered the value into otp textfield"); 


		}
		catch(Exception e)
		{
		nlpResponseModel.setStatus(CommonConstants.fail);
		nlpResponseModel.setMessage("failed to enter value into otp textfield"); 
		}


		// Your program element business logic goes here ...

		// String string3 = "Return Value";
		// nlpResponseModel.getAttributes().put("string3", string3);
		return nlpResponseModel;
	}
} 
