package Practice;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class Create_Lead {
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		DesiredCapabilities capabilitiesApp1 = new DesiredCapabilities();
	     capabilitiesApp1.setCapability("platformName", "Android");
	     AndroidDriver  driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilitiesApp1); 
	     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	     
	     Thread.sleep(5000);
	      driver.findElement(By.xpath("//android.widget.TextView[@text=\"LEAD\"]")).click();
	      driver.findElement(By.xpath("//android.widget.ImageButton[@resource-id=\"com.apacfin.newalpha:id/btnAddNew\"]")).click();
	      driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.apacfin.newalpha:id/edtFName\"]")).sendKeys("abhi");
	      driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.apacfin.newalpha:id/edtLName\"]")).sendKeys("sammy");
	      driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.apacfin.newalpha:id/edtMobileNo\"]")).sendKeys("8765432678");

	      
	}

}
