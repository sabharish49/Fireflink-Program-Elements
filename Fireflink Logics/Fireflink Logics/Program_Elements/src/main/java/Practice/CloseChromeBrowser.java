package Practice;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class CloseChromeBrowser {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        // Define desired capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Your_Device_Name");
        capabilities.setCapability("browserName", "Chrome");

        // Initialize the Appium driver
        WebDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        

        // Navigate to a webpage
        driver.get("https://www.amazon.in/");
        System.out.println("successfully launched browser");

        // Wait for some time to see the webpage
        Thread.sleep(5000);

        // Close the browser
        driver.quit();
    }
}
