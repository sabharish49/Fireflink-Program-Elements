package Practice;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class AndroidBrowser {
 public static void main(String[] args) throws MalformedURLException, InterruptedException {
	 DesiredCapabilities capabilitiesApp1 = new DesiredCapabilities();
     capabilitiesApp1.setCapability("platformName", "Android");
       
     AndroidDriver  driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilitiesApp1);
     driver.findElement(By.xpath("//android.widget.Button[@text=\"PROCEED TO PAY\"]")).click();
     Thread.sleep(4000);
     driver.findElement(By.xpath("//android.view.View[@text=\"Phone Number\"]")).click();
     driver.findElement(By.xpath("//android.view.View[@text=\"Phone Number\"]")).sendKeys("1234567898");
     
}
}
