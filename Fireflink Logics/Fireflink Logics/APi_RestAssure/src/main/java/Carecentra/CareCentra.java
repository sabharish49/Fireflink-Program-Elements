package Carecentra;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;

public class CareCentra {
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
	
		DesiredCapabilities dc=new DesiredCapabilities();
				dc.setCapability("deviceName", "RZ8T60DLKYZ");
				dc.setCapability("platformName", "Android");
				dc.setCapability("appPackage", "com.carecentra.mymobemap");
				dc.setCapability("appActivity", "com.carecentra.mymobemap.MainActivity");
				dc.setCapability("noReset", true);
				dc.setCapability("waitForIdleTimeout",5);
				URL url = new URL("http://localhost:4723/wd/hub");		
				AndroidDriver driver = new AndroidDriver(url, dc);
				Thread.sleep(20000);
			WebElement Source = driver.findElement(By.xpath("//android.widget.TextView[@text='Get adequate sleep']"));
			WebElement Destination = driver.findElement(By.xpath("//android.widget.TextView[@text='Maintain a balanced diet']"));
	      	Actions act=new Actions(driver);
	      	act.dragAndDrop(Source, Destination).build().perform();
	}

}

