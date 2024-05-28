package Business_LogicForWeb;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;




@Component("LIC1710_PJT1014_PE_NLP6056ea97-12f8-42ab-bc09-c37e28ea2705")
public class ListCompareWithList implements Nlp {
	@InputParams({@InputParam(name = "ListOfText_1", type = "java.lang.ArrayList"),
				  @InputParam(name = "ListOfText_2", type = "java.lang.ArrayList")})
    @ReturnType(name = "ListOfText_3", type = "java.util.ArrayList")

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
    @SuppressWarnings("unchecked")
	@Override
      public NlpResponseModel execute(NlpRequestModel nlpRequestModel) throws NlpException {
        
          NlpResponseModel nlpResponseModel = new NlpResponseModel();
          Map<String, Object> attributes = nlpRequestModel.getAttributes();

      ArrayList<String> ListOfText_3 = new ArrayList<String>();
      Boolean cond = false;
      try 
      	{
    	  	ArrayList<String> ListOfText_1 = (ArrayList<String>) attributes.get("ListOfText_1");
          	ArrayList<String> ListOfText_2 = (ArrayList<String>) attributes.get("ListOfText_2");
      		int x = ListOfText_1.size()-1;
      		int y = x+1;
      		int z = ListOfText_2.size()-1;
      		for(int i = y; i <= z; i++) 
      		{
      			for(int j = 0; j <= x; j++)
      			{
      				if(ListOfText_2.get(i).equals(ListOfText_1.get(j)))
      				{
      					ListOfText_3.add(ListOfText_2.get(i));
      		  			cond = true;
      				}
      			}
      		}
      		if(cond == true)
      		{
	      		nlpResponseModel.setMessage(" Found Duplicates ");
	  			nlpResponseModel.setStatus(CommonConstants.pass);
      		}
      		else
      		{
	      		nlpResponseModel.setMessage(" No Duplicates Found ");
	  			nlpResponseModel.setStatus(CommonConstants.pass);
      		}
		}
      catch (Exception e)
      	{

  			nlpResponseModel.setStatus(CommonConstants.fail);
  			nlpResponseModel.setMessage(" Something Went Wrong ");
		}
      nlpResponseModel.getAttributes().put("listofText", ListOfText_3);
      return nlpResponseModel;
      }
  } 