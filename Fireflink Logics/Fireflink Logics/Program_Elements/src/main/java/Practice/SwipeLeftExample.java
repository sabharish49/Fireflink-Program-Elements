package Practice;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class SwipeLeftExample {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        // Set the desired capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
        System.out.println("App launched");
        WebElement parentElement = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.View[6]"));
        WebElement childElement = null;
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
        String dayOfMonth = formatter.format(currentDate);
        String noDay="7";
        int additionalDay = Integer.parseInt(dayOfMonth) + Integer.parseInt(noDay);
        System.out.println("Updated day of the month (dd): " + additionalDay); 
        while (true) {
            try {
                System.out.println("Searching for element");
                childElement = parentElement.findElement(By.xpath("//android.view.View[contains(@content-desc,'"+additionalDay+"')]"));

                    childElement.click();
            
                break; 
            } catch (org.openqa.selenium.NoSuchElementException e) {           	
            	Thread.sleep(2000);
            	System.out.println("method called");
                Dimension size = driver.manage().window().getSize();
                int startX = (int) (size.width * 0.8); 
                int endX = (int) (size.width * 0.1); 
                int startY = parentElement.getLocation().getY() + parentElement.getSize().getHeight() / 2;
                TouchAction<?> touchAction = new TouchAction<>(driver);
                touchAction.press(PointOption.point(startX, startY))
                           .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                           .moveTo(PointOption.point(endX, startY))
                           .release()
                           .perform();
            }
        }    
        driver.quit();
    }
}
