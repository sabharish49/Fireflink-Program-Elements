
package default_package;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;

import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.nlp.util.Nlp;
import com.tyss.optimize.nlp.util.NlpException;
import com.tyss.optimize.nlp.util.NlpRequestModel;
import com.tyss.optimize.nlp.util.NlpResponseModel;
import com.tyss.optimize.nlp.util.annotation.InputParam;
import com.tyss.optimize.nlp.util.annotation.InputParams;
import com.tyss.optimize.nlp.util.annotation.ReturnType;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import org.springframework.stereotype.Component;

public class MOB_SwipeAndGetAttribute implements Nlp {
	@InputParams({ @InputParam(name = "Day", type = "java.lang.String"),
			@InputParam(name = "TimeSlot", type = "java.lang.String") })
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
		String Day = (String) attributes.get("Day");
		String TimeSlot = (String) attributes.get("TimeSlot");

		AndroidDriver androidDriver = nlpRequestModel.getAndroidDriver();
		IOSDriver iosDriver = nlpRequestModel.getIosDriver();

		String formattedDate = null;
		boolean result = false;
		try {

			int daysToAdd = Integer.parseInt(Day);
			String slotTime = TimeSlot;

			if (androidDriver != null) {
				LocalDate currentDate = LocalDate.now();
				Scanner scanner = new Scanner(System.in);

				System.out.print("Enter the number of days to add to the current date: ");

				LocalDate futureDate = currentDate.plusDays(daysToAdd);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM, E", Locale.ENGLISH);
				formattedDate = futureDate.format(formatter);

				if (daysToAdd == 0) {
					formattedDate = "Today";
					System.out.println(formattedDate);
				} else if (daysToAdd == 1) {
					formattedDate = "Tomorrow";
					System.out.println(formattedDate);
				} else {
					System.out.println("Future date: " + formattedDate);
				}

				String headerElementXpath = "(//android.widget.FrameLayout//android.widget.TextView[@text='"
						+ formattedDate
						+ "' and @resource-id='com.bigbasket.mobileapp:id/txtHeaderMsg'] | //android.widget.FrameLayout//android.widget.TextView[@text='"
						+ formattedDate
						+ "' and @resource-id='com.bigbasket.mobileapp:id/txtHeaderMsg']/../following-sibling::android.widget.RelativeLayout//android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/itemTitle' and @text='"
						+ slotTime + "'])";
				String timeSlotXpath = "(//android.widget.FrameLayout//android.widget.TextView[@text='" + formattedDate
						+ "' and @resource-id='com.bigbasket.mobileapp:id/txtHeaderMsg']/../../preceding-sibling::androidx.recyclerview.widget.RecyclerView//android.widget.TextView[@resource-id=\"com.bigbasket.mobileapp:id/itemTitle\" and @text='"
						+ slotTime + "'] | //android.widget.FrameLayout//android.widget.TextView[@text='"
						+ formattedDate
						+ "' and @resource-id='com.bigbasket.mobileapp:id/txtHeaderMsg']/../following-sibling::android.widget.RelativeLayout//android.widget.TextView[@resource-id='com.bigbasket.mobileapp:id/itemTitle' and @text='"
						+ slotTime + "'])";

				System.out.println(headerElementXpath);
				System.out.println(timeSlotXpath);

				long startTime = 0;
				boolean isDisplayed = false;
				while (isDisplayed == false) {
					try {
						startTime = System.currentTimeMillis();
						if (androidDriver.findElement(By.xpath(headerElementXpath)).isDisplayed()) {
							boolean time = false;
							while (time == false) {
								try {
									startTime = System.currentTimeMillis();
									WebElement timeSlotElement = androidDriver.findElement(By.xpath(timeSlotXpath));

									if (timeSlotElement.isDisplayed()) {
										timeSlotElement.click();
										time = true;
										isDisplayed = true;
										break;

									}
								} catch (Exception e) {
									System.out.println("performing swipe inside while");

									swipeUpInAndroid(androidDriver);

									System.out.println((System.currentTimeMillis() - startTime) / 1000);
								}
							}
						}

					} catch (Exception e) {
						System.out.println("performing swipe outside while");

						swipeUpInAndroid(androidDriver);

						System.out.println((System.currentTimeMillis() - startTime) / 1000);
					}
				}
			} else {

			}

			result = true;
			nlpResponseModel.setStatus(CommonConstants.pass);
			nlpResponseModel.setMessage("Successfully selected the Time slot on : " + formattedDate + " , " + slotTime);

		} catch (Exception e) {
			nlpResponseModel.setStatus(CommonConstants.fail);
			nlpResponseModel.setMessage("Failed to select the Time slot on " + e);
		}

		nlpResponseModel.getAttributes().put("result", result);
		return nlpResponseModel;
	}

	private void swipeUpInAndroid(AndroidDriver driver) {
		WebElement dropdown = driver.findElement(By.xpath(
				"//androidx.recyclerview.widget.RecyclerView[@resource-id='com.bigbasket.mobileapp:id/slotRecycleView']"));

		Rectangle rect = dropdown.getRect();
		int startX = rect.getX() + (int) (rect.getWidth() * 0.5);
		int startY = rect.getY() + (int) (rect.getHeight() * 0.8);
		int endX = startX;
		int endY = rect.getY() + (int) (rect.getHeight() * 0.2);

		TouchAction<?> touchAction = new TouchAction<>(driver);
		touchAction.press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
				.moveTo(PointOption.point(endX, endY)).release().perform();

	}

}