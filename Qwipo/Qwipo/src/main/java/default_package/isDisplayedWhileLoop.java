package default_package;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class isDisplayedWhileLoop {
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		 AndroidDriver driver = null;
	            DesiredCapabilities capabilities = new DesiredCapabilities();
	            capabilities.setCapability("appium:platformName", "Android");
	      //      capabilities.setCapability("appium:platformVersion", "14.0"); 
	            capabilities.setCapability("appium:deviceName", "Galaxy A13"); 
	            capabilities.setCapability("appium:appPackage", "com.c2info.wellnessforever"); 
	            capabilities.setCapability("appium:appActivity", "com.c2info.wellnessforever.ui.on_boarding.activity.SplashActivity");
	            capabilities.setCapability("appium:automationName", "UiAutomator2");
	            capabilities.setCapability("appium:noReset", true);
	           // capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
	         //   capabilities.setCapability("appium:autoGrantPermissions", true);

	            // Specify the path to the chromedriver
	       //     String chromedriverPath = System.getProperty("user.dir") + "/src/main/resources/chromedriver.exe"; // Adjust if needed
	       //     capabilities.setCapability("chromedriverExecutable", chromedriverPath);
	            
	            URL appiumServerURL = new URL("http://localhost:4723/wd/hub");
	            driver = new AndroidDriver(appiumServerURL, capabilities);

	            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		WebElement element = null;
		Boolean isDisplayed = null;
		Thread.sleep(30000);
		driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,\"productNameList\") and contains(@text,\"Dolo 650mg\")]/../..//android.widget.ImageView[@resource-id=\"com.c2info.wellnessforever:id/ivAddFavourites\"]")).click();
		int count = 0;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Toast[@text=\"Product has been added to favourites\"]")));
		System.out.println("Eleemnt is displayed");
		
		while(count<=5) {	
		try {
			element=driver.findElement(By.xpath("//android.widget.Toast[@text=\"Product has been added to favourites\"]"));
			isDisplayed = element.isDisplayed();
			isDisplayed=true;
			System.out.println("Successfully element is displayed"+isDisplayed);
		} catch (NoSuchElementException e) {
			count++;
			isDisplayed = false;
			System.out.println("Element Not found running in loop"+e);
		} catch (StaleElementReferenceException e) {
			count++;
			isDisplayed = false;
			System.out.println("Failed to locate the element"+e);
		} catch (Exception e) {
			isDisplayed = false;
			System.out.println("Failed to locate the element"+e);
			break;
		}
		}
	}
}
