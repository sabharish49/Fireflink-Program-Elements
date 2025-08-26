package Business_Logics;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
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


public class stockPricePolling implements Nlp {
	@InputParams({ @InputParam(name = "maxvalue", type = "java.lang.String"),
			@InputParam(name = "minvalue", type = "java.lang.String"),
			@InputParam(name = "Timelimit", type = "java.lang.String"),
			@InputParam(name = "xpath", type = "java.lang.String") })
	 @ReturnType(name = "ActionType", type = "java.lang.String")

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
		String maxvalue1 = (String) attributes.get("maxvalue");
		String minvalue1 = (String) attributes.get("minvalue");

		String result=null;
		AndroidDriver driver = nlpRequestModel.getAndroidDriver();
		Duration implicitWait = driver.manage().timeouts().getImplicitWaitTimeout();
		try {
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));			// String element =
			// driver.findElement(By.xpath(xpath)).getAttribute("content-desc");
			String ltp = getValue(driver, xpath);
			System.out.println(ltp);

			String maxpercentageInput = maxvalue1;
			String minpercentageInput = minvalue1;

			double principalAmount = Double.parseDouble(ltp);
			double maxValue = calculatePercentageValue(maxpercentageInput, principalAmount);
			double minValue = calculatePercentageValue(minpercentageInput, principalAmount);

			System.out.println("The calculated max percentage value is: " + maxValue);
			System.out.println("The calculated min percentage value is: " + minValue);

			boolean isChanged = false;

			int count = 1;
			try {
				String time = Timelimit;

				String s = time.substring(0, time.length() - 1);
				int number = Integer.parseInt(s);
				String type = time.substring(time.length() - 1);
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
					break;
				}

				long startTime = System.currentTimeMillis();

				while (System.currentTimeMillis() - startTime <= number) {
					String value = getValue(driver, xpath);
					if (!(value.equals(ltp))) {
						try {
							double actualvalue = Double.parseDouble(getValue(driver, xpath));
							if (actualvalue <= minValue && actualvalue < maxValue) {

								nlpResponseModel.setMessage("Successfully sold the stocks for:" + " " + actualvalue
										+ " " + "min:" + minValue + " " + "max:" + maxValue);
								driver.findElement(By.xpath(
										"//android.widget.Button[@resource-id='com.choiceequitybroking.jiffy:id/btnSell' or @text='Sell']"))
										.click();
								count = 10;
								isChanged = true;
								result="Sell";
								break;
							} else if (actualvalue > minValue && actualvalue >= maxValue) {
								nlpResponseModel.setMessage("Successfully bought the stocks for:" + " " + actualvalue
										+ " " + "min:" + minValue + " " + "max:" + maxValue);
								driver.findElement(By.xpath(
										"//android.widget.Button[@resource-id='com.choiceequitybroking.jiffy:id/btnBuy' or @text='Buy']"))
										.click();

								count = 10;
								isChanged = true;
								result="Buy";
								break;
							}

						} catch (Exception e) {
							// TODO: handle exception
						}
					}

				}

			}

			catch (Exception e) {
				// TODO: handle exception
			}

			if (isChanged == false) {
				nlpResponseModel.setStatus(CommonConstants.fail);
				nlpResponseModel.setMessage("Failed to buy or sell stock within the stipulated time ");

				System.out.println("Failed to buy or sell stock within the stipulated time");
			} else {
				nlpResponseModel.setStatus(CommonConstants.pass);
			}

		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed due to exception " + e);
		}
		finally {
			driver.manage().timeouts().implicitlyWait(implicitWait);
		}

		nlpResponseModel.getAttributes().put("ActionType", result);
        return nlpResponseModel;
	}

	public static double calculatePercentageValue(String percentageInput, double principalAmount) {

		String percentageString = percentageInput.replace("%", "");
		double percentage = Double.parseDouble(percentageString);
		double result = 0;
		// Calculate the percentage value
		double percentageValue = (percentage / 100) * principalAmount;
		result = (principalAmount + percentageValue);
		double roundedValue = Math.round(result * 100.0) / 100.0;

		return roundedValue;
	}

	public static String getValue(AndroidDriver driver, String xpath) {
		String value = null;
		try {
			value = driver.findElement(By.xpath(xpath)).getAttribute("content-desc").replaceAll(",", "").trim();
		} catch (Exception e) {
			getValue(driver, xpath);
		}
		return value;
	}
}
