package Business_Logic;


//Split the given Object and Return the List

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import lombok.extern.log4j.Log4j2;

import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
@Log4j2

@Component("LIC16279_PJT1002_PE_NLPb1baac19-9e50-48ef-8c65-05140e2b2217")
public class Splitfuction implements Nlp {
  @InputParams({@InputParam(name = "list", type = "java.lang.Object"),@InputParam(name = "Delimiter", type = "java.lang.String")})
  @ReturnType(name = "returnList", type = "java.util.List")

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
      //  List<String> list = (List<String>) attributes.get("list");
        String Delimiter = (String) attributes.get("Delimiter");
        Object list = (Object) attributes.get("list");
        String list1=list.toString();
        List<String> returnList=null;
        try {
      	  returnList = getSplitData(list1,Delimiter);
      	   nlpResponseModel.setStatus(CommonConstants.pass);
             nlpResponseModel.setMessage("Sucessfully Fetched the Data");
        }catch(Exception e) {
      	  nlpResponseModel.setStatus(CommonConstants.fail+" "+e);
     	   nlpResponseModel.setMessage("Failed due to Exception");
        }

        //String string3 = "Return Value";
        nlpResponseModel.getAttributes().put("returnList", returnList);
        return nlpResponseModel;
    }
    public static List<String> getSplitData(String s,String Delimiter){
        return Arrays.asList(s.split(Delimiter));
     }
}
