
package Web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.android.AndroidDriver;




public class clickOnChoiceEmail implements Nlp {
	@InputParams({ @InputParam(name = "Duration", type = "java.lang.String") })
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

		String Duration = (String) attributes.get("Duration");

		boolean b = true;
		try {
			WebDriver driver = nlpRequestModel.getWebDriver();
			long startTime = System.currentTimeMillis();

			Date now = new Date();
			SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
			String currentTime = sdf1.format(now);
			Date date = sdf1.parse(currentTime);

			String time = Duration;

			String s = time.substring(0, time.length() - 1);
			int number = Integer.parseInt(s);
			// System.out.println(number);
			String type = time.substring(time.length() - 1);
			// System.out.println(type);

			switch (type) {
			case "s":
				number = number * 1000;
				break;
			case "m":
				number = number * 60 * 1000;
				break;
			case "h":
				number = number * 60 * 60 * 1000;
				break;
			default:
				// System.out.println("enter proper input");
				break;
			}

			String mailTime = null;
			String OTP = null;

			String xpath = "((//span[text()='Choice FinX-Login OTP'])[1]/ancestor::td/following-sibling::td[4]//span)[1]";

			try {
				while (b == true && System.currentTimeMillis() - startTime <= number) {
					try {
						if (driver.findElement(By.xpath(xpath)).getAttribute("title").contains("")) {
							while (driver.findElement(By.xpath(xpath)).getAttribute("title") != null && b == true
									&& System.currentTimeMillis() - startTime <= number) {

								SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
								sdf.setLenient(false); // To ensure strict parsing of the time

								try {

									Calendar calendar = Calendar.getInstance();
									calendar.setTime(date);
									currentTime = sdf.format(calendar.getTime());
									if (currentTime.charAt(0) == '0') {
										currentTime = currentTime.substring(1, currentTime.length());
									}
									System.out.println("Input time: " + currentTime);

									calendar.add(Calendar.MINUTE, -1);
									String minuteLess = sdf.format(calendar.getTime());
									if (minuteLess.charAt(0) == '0') {
										minuteLess = minuteLess.substring(1, minuteLess.length());
									}
									System.out.println("One minute less: " + minuteLess);

									calendar.setTime(date);
									calendar.add(Calendar.MINUTE, 1);
									String minuteHigh = sdf.format(calendar.getTime());
									if (minuteHigh.charAt(0) == '0') {
										minuteHigh = minuteHigh.substring(1, minuteHigh.length());
									}
									System.out.println("One minute more: " + minuteHigh);

									String attributeValue = driver.findElement(By.xpath(xpath)).getAttribute("title");
									System.out.println(attributeValue);

									if (attributeValue.contains(minuteLess) || attributeValue.contains(minuteHigh)
											|| attributeValue.contains(currentTime)) {
										driver.findElement(By.xpath("(//span[text()='Choice FinX-Login OTP'][1])[2]"))
												.click();
										b = false;
										break;
									}

								} catch (Exception e) {
									e.printStackTrace();
								}

							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("performed action");
			if (b == true) {
				nlpResponseModel.setStatus(CommonConstants.fail);
				nlpResponseModel.setMessage("Failed to click on email Tab because email is not yet received");
			} else {
				nlpResponseModel.setStatus(CommonConstants.pass);
				nlpResponseModel.setMessage("Successfully clicked on email tab");
			}

		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to Click on email Tab " + e);
		}

		nlpResponseModel.getAttributes().put("result", b);
		return nlpResponseModel;
	}
}