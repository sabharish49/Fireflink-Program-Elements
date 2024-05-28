
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

import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component("LIC12620_PJT1003_PE_NLPd9fc2e36-a31a-4792-8ce4-6498a878704e")
public class testHDFC implements Nlp {
	@InputParams({ @InputParam(name = "list", type = "java.util.List"),
			@InputParam(name = "xpath", type = "java.lang.String") })
	@ReturnType(name = "map", type = "java.util.Map")

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
		List list = (List) attributes.get("list");
		String xpath = (String) attributes.get("xpath");
		
		// Your program element business logic goes here ...
		Map<String, String> map = new LinkedHashMap();
		try {
			String[] data = getMap(list, xpath, nlpRequestModel, nlpResponseModel);
			int l = data.length - 1;
			int count = 0, count1 = 0, k = 0, m = 0, n = 0;
			try {
				if (list.size() != data.length) {
					for (int i = 0; i < l; i++) {
						if (count < list.size() && !data[i].contains(" | ")) {
							map.put(((String) list.get(count)).trim(), data[i].trim());
							count++;
							k++;
						}
						if (list.size() <= count) {
							n = i;
							map.put(data[n + 1].trim(), data[n + 2].trim());
							i = i + 1;
						}
						count1 = i;
					}
				}
				else {
					for (int i = 0; i < data.length; i++) {
						if (count < list.size() && !data[i].contains(" | ")) {
							map.put(((String) list.get(count)).trim(), data[i].trim());
							count++;
							k++;
						}
					}

				}

				} 
				catch (Exception e) {
					map.put(data[count1].trim(), data[count1 + 1].trim() + data[count1 + 2].trim());
				}
	
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("map fetched from data successfully");
			}
			catch (Exception e) {
				nlpResponseModel.setStatus(CommonConstants.fail);
				nlpResponseModel.setMessage("failed to fetch map from data" + e);
			}
			nlpResponseModel.getAttributes().put("map", map);
			return nlpResponseModel;
	}

	public String[] getMap(List list, String xpath, NlpRequestModel nlpRequestModel,
			NlpResponseModel nlpResponseModel) {
		String contentDesc = null;
		try {
			AndroidDriver driver = nlpRequestModel.getAndroidDriver();
			contentDesc = driver.findElement(By.xpath(xpath)).getAttribute("content-desc") + "\r\n";
		}

		catch (Exception e) {
			getMap(list, xpath, nlpRequestModel, nlpResponseModel);
		}

		String[] data = contentDesc.split("\r\n");
		return data;

	}
}