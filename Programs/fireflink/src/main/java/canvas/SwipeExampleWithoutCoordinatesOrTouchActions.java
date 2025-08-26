package canvas;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

public class SwipeExampleWithoutCoordinatesOrTouchActions {
    public static void main(String[] args) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "your_platform_version");
        capabilities.setCapability("deviceName", "your_device_name");
        capabilities.setCapability("app", "path_to_your_app");
        capabilities.setCapability("automationName", "XCUITest");

        // Initialize driver
        AppiumDriver<MobileElement> driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"), capabilities);

        // Example: Find start and end elements and perform a swipe
        MobileElement startElement = driver.findElementById("your_start_element_id");
        MobileElement endElement = driver.findElementById("your_end_element_id");

        // Perform swipe from start element to end element
        startElement.swipe(endElement, 1000); // Adjust duration as needed (e.g., 1000 milliseconds)

        // Close the driver session
        driver.quit();
    }
}
