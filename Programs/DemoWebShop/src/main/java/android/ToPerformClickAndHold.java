package android;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ToPerformClickAndHold {

	public static void main(String[] args) throws InterruptedException {
		Map<String, Object> deviceMetrics = new HashMap<>();
		deviceMetrics.put("width", 1000);
		deviceMetrics.put("height", 500);
		deviceMetrics.put("pixelRatio", 3.0);
		Map<String, Object> mobileEmulation = new HashMap<>();
		mobileEmulation.put("deviceMetrics", deviceMetrics);
		mobileEmulation.put("userAgent",
		"Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");
		Map<String, Object> clientHints = new HashMap<>();
		clientHints.put("platform", "Android");
		clientHints.put("mobile", true);
		mobileEmulation.put("clientHints", clientHints);
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setAcceptInsecureCerts(true);
		chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
		WebDriver driver= new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();
		driver.get("https://staging.gatekeeper.scm.gomercury.in/app/requests/all");
		
	//	driver.findElement(By.xpath("//span[text()='Login']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@id='username']")).sendKeys("naina.fireflink");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("fireflink");
			driver.findElement(By.xpath("//input[@value=\"Sign In\"]")).click();
			
    		Actions ac= new Actions(driver);
//			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
			//Thread.sleep(5000);
		//	driver.findElement(By.xpath("//button[contains(.,'Create New Request')]")).click();
		    //wait.until(ExpectedConditions.);
//			
//			driver.findElement(By.xpath("//img[@alt=\"Cart\"]")
			Thread.sleep(10000);
		ac.moveToElement(driver.findElement(By.xpath("//label[contains(.,'Requests')]/../.."))).click().perform();
		System.out.println();
			System.out.println(driver.findElement(By.xpath("//label[contains(.,'Requests')]/../..")).getLocation().getX() +"**************************************************");
			System.out.println(driver.findElement(By.xpath("//label[contains(.,'Requests')]/../..")).getLocation().getY()+"****************************************");

	}

}
