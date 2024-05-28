package Practice;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;

public class AndroidSwitch {
    public static void main(String[] args) throws InterruptedException {

        DesiredCapabilities capabilitiesApp1 = new DesiredCapabilities();
        capabilitiesApp1.setCapability("platformName", "Android");
        capabilitiesApp1.setCapability("appPackage", "com.olacabs.oladriver");
        capabilitiesApp1.setCapability("appActivity", "com.olacabs.oladriver.activity.SplashActivity");
        
        DesiredCapabilities capabilitiesApp3 = new DesiredCapabilities();
        capabilitiesApp3.setCapability("platformName", "Android");
        capabilitiesApp3.setCapability("appPackage", "com.unchained.release");
        capabilitiesApp3.setCapability("appActivity", "com.unchained.MainActivity");

        DesiredCapabilities capabilitiesApp2 = new DesiredCapabilities();
        capabilitiesApp2.setCapability("platformName", "Android");
        capabilitiesApp2.setCapability("appPackage", "com.olacabs.customer");
        capabilitiesApp2.setCapability("appActivity", "com.olacabs.customer.ui.SplashActivity");

        AppiumDriver driver = null;

        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilitiesApp1);
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilitiesApp1);
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilitiesApp1);
            Thread.sleep(5000);
            driver.findElement(By.xpath("//android.widget.TextView[@text='ALLOW PERMISSION']")).click();
            driver.findElement(By.id("your_element_id_in_app1")).click();

            switchToApp(driver, "com.unchained.release");
            switchToApp(driver, "com.olacabs.customer");
            switchToApp(driver, "com.olacabs.oladriver");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
            // Remember to close the driver when done
            if (driver != null) {
                driver.quit(); 
            }
        }
    }

    private static void switchToApp(AppiumDriver driver, String appPackage) {
        if (driver instanceof InteractsWithApps) {
            ((InteractsWithApps) driver).activateApp(appPackage);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Driver does not support app activation.");
        }
    }
}
