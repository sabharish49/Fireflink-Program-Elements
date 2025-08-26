package Business_Logics;

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
import io.appium.java_client.ios.IOSDriver;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Slf4j

@Component("LIC20665_PJT1001_PE_NLP85cccd4d-bbad-45f2-b4d3-782df7101340")
public class MOB_EnterInputintoElement implements Nlp {
	@InputParams({ @InputParam(name = "inputData", type = "java.lang.String") })
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
		String input = (String) attributes.get("inputData");

		AndroidDriver androidDriver = nlpRequestModel.getAndroidDriver();
		IOSDriver iosDriver = nlpRequestModel.getIosDriver();
		try {

			if (androidDriver != null) {
				log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!androidDriver!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				for (int i = 0; i < input.length(); i++) {
					log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!forLoop!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + i);
					char ch = input.charAt(i);
					log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Char ch!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + ch
							+ "           " + i);
					int value = ch - '0';
					switch (value) {
					case 0:
						log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SwitchCase!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Case0");

						androidDriver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_0));
						break;
					case 1:
						log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SwitchCase!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Case1");

						androidDriver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_1));
						break;
					case 2:
						log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SwitchCase!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Case2");

						androidDriver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_2));
						break;
					case 3:
						log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SwitchCase!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Case3");

						androidDriver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_3));
						break;
					case 4:
						log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SwitchCase!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Case4");

						androidDriver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_4));
						break;
					case 5:
						log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SwitchCase!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Case5");

						androidDriver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_5));
						break;
					case 6:
						log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SwitchCase!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Case6");

						androidDriver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_6));
						break;
					case 7:
						log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SwitchCase!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Case7");

						androidDriver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_7));
						break;
					case 8:
						log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SwitchCase!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Case8");

						androidDriver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_8));
						break;
					case 9:
						log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!SwitchCase!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Case9");

						androidDriver.pressKey(new KeyEvent().withKey(AndroidKey.DIGIT_9));
						break;
					default:
						System.out.println("none");
					}
				}

			} else {

			}

			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("succesfully Entered the value into otp textfield");

		} catch (Exception e) {
			log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Exception is!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + e);
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("failed to enter value into otp textfield");
		}

		// String string3 = "Return Value";
		// nlpResponseModel.getAttributes().put("string3", string3);
		return nlpResponseModel;
	}
}
