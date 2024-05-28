package Practice;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Sample_class {
	@SuppressWarnings({ "deprecation", "rawtypes" })
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability("noReset", "true");
		dc.setCapability("platformName", "Android");
		dc.setCapability("noReset", true);
		//dc.setCapability("waitForIdleTimeout",5);
		URL url = new URL("http://localhost:4723/wd/hub");
		AndroidDriver driver = new AndroidDriver(url, dc);	
		System.out.println("dcbhdbhdb");
		Thread.sleep(5000);
		int x = 106;
		int y = 700;
		//WebElement e1 = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[5]"));
		// ((JavascriptExecutor) driver).executeScript("document.elementFromPoint(arguments[0], arguments[1]).click();", x, y);
        Actions actions = new Actions(driver);
        Thread.sleep(5000);
        new TouchAction(driver)
        .press(PointOption.point(106,700))
        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
        .release().perform();
       // actions.moveToElement(e1).moveByOffset(106, 700).click().perform();
       System.out.println("sss");
        
	}
}
