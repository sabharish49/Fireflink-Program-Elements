package Business_Logic;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;

import org.springframework.stereotype.Component;

@Component("LIC17078_PJT1001_PE_NLPa0ce346f-f7a9-45a2-a775-bae3c35f30d4")
public class Verify_Toaster_Message implements Nlp {

	@ReturnType(name = "result", type = "java.lang.Boolean")

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
		//String xpath = (String) attributes.get("xpath");
		boolean result = false;
		try {
			
			result=visible(nlpRequestModel, nlpResponseModel);
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("element is displayed");
		} catch (Exception e) {
			result = false;
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("element is not displayed");
		}

		nlpResponseModel.getAttributes().put("result", result);
		return nlpResponseModel;
	}

	public boolean visible(NlpRequestModel nlpRequestModel, NlpResponseModel nlpResponseModel)throws InterruptedException {
		
		AndroidDriver driver = nlpRequestModel.getAndroidDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		driver.findElement(By.xpath("//android.widget.Button[@content-desc='VALIDATE']")).click();
		int count=0;
			boolean flag=false;
			driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"VALIDATE\"]")).click();
			while(count<20)
			{
				try {
					driver.findElement(By.xpath("/hierarchy/android.widget.Toast"));			
					flag=true;
					break;
				} catch (Exception e) {
					Thread.sleep(50);
				}
				count++;
			}
			System.out.println(flag);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return flag;
	}

}