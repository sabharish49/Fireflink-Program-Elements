
package Business_Logic;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import net.minidev.json.JSONArray;

import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Component;

@Component("LIC23707_PJT1008_PE_NLP2abf1478-b671-4938-a42b-792667a4ce0a")
public class ValidateDataInRespone implements Nlp {
	@InputParams({ @InputParam(name = "InputMap", type = "java.util.Map"),
			@InputParam(name = "JSONData", type = "java.lang.String") })
	@ReturnType(name = "FailedKeymap", type = "java.util.Map")

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
		LinkedHashMap<String, String> FailedKeymap = new LinkedHashMap<String, String>();

		try {

			Map<String, String> InputMap = (Map) attributes.get("InputMap");
			String JSONData = (String) attributes.get("JSONData");

			String jsonString = JSONData;
			ReadContext ctx = JsonPath.parse(jsonString);

			for (Map.Entry entry : InputMap.entrySet()) {
				String key = (String) entry.getKey();
				String val = (String) entry.getValue();

				if (!(key.contains("Multi"))) {
					String exp1 = "$.." + key + "";
					JSONArray jsonValue = ctx.read(exp1);
					jsonValue.toArray();
					String value = jsonValue.toString().replaceAll("[\\[\\]\"]", "");
					if (val.contains(value)) {
						System.out.println(value + "==" + val);
					} else {
						System.out.println(value + "!=" + val);
						FailedKeymap.put(key, value);
					}
				} else {
					String[] div = key.split("-");

					String exp1 = "$.." + div[1] + "[" + div[2] + "]" + "." + div[3];
					System.out.println(exp1);
					JSONArray jsonValue = ctx.read(exp1);
					jsonValue.toArray();
					String value = jsonValue.toString().replaceAll("[\\[\\]\"]", "");
					if (val.contains(value)) {
						System.out.println(value + "==" + val);
					} else {
						System.out.println(value + "!=" + val);
						FailedKeymap.put(div[1] + " " + div[2] + " " + div[3], value);
					}
				}

			}

			if (FailedKeymap.isEmpty()) {
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Successfully Validated input data with Response JSON");
			} else {
				nlpResponseModel.setStatus(CommonConstants.fail);
				nlpResponseModel.setMessage("Failed to Validate input data with Response JSON ");
			}

		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Failed to Validat input data with Response JSON " + e);
		}

		nlpResponseModel.getAttributes().put("FailedKeymap", FailedKeymap);
		return nlpResponseModel;
	}
}