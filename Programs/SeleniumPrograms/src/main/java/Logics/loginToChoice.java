package Logics;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class loginToChoice {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("platformName", "Android");
		dc.setCapability("appium:skipServerInstallation", true);
		dc.setCapability("appium:disableAndroidWatchers", true);
		dc.setCapability("appium:disableWindowAnimation", true);
		dc.setCapability("appium:waitForIdleTimeout", 10);
		dc.setCapability("appium:skipDeviceInitialization", true);
		URL url = new URL("http://localhost:4723/wd/hub");
		AndroidDriver driver = new AndroidDriver(url, dc);

		System.out.println("app launched");
		System.out.println("started");
		String strike="24,700";
		String ltpOption="Call LTP";

		Point sourceLocation=driver.findElement(By.xpath("//android.widget.TextView[@text='24,250']")).getLocation();
		Point targetLocation=driver.findElement(By.xpath("//android.widget.TextView[@text='23,800']")).getLocation();

		WebElement putElement=driver.findElement(By.xpath("//android.widget.TextView[@text='"+strike+"']/following-sibling::android.view.View[@resource-id='com.choiceequitybroking.jiffy:id/vPutClicker']"));
		WebElement callElement=driver.findElement(By.xpath("//android.widget.TextView[@text='"+strike+"']/preceding-sibling::android.view.View[@resource-id='com.choiceequitybroking.jiffy:id/vCallClicker']"));

		boolean b=true;

		boolean isClicked=false;
		int count=0;


		while(isClicked==false && count<=30) {
			if(driver.findElement(By.xpath("//android.widget.TextView[@text='"+strike+"']")).isDisplayed()){
			try
			{
				
				
				if(ltpOption.equalsIgnoreCase("Call LTP")) {
					callElement.click();
					System.out.println("clicked");
				}
				else if(ltpOption.equalsIgnoreCase("Put LTP"))
				{
					putElement.click();
					System.out.println("clicked");
				}

				isClicked=true;
				break;
			}
			catch(Exception e)
			{
				  
				swipe(sourceLocation, targetLocation, driver );
			}
			count++;
			}
			
			

		}

	}
	public static void swipe(Point sourceLocation,Point targetLocation, AndroidDriver driver )
	{
		TouchAction touchAction = new TouchAction(driver);
		touchAction.press(PointOption.point(sourceLocation)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
		.moveTo(PointOption.point(targetLocation)).release().perform();
	}
}














//		boolean isClicked=false;
//		int count=0;
//		while(isClicked==false && count<=30) {
//			try
//		      {
//		    	  WebElement putElement= driver.findElement(By.xpath("//android.widget.TextView[@text='23,800']/following-sibling::android.view.View[@resource-id='com.choiceequitybroking.jiffy:id/vPutClicker']"));
//		    	  putElement.click();
//		    	  isClicked=true;
//		      }
//		      catch(Exception e)
//		      {
//		    	  
//		      }
//			count++;




//  Point loc1 = destElement.getLocation();





//			WebElement sourceElement=driver.findElement(By.xpath("//android.widget.TextView[@text='@bandhan']"));;
//			Point loc2 = sourceElement.getLocation();
//			
//			TouchAction touchAction = new TouchAction(driver);
//			touchAction.press(PointOption.point(loc2))
//			.waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
//			.moveTo(PointOption.point(loc1))
//			.release()
//			.perform();
//		
//		
//		System.out.println("completed");





//		driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.choiceequitybroking.jiffy:id/tvUpiIds']")).click();
//		WebElement dropdown=driver.findElement(By.xpath("//android.widget.ListView"));
//
//		while(true) {
//			TouchAction touchAction = new TouchAction(driver);
//			touchAction.press(PointOption.point(677, 155))
//			.waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
//			.moveTo(PointOption.point(677, 972))
//			.release()
//			.perform(); 
//
//			if(dropdown.findElement(By.xpath("//android.widget.TextView[@text='@oksbi']")).isDisplayed())
//			{
//				dropdown.findElement(By.xpath("//android.widget.TextView[@text='@oksbi']")).click();
//
//			}
//			break;
//
//
//		}ex





