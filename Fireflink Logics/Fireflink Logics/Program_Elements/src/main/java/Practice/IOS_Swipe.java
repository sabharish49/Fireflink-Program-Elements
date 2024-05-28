package Practice;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class IOS_Swipe {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilitiesApp1 = new DesiredCapabilities();
        capabilitiesApp1.setCapability("platformName", "Android");  
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilitiesApp1);
        Thread.sleep(5000);
        
        WebElement element = findElement(driver, By.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Advanced features\"]"));
        
        if (element != null) {
            element.click();
        } else {
            System.out.println("Element not found or not visible.");
        }
        
        driver.quit();
    }
    
    public static WebElement findElement(AndroidDriver driver, By locator) {
        WebElement element = null;
        int attempts = 0;
        while (element == null && attempts < 5) {
            try {
                element = driver.findElement(locator);
                if (!element.isDisplayed()) {
                    swipeUp(driver);
                }
            } catch (Exception e) {
                swipeUp(driver);
            }
            attempts++;
        }
        return element;
    }

    public static void swipeUp(AndroidDriver driver) {
        int height = driver.manage().window().getSize().getHeight();
        int width = driver.manage().window().getSize().getWidth();
        int startX = width / 2;
        int startY = (int) (height * 0.8);
        int endY = (int) (height * 0.2);
        TouchAction<?> action = new TouchAction<>(driver);
        action.press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
    }
}
