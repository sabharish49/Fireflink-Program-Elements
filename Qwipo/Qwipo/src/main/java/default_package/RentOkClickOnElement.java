package default_package;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class RentOkClickOnElement {
    public static void main(String[] args) throws MalformedURLException {
        // Set up desired capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        
        // Set Device & Platform capabilities
        caps.setCapability("appium:platformName", "Android");
           caps.setCapability("appium:deviceName", "Galaxy A22 5G");  // Replace with actual device name if needed
        caps.setCapability("appium:appPackage", "net.eazypg.eazypgmanager");  // Your app's package name
        caps.setCapability("appium:appActivity", "net.eazypg.eazypgmanager.basemodule.view.MainFirstActivity");  // Your app's main activity name
        caps.setCapability("appium:noReset", true);  // Avoid app reset after test execution
        
        // Start Appium Driver
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        System.out.println("App Launched Successfully!");
        driver.findElement(By.xpath("(//android.widget.TextView[contains(@text,'Add Tenant')] | //android.widget.TextView[@resource-id='net.eazypg.eazypgmanager:id/tv_text'])[1]")).click();
       
        
    }
}
