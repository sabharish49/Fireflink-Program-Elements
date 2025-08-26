package default_package;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class DatePicker {

	public static void main(String[] args) throws InterruptedException, MalformedURLException {

		DesiredCapabilities dc = new DesiredCapabilities();
		// dc.setCapability("appium:noReset", "true");
		dc.setCapability("appium:platformName", "Android");
		//dc.setCapability("appium:autoWebview", true);
		dc.setCapability("appium:appPackage", "calculator.currencyconverter.tipcalculator.unitconverter");
		dc.setCapability("appium:appActivity", "calculator.currencyconverter.tipcalculator.unitconverter.ui.splash.SplashActivity");
		URL url = new URL("http://localhost:4723/wd/hub");
		AndroidDriver driver = new AndroidDriver(url, dc);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		System.out.println("app launched");
		Thread.sleep(1000);
		
		String individualAmtText = driver.findElement(By.xpath("//android.widget.TextView[@text='Your Order']/../following-sibling::android.widget.ScrollView/descendant::android.widget.TextView[@resource-id='com.c2info.wellnessforever:id/productShellPrice']")).getText();
		String individualAmt1 = individualAmtText.replaceAll("[^\\d.]", "");
		double individualAmt = Double.parseDouble(individualAmt1);
		System.out.println(individualAmt);
		
		driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Use coupons')]")).click();
    	driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'VIEW DETAILS')]")).isDisplayed();
		driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'VIEW DETAILS')]")).click();
        System.out.println("clicked");
        
     String descrp = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.c2info.wellnessforever:id/description']")).getText();
     String regex = "\\b\\d+\\b";
     Pattern pattern = Pattern.compile(regex);
     Matcher matcher = pattern.matcher(descrp);
     List<Integer> numbers = new ArrayList<>();
     while (matcher.find()) {
         int number = Integer.parseInt(matcher.group());
         numbers.add(number);
     }
     System.out.println("Numbers found in the text: " + numbers);
     int value = numbers.get(1);
     System.out.println(value);
     int multiplier = (int) Math.ceil(value / individualAmt);
     System.out.println(multiplier);
     System.out.println("Result after multiplication: " + (individualAmt * multiplier));
     
	}
}
