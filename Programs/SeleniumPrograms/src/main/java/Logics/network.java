package Logics;


import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class network {
	static AndroidDriver driver = null;

	public static void sendkey(String Xpath, String Key) {
		driver.findElement(By.xpath(Xpath)).sendKeys(Key);
	}

	public static void click(String Xpath) {
		driver.findElement(By.xpath(Xpath)).click();
	}

	public static void main(String[] args) throws IOException, InterruptedException {

		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("automationName", "UiAutomator2");
		desiredCapabilities.setCapability("autoGrantPermissions", true);
     	desiredCapabilities.setCapability("showChromedriverLog", true);
		desiredCapabilities.setCapability("appPackage", "com.facebook.katana");
		desiredCapabilities.setCapability("appActivity","com.facebook.katana.LoginActivity");

		URL appiumServerURL = new URL("http://localhost:4723/wd/hub");

		driver = new AndroidDriver(appiumServerURL, desiredCapabilities);
		Thread.sleep(30000);
		
		System.out.println(driver.manage().logs().getAvailableLogTypes());
		
		
		driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.fireflinkLink:id/appNameTextView\" and @text=\"Tethering\"]")).click();
		driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.fireflinkLink:id/setVariableButton\"]")).click();
		driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.fireflinkLink:id/username\"]")).sendKeys("varun.testyantra@gmail.com");
		driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.fireflinkLink:id/password\"]")).sendKeys("Password@123");
		driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.fireflinkLink:id/login_button\"]")).click();
		Thread.sleep(3000);
		LogEntries logEntries = driver.manage().logs().get("logcat");

		
		
for (LogEntry logEntry : logEntries) {
	System.out.println(logEntry.getMessage());
}

System.out.println("hi");
	}
	
}
