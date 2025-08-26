package checkapp;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class Chec {
public static void main(String[] args) throws MalformedURLException, InterruptedException {
	
	DesiredCapabilities capabilities=new DesiredCapabilities();
	capabilities.setCapability("platformName", "Android");
//	capabilities.setCapability("appPackage","com.pantaloons");
//	capabilities.setCapability("appActivity", "com.pantaloons.MainActivity");
    capabilities.setCapability("noReset",true);	
	capabilities.setCapability("waitForIdleTimeout", 0);
	AndroidDriver driver=new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
	Thread.sleep((45000));
	driver.findElement(By.id("com.oplus.camera:id/shutter_button")).click();
}
}
