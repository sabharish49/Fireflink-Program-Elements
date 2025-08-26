package Logics;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.android.AndroidDriver;

public class OptionChain {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {


		String strike = "24,000";
		String ltpOption = "Call LTP";

		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("platformName", "Android");
		dc.setCapability("appium:skipServerInstallation", true);
		dc.setCapability("appium:disableAndroidWatchers", true);
		dc.setCapability("appium:disableWindowAnimation", true);
		dc.setCapability("appium:waitForIdleTimeout", 10);
		dc.setCapability("appium:skipDeviceInitialization", true);
		URL url = new URL("http://localhost:4723/wd/hub");
		AndroidDriver driver = new AndroidDriver(url, dc);

		System.out.println("App launched");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.xpath("//android.widget.TextView[@text='NIFTY']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//android.view.View[@resource-id='com.choiceequitybroking.jiffy:id/vOptionChainClicker']")).click();

		boolean isClicked = false;
		int count = 0;


		while (isClicked ==false && count <= 30) {
			try {

				if (driver.findElement(By.xpath("//android.widget.TextView[@text='" + strike + "']")).isDisplayed()) {
					if (ltpOption.equalsIgnoreCase("Call LTP")) {
						driver.findElement(By.xpath("//android.widget.TextView[@text='" + strike + "']/preceding-sibling::android.view.View[@resource-id='com.choiceequitybroking.jiffy:id/vCallClicker']")).click();
						System.out.println("Clicked Call LTP");
					} else if (ltpOption.equalsIgnoreCase("Put LTP")) {
						driver.findElement(By.xpath("//android.widget.TextView[@text='" + strike + "']/following-sibling::android.view.View[@resource-id='com.choiceequitybroking.jiffy:id/vPutClicker']")).click();
						System.out.println("Clicked Put LTP");
					}
					isClicked = true;
				}
			} catch (Exception e) {

				performSwipeUp(driver);
			}
			count++;
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@text='Sell']")).click();
		



	}

	public static void performSwipeUp(AndroidDriver driver) {

		boolean swipeUp = false;
		Dimension screenSize = driver.manage().window().getSize();
		int screenCenter = (int) (screenSize.getWidth() * 0.5);// Identify center point of screen for Y axis
		int startPoint = (int) (screenSize.getHeight() * 0.5);// Identify beginning point of scroll for X axis
		int endPoint = (int) (screenSize.getHeight() * 0.4);// Identify ending point of scroll
		int count = 0;
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		while (swipeUp == false) {
			Sequence swipe = new Sequence(finger, 1);
			swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(),
					screenCenter, startPoint));
			swipe.addAction(finger.createPointerDown(0));
			swipe.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(),
					screenCenter, endPoint));
			swipe.addAction(finger.createPointerUp(0));
			((RemoteWebDriver) driver).perform(Arrays.asList(swipe));
			if (++count > 1) {
				break;
			}
			if (count >= 1) {
				swipeUp = true;
			} else {

			}

		}

	}

}
