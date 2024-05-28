package Practice;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class DragDrop {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		DesiredCapabilities dc=new DesiredCapabilities();
				dc.setCapability("deviceName", "RZ8T60DLKYZ");
				dc.setCapability("noReset", "true");
				dc.setCapability("platformName", "Android");
				dc.setCapability("appPackage", "com.carecentra.mymobemap");
				dc.setCapability("appActivity", "com.carecentra.mymobemap.MainActivity");
				dc.setCapability("noReset", true);
				URL url = new URL("http://localhost:4723/wd/hub");			
				AndroidDriver driver = new AndroidDriver(url, dc);
				Thread.sleep(10000);
				WebElement Source = driver.findElement(By.xpath("//android.widget.TextView[@text='Get adequate sleep']"));
			    WebElement Destination = driver.findElement(By.xpath("//android.widget.TextView[@text='Maintain a balanced diet']"));				
			    TouchAction Touch = new TouchAction(driver);
	      		Touch.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(Source))
	                    .withDuration(Duration.ofMillis(30)))
	            .moveTo(ElementOption.element(Destination))
	            .release()
	            .perform();	  
	}
}