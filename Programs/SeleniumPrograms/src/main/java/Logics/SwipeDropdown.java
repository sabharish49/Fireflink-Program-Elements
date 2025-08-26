import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.LongPressOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.TouchAction;

import java.net.URL;
import java.time.Duration;

public class SwipeDropdown {
    public static void main(String[] args) {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "emulator-5554");
        caps.setCapability("appPackage", "com.example.yourapp");
        caps.setCapability("appActivity", "com.example.yourapp.MainActivity");

        try {
            AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);

            // Locate and tap the dropdown element
            MobileElement dropdown = driver.findElementById("com.example.yourapp:id/your_dropdown_element_id");
            dropdown.click();

            // Perform swipe gesture
            TouchAction touchAction = new TouchAction(driver);
            touchAction.press(PointOption.point(500, 1000))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                    .moveTo(PointOption.point(500, 500))
                    .release()
                    .perform();

            // Close the driver
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
