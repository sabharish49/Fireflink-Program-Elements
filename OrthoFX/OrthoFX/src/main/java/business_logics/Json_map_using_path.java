package business_logics;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap; // Changed import
import java.util.List;
import java.util.Map;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.jayway.jsonpath.JsonPath;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import org.springframework.stereotype.Component;



@Component("LIC20128_PJT1001_PE_NLP141a961a-2790-4d4b-b722-c72cb57faeb1")
public class Json_map_using_path implements Nlp {
    @InputParams({ @InputParam(name = "Stream", type = "java.io.InputStream"),
            @InputParam(name = "JSON Path", type = "java.lang.String") })
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
        DesiredCapabilities cap = (DesiredCapabilities) attributes.get("Desired Capabilities");
        Map<String, Map<String, Object>> result = new LinkedHashMap<>(); // Changed map declaration
        InputStream fis = (InputStream) attributes.get("Stream");
        String path = (String) attributes.get("JSON Path");
        String jsonInput = "";
        LinkedHashMap<String, Object> salDataMap = null; // Changed map instantiation
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
            String tempString;
            while ((tempString = bufferedReader.readLine()) != null) {
                jsonInput = jsonInput + tempString;
            }
            bufferedReader.close();
            salDataMap = extractValuesFromJsonPath(jsonInput, path);
            System.out.println("Values of salData:");
            for (Map.Entry<String, Object> entry : salDataMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            nlpResponseModel.setStatus(CommonConstants.pass);
            nlpResponseModel.setMessage(salDataMap + " count is " + salDataMap.size());
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            // log.info(exceptionAsString);
            nlpResponseModel.setStatus(CommonConstants.fail);
            nlpResponseModel.setMessage(salDataMap + " Failed  " + exceptionAsString);
        }
        nlpResponseModel.getAttributes().put("map", salDataMap);
        return nlpResponseModel;
    }

    public static LinkedHashMap<String, Object> extractValuesFromJsonPath(String jsonString,
            String jsonPathExpression) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>(); // Changed map declaration
        Object result = JsonPath.read(jsonString, jsonPathExpression);
        if (result instanceof Map) {
            resultMap.putAll((LinkedHashMap<String, Object>) result); // Changed map instantiation
        }
        return resultMap;
    }
}
