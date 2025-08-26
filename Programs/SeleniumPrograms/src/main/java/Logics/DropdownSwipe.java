package Logics;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class DropdownSwipe {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName", "Android");
        URL url = new URL("http://localhost:4723/wd/hub");
        AndroidDriver driver = new AndroidDriver(url, dc);
        WebElement targetElement = null;
        System.out.println("App launched");
        System.out.println("Started");

        try {
            driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.choiceequitybroking.jiffy:id/tvUpiIds']")).click();
            Thread.sleep(2000); 

            WebElement parentElement = driver.findElement(By.xpath("//android.widget.TextView[@text='@BARODAMPAY']/ancestor::android.widget.LinearLayout"));
            while (true) {
                try {
           
                    targetElement = driver.findElement(By.xpath("//android.widget.TextView[@text='@oksbi']"));
                    if (isElementVisible(driver, targetElement)) {
                        break;
                    }
                } catch (Exception e) {
                    parentElement = driver.findElement(By.xpath("//android.widget.TextView[@text='@BARODAMPAY']/ancestor::android.widget.LinearLayout"));
                   System.out.println("catched");
                    scrollMiddle(driver, parentElement);
                }
            }
            targetElement.click();
        } finally {
  
            if (driver != null) {
                driver.quit();
            }
        }
    }

    private static boolean isElementVisible(AndroidDriver driver, WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private static void scrollMiddle(AndroidDriver driver, WebElement parentElement) {
        Dimension parentSize = parentElement.getSize();
        int startX = parentSize.width / 2;
        int startY = parentSize.height / 2;

        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))) // Adjust wait time as needed
                .moveTo(PointOption.point(startX, startY - 100)) // Adjust scroll distance as needed
                .release()
                .perform();
    }
}
