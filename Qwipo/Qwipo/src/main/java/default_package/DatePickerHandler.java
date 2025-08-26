package default_package;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class DatePickerHandler {

    private static AndroidDriver driver;

    public DatePickerHandler(AndroidDriver driver) {
        this.driver = driver;
    }

    
    public static void scroll(AndroidDriver driver, int startX, int startY, int endX, int endY) {
        // Create a pointer input for touch gestures
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        // Define a swipe action sequence
        Sequence swipe = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Perform the swipe
        driver.perform(Arrays.asList(swipe));
    }
    
    public void selectDate(String targetMonth, String targetDay, String targetYear) {
        
        WebElement monthElement = driver.findElement(By.xpath("//android.widget.ListView[@text='Month']//android.view.View[@text='"+targetMonth+"']")); 
        WebElement dayElement = driver.findElement(By.xpath("//android.widget.ListView[@text='Day']//android.view.View[@text='"+targetDay+"']")); 
        WebElement yearElement = driver.findElement(By.xpath("//android.widget.ListView[@text='Year']//android.view.View[@text='"+targetYear+"']")); 
        System.out.println(yearElement.getText());
    }
//        while (!yearElement.getText().equals(targetYear)) {
//        	
//        	scroll(yearElement, false);
//        }
//        
//        
//        while (!monthElement.getText().equals(targetMonth)) {
//            swipeElement(monthElement, true); 
//        }
//
//        
//        while (!dayElement.getText().equals(targetDay)) {
//            swipeElement(dayElement, true); 
//        }
//
//      
//
//        
//        driver.findElement(By.xpath("//android.widget.Button[@text='Set']")).click(); 
//    }

    public static void main(String[] args) throws MalformedURLException {
       
    	DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("appium:noReset", "true");
        dc.setCapability("appium:platformName", "Android");
        URL url = new URL("http://localhost:4723/wd/hub");
        AndroidDriver driver = new AndroidDriver(url, dc);
        System.out.println("app launched");
       
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the month ");
        String targetMonth = scanner.nextLine();

        System.out.println("Enter the day");
        String targetDay = scanner.nextLine();

        System.out.println("Enter the year");
        String targetYear = scanner.nextLine();

       
        DatePickerHandler datePicker = new DatePickerHandler(driver);
        datePicker.selectDate(targetMonth, targetDay, targetYear);
        
        scanner.close();
    }
}
