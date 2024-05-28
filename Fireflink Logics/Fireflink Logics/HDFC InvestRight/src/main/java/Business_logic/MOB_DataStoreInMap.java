package Business_logic;



import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;
@Slf4j


@Component("LIC14172_PJT1001_PE_NLPf56fc76a-e6f8-41b7-91d0-51e4f703c005")
public class MOB_DataStoreInMap implements Nlp {
	@InputParams({ @InputParam(name = "list", type = "java.lang.String"),@InputParam(name = "xpath", type = "java.lang.String") })
	@ReturnType(name = "Resultmap", type = "java.util.Map")
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
          String list = (String) attributes.get("list");
          String xpath = (String) attributes.get("xpath");
          AndroidDriver driver = nlpRequestModel.getAndroidDriver();
          String s=null;
          LinkedHashMap<String, String> map=new LinkedHashMap<String, String>();   
          try {
        	  String keys[]=list.split(":");      
        	  for(int i=0;i<20;i++) 
  			  {
	  				try {
	  					WebElement element=driver.findElement(By.xpath(xpath));	
	  					s=element.getAttribute("content-desc");
	  				}
	  				catch (Exception e) {
	  					
	  				}
	  				if(s!=null) {
	  					break;
	  				}
  			  }
      		String[] data = s.split("\n");
      		
      		System.out.println(data.length);
      		for (String string : data) {
      			System.out.println(string.trim());
      		}
      		System.out.println();
      		
      		System.out.println("array printed");
      		int l=data.length-1;
      		int count=0,count1=0,k=0,m=0,n=0;     				
      				try {
      					if(keys.length!=data.length) {
      						for(int i=0;i<l;i++) {							
      							if(count<keys.length && !data[i].contains(" | ")) {
      								
      								map.put(keys[count].trim(), data[i].trim());
      								
      								count++;
      			;					k++;
      							}	
      						if(keys.length<=count) {
      								n=i;
      								map.put(data[n+1].trim(), data[n+2].trim());
      								i=i+1;
      							}
      							count1=i;	
      						}
      					}
      					else {
      							for(int i=0;i<data.length;i++)
      							{	
      								if(count<keys.length && !data[i].contains(" | ")) {
      									map.put(keys[count].trim(), data[i].trim());						
      									count++;
      									k++;
      								}
      							}     						
      					}
      		 
      				}
      				catch (Exception e) {
      					//log.info("excepion inside"+e);
      					map.put(data[count1].trim(),data[count1+1].trim()+data[count1+2].trim() );
      				}  				
      				nlpResponseModel.setStatus(CommonConstants.pass);
      				nlpResponseModel.setMessage("map fetched from data");

          }
          catch (Exception e) {
        	  nlpResponseModel.setStatus(CommonConstants.fail);
			  nlpResponseModel.setMessage("failed to fetch map from data"+e);
		}

          //String string3 = "Return Value";
          nlpResponseModel.getAttributes().put("Resultmap", map);
          return nlpResponseModel;
      }
}
