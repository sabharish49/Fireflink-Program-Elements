package android;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class loginToChoice {

	public static void main(String[] args) throws MalformedURLException {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("platformName", "Android");
		URL url = new URL("http://localhost:4723/wd/hub");
		AndroidDriver driver = new AndroidDriver(url, dc);
		
//		List<String> stockNamesList = null;
//		while (true) {
//			List<WebElement> stockNames = driver.findElements(
//					By.xpath("//android.widget.TextView[@resource-id='com.choiceequitybroking.jiffy:id/tvScripName']"));
//			List<WebElement> stockExchange = driver.findElements(
//					By.xpath("//android.widget.TextView[@resource-id='com.choiceequitybroking.jiffy:id/tvExchangeName']"));
//			
//			for (int i = 0; i < stockNames.size(); i++) {
//				String value = stockNames.get(i).getText();		
//				stockNamesList.add(value);
//			}
//			
//			for (String string : stockNamesList) {
//				System.out.println(string);
//			}
//			break;
//			
//		}
		System.out.println("===Action started=========");
		long initialTime = System.currentTimeMillis();
		driver.findElement(By.xpath("//android.widget.TextView[@text='WELCORP']")).click();
		long finalTime=System.currentTimeMillis();
		double duration = (double) (finalTime-initialTime) / 1000.0;	
		System.out.println("Time took to perform action :"+ duration);
		System.out.println("========= Action completed===============");
		driver.quit();
		
	}

}
