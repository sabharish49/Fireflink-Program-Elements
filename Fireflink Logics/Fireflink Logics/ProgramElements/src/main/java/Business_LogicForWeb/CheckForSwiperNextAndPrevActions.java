package Business_LogicForWeb;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;

@Component("LIC1710_PJT1014_PE_NLPb282fccb-8ac5-4e28-8dee-0a218d5b10b1")
public class CheckForSwiperNextAndPrevActions implements Nlp {
	 @InputParams({	@InputParam(name = "SwipeWrapperImages", type = "java.lang.String"),
		 @InputParam(name = "SwiperPrevButtonDisabled", type = "java.lang.String"),
		 @InputParam(name = "SwiperNextButton", type = "java.lang.String"),
		 @InputParam(name = "SwiperNextButtonDisabled", type = "java.lang.String"),
		 @InputParam(name = "SwiperPrevButton", type = "java.lang.String")})
    

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
		WebDriver driver = nlpRequestModel.getWebDriver();
		String SwipeWrapperImages=(String)attributes.get("SwipeWrapperImages");
		String SwiperPrevButtonDisabled=(String)attributes.get("SwiperPrevButtonDisabled");
		String SwiperNextButton=(String)attributes.get("SwiperNextButton");
		String SwiperNextButtonDisabled=(String)attributes.get("SwiperNextButtonDisabled");
		String SwiperPrevButton=(String)attributes.get("SwiperPrevButton");
		 try {
			   List<WebElement> SwipeWrapperImages1 = driver.findElements(By.xpath(SwipeWrapperImages));
			    int size=SwipeWrapperImages1.size();
			 
			    if(driver.findElement(By.xpath(SwiperPrevButtonDisabled)).isDisplayed()==true) {
			    for(int i=1;i<=size-1;i++) {
			    	Thread.sleep(1000);
			    	driver.findElement(By.xpath(SwiperNextButton)).click();
			    }
			    }
			   if(driver.findElement(By.xpath(SwiperNextButtonDisabled)).isDisplayed()==true && driver.findElement(By.xpath(SwiperPrevButton)).isDisplayed()) {
				   nlpResponseModel.setStatus(CommonConstants.pass);
				   nlpResponseModel.setMessage("SuccesFully Verified Swiper Right And Left");
			   }
			   else {
				   nlpResponseModel.setStatus(CommonConstants.fail);
				   nlpResponseModel.setMessage("Swiper Elements Are Not Displayed");
			   }
			    }
		      catch (Exception e) {
				System.out.println("Something Went Wrong"+e);
			}
			
		
		return nlpResponseModel;
	}
}

