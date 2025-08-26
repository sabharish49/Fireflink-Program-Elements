package default_package;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class DragAndDropExample2 {

	public static AndroidDriver driver;

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		System.out.println("Method called");
	  	String str1="-20";
		CharSequence substringToReplace="-";
		String replacedstr1 = str1.replace(substringToReplace, "");
     	int minusDate = Integer.parseInt(replacedstr1);
    	//driver.findElement(By.xpath("//label[contains(text(),'"+calenderName+"')]/..//input")).click();
  		LocalDate day=LocalDate.now();
  		int calenderDate=day.minusDays(minusDate).getDayOfMonth();
  		String month=day.minusDays(minusDate).getMonth().toString();
  		month=month.substring(0,1)+month.substring(1).toLowerCase();
  		System.out.println(month);
		DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("appium:platformName", "Android");
        URL url = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver(url, dc);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("app launched");
        
    	int yearfewYearsAgo = 0;
    	int maxSwipeCount = 10;
    	int Age=10;
    	int yearLimit =2024;
    	

    	Calendar calendar = Calendar.getInstance();
    	int currentYear = calendar.get(Calendar.YEAR);

    	yearfewYearsAgo=currentYear-Age;
    	System.out.println("Year to swipe is " +yearfewYearsAgo);
    	
    	if(yearfewYearsAgo>yearLimit) {
    		System.out.println("Restricted the year");
    	}
    	
    	
    	//driver.findElement(By.xpath("//android.view.View[@content-desc=\"User form\"]/android.widget.Button")).click();
    	if(driver!=null)
    	{
    		WebElement element = driver.findElement(By.xpath(
    				"//android.widget.Button[contains(@content-desc,'Select')]/android.view.View/android.view.View"));
    		int x = element.getLocation().getX();
    		int y = element.getLocation().getY();
    		int width = element.getSize().getWidth();
    		int height = element.getSize().getHeight();
    		int startX = (int) (x + (width * 0.5));
    		int startY = (int) (y + (height * 0.8));
    		int endX = (int) (x + width);
    		int endY = (int) (y + (height * 0.5));

    		int centerX = 457;
    		int centerY = 855;
    		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

    		Sequence tap = new Sequence(finger, 1)
    				.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, centerY))
    				.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
    				.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
    		driver.perform(Arrays.asList(tap));


    		System.out.println("Started Method Executing");
    		selectDate(startX, startY, endX, endY, maxSwipeCount, yearfewYearsAgo);
    		System.out.println("End Method Executing");
    	}
    
    }

    	public static void selectDate(int startX, int startY, int endX, int endY, int maxSwipeCount, int yearfewYearsAgo) {
    		for (int i = 0; i < maxSwipeCount;) {
    			
				PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

    			// Define a swipe action sequence
    			Sequence swipe = new Sequence(finger1, 1)
    					.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), endX, endY))
    					.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg())).addAction(finger1
    							.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), startX, startY))
    					.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

    		
    				driver.perform(Arrays.asList(swipe));

    			    			try {
    				if (driver != null) {
    					driver.findElement(By.xpath("//android.widget.Button[@content-desc='" + yearfewYearsAgo + "']"))
    							.isDisplayed();
    					driver
    							.findElement(By.xpath("//android.widget.Button[@content-desc='" + yearfewYearsAgo + "']"))
    							.click();
    					driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"OK\"]")).click();
    				    System.out.println("Selected The Date");
    					break;

    				} 
    				else {
    					System.out.println("It is in IOS checking the element");
    					break;
    				}

    			} catch (NoSuchElementException e) {
    			
    				System.out.println("Element Not Found");
    				i++;

    			} catch (Exception e) {
    				System.out.println("Execption is occured " + e);
    			}

    		}
        
	}
}
