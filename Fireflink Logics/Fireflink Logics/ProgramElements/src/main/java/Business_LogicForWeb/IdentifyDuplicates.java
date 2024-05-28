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





@Component("LIC1710_PJT1014_PE_NLP2765aa61-976c-4736-980f-7ca5c4f4fc18")
public class IdentifyDuplicates implements Nlp {
	@InputParams({@InputParam(name = "ListOfText_1", type = "java.lang.ArrayList")})
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
	  	int k = 0;
      try 
      	{
    	  	ArrayList<String> ListOfText_1 = (ArrayList<String>) attributes.get("ListOfText_1");
	  		ArrayList<String> ListOfText_2 = new ArrayList<String>();
	  		ListOfText_2.addAll(ListOfText_1);
	  		int x = ListOfText_1.size()-1;
	  		int count = 0;
	  		for(int i = 0; i <= x; i++) 
	  		{
	  			for(int j = 0; j <= x; j++)
	  			{
	  				if(ListOfText_1.get(i).equals(ListOfText_2.get(j)))
	  				{
	  					Thread.sleep(2000);
	  					count++;
	  					if(count > 1)
	  					{
	  						ListOfText_3.add(k, ListOfText_1.get(i));
	  						k++;
	  						Thread.sleep(1000);
	  					}
	  				}
	  			}
	  			count = 0;
	  		}
	  		if(ListOfText_3.isEmpty())
	  		{
	  			nlpResponseModel.setStatus(CommonConstants.pass);
	  			nlpResponseModel.setMessage(" No Duplicates Found ");
	  		}
	  		else
	  		{
	  			nlpResponseModel.setStatus(CommonConstants.pass);
	  			nlpResponseModel.setMessage(" Duplicates Found ");
	  		}
		}
      catch (Exception e)
      	{
  			nlpResponseModel.setStatus(CommonConstants.pass);
  			nlpResponseModel.setMessage(" Something Went Wrong ");
		}
      nlpResponseModel.getAttributes().put("ListOfText_3", ListOfText_3);
      return nlpResponseModel;
      }
  } 