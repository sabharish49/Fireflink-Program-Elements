package Practice;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class Close_all_apps {
    public static void main(String[] args) throws InterruptedException, IOException {
    	DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("noReset", "true");
        dc.setCapability("platformName", "Android");
        URL url = new URL("http://localhost:4723/wd/hub");
        AndroidDriver driver=new AndroidDriver(url,dc);
        Runtime.getRuntime().exec("adb shell input keyevent KEYCODE_APP_SWITCH"); 

        try {
            driver.findElement(By.xpath("//android.widget.Button[@text='Close all']")).click();
            System.out.println("successfully closed all apps");
		} catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("falied to closed all apps");
		}
        
//        if (isElementPresent(driver, By.xpath("//android.widget.Button[@text='Close all']"))) {
//            System.out.println("Closed all recent apps");
//        } else {
//            System.out.println("All apps are already closed");
//        }     
//    }

//    public static boolean isElementPresent(AndroidDriver driver, By locator) {
//        try {
//            driver.findElement(locator);
//            return true;
//        } catch (org.openqa.selenium.NoSuchElementException e) {
//            return false;
//        }
    }
}
