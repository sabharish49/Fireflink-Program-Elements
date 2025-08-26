package default_package;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Snippet {
	private void swipeUpInIOS(IOSDriver driver) {
			WebElement dropdown = driver.findElement(By.xpath(
					
		    "//XCUIElementTypeApplication[@name=\"bigbasket\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[3]/XCUIElementTypeOther[2]/XCUIElementTypeTable"));
	
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

