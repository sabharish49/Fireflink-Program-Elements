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

	
@Component("LIC12620_PJT1003_PE_NLP8a163f11-ff20-46d4-899c-7860ca25d784")
public class hdfcDataFetching implements Nlp {
		 @InputParams({@InputParam(name = "keyList", type = "java.util.List"), @InputParam(name = "Xpath", type = "java.Lang.String")})
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
	          
	          List keyList = (List) attributes.get("keyList");
	          String Xpath = (String) attributes.get("Xpath");

	          Map<String, String> map=new LinkedHashMap();
	          
	          try {
	        	  AndroidDriver driver = nlpRequestModel.getAndroidDriver();
		        	 
	        	  String content=getContentDescription(Xpath,driver);
	        	 
	        	  String[] data = content.split("\n");
	        	  
	        	  int l=data.length-1;
	        	  
	        	  int count=0,count1=0,n=0;
					
					try {
						if(keyList.size()!=data.length) {
							for(int i=0;i<l;i++) {
								
								if(count<keyList.size() && !data[i].contains(" | ")) {
									
									map.put(((String) keyList.get(count)).trim(), data[i].trim());							
									count++;
				
								}
								if(keyList.size()<=count) {
									
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
									if(count<keyList.size() && !data[i].contains(" | ")) {
										map.put(((String) keyList.get(count)).trim(), data[i].trim());						
										count++;
										
									}
								}
							
						}
			 
					}
					catch (Exception e) {

						map.put(data[count1].trim(),data[count1+1].trim()+data[count1+2].trim() );
					}
					
					nlpResponseModel.setStatus(CommonConstants.pass);
					nlpResponseModel.setMessage("Successfully fetched the values and stored it in map");
	        	  
	          }
	          catch (Exception e) {
	        	  nlpResponseModel.setStatus(CommonConstants.fail);
				  nlpResponseModel.setMessage("Failed to fetch the values and store in map"+e);
	          }
	          
	          nlpResponseModel.getAttributes().put("map", map);
	          return nlpResponseModel;
	      }
	      
	      public static String getContentDescription(String xpath, AndroidDriver driver) {
				String contentDesc=null;
				
				try {
					
				contentDesc=driver.findElement(By.xpath(xpath)).getAttribute("content-desc");
				
				}
				catch (Exception e) {
					getContentDescription(xpath, driver);
				}
				contentDesc=contentDesc+"\n";
				return contentDesc;
			}
	   
	

}
