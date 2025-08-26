package Business_Logics;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

import io.appium.java_client.android.AndroidDriver;

@Component("LIC20369_PJT1002_PE_NLP1e19de6c-89ad-4b3d-860a-e055617db721")
public class stockPricePolling implements Nlp {
	@InputParams({@InputParam(name = "maxvalue", type = "java.lang.Integer"), @InputParam(name = "minvalue", type = "java.lang.Integer"), @InputParam(name = "Timelimit", type = "java.lang.String"), @InputParam(name = "xpath", type = "java.lang.String")})
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
		String Timelimit = (String) attributes.get("Timelimit");
		String xpath = (String) attributes.get("xpath");
		Integer maxvalue = (Integer) attributes.get("maxvalue");
		Integer minvalue = (Integer) attributes.get("minvalue");
		int count=1;	
		try {
			AndroidDriver driver=(AndroidDriver)nlpRequestModel.getAndroidDriver();	
			while(count!=0) {
				try {
					String ele=driver.findElement(By.xpath(xpath)).getAttribute("content-desc");		
					count=0;	
					String value=ele.replaceAll(",", "").trim();
					System.out.println(value);
					
					int actualvalue=Integer.parseInt(value);
					long time=System.currentTimeMillis();
					long d=Duration.ofMillis(time).toMillis();
					String Time=Timelimit;
					int s=Integer.parseInt(Time);
					long duration=d+s;
					
					while(actualvalue>minvalue || actualvalue<maxvalue) {
						long fetchtime=System.currentTimeMillis();
						d=Duration.ofMillis(fetchtime).toMillis();
						actualvalue=0;
						count=1;
						while(count!=0) {
							try {
								ele=driver.findElement(By.xpath(xpath)).getAttribute("content-desc");
								count=0;
								String value1=ele.replaceAll(",", "").trim();
								actualvalue=Integer.parseInt(value1);
							}catch(StaleElementReferenceException e) {
								
							}
						}
						if(d>=duration) {
							count=10;
							nlpResponseModel.setStatus(CommonConstants.fail);
							nlpResponseModel.setMessage("Failed to buy stock for Rs." + actualvalue);
							break;
						}
						if(actualvalue>minvalue && actualvalue<maxvalue) {
							nlpResponseModel.setStatus(CommonConstants.fail);
							nlpResponseModel.setMessage("Failed to buy stock for Rs. " + actualvalue+" "+"min:"+minvalue+" "+"max:"+maxvalue);
							count=10;
							break;
						}
						else if(actualvalue<minvalue && actualvalue<maxvalue) {
							nlpResponseModel.setStatus(CommonConstants.pass);
							nlpResponseModel.setMessage("Successfully sold the stocks for:"+" "+actualvalue+" "+"min:"+minvalue+" "+"max:"+maxvalue);
							driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.choiceequitybroking.jiffy:id/btnBuy' or @text='Sell']")).click();
							count=10;
							break;
						}
						else {
							nlpResponseModel.setStatus(CommonConstants.pass);
							nlpResponseModel.setMessage("Successfully bought the stocks for:"+" "+actualvalue+" "+"min:"+minvalue+" "+"max:"+maxvalue);
							driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.choiceequitybroking.jiffy:id/btnBuy' or @text='Buy']")).click();

							count=10;
							break;
						}
					}
					if(count==10) {
						break;
					}

				}catch(StaleElementReferenceException e) {

				}
			}
		}catch(Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed due to exception");
		}

		
		return nlpResponseModel;
	}
} 
