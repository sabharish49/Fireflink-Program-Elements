package default_package;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class WhatsAppAutomation {

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        // Setup User A device capabilities
        DesiredCapabilities capsUserA = new DesiredCapabilities();
        capsUserA.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capsUserA.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554"); // User A device
        capsUserA.setCapability("appPackage", "com.whatsapp");
        capsUserA.setCapability("appActivity", "com.whatsapp.HomeActivity");
        capsUserA.setCapability("noReset", true);

        // Setup User B device capabilities
        DesiredCapabilities capsUserB = new DesiredCapabilities();
        capsUserB.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capsUserB.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5556"); // User B device
        capsUserB.setCapability("appPackage", "com.whatsapp");
        capsUserB.setCapability("appActivity", "com.whatsapp.HomeActivity");
        capsUserB.setCapability("noReset", true);

        // Connect to Appium server
        AndroidDriver driverUserA = new AndroidDriver(
                new URL("http://localhost:4723/wd/hub"), capsUserA);
        AndroidDriver driverUserB = new AndroidDriver(
                new URL("http://localhost:4723/wd/hub"), capsUserB);

        driverUserA.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driverUserB.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {
            // User A: Search for User B and send a message
            driverUserA.findElementByAccessibilityId("Search").click();
            MobileElement searchInputA = driverUserA.findElementById("com.whatsapp:id/search_src_text");
            searchInputA.sendKeys("User B Name"); // Replace with actual contact name

            Thread.sleep(2000);  // wait for search results

            driverUserA.findElementByXPath("//android.widget.TextView[@text='User B Name']").click();

            MobileElement messageInputA = driverUserA.findElementById("com.whatsapp:id/entry");
            String messageText = "Hello from User A!";
            messageInputA.sendKeys(messageText);

            driverUserA.findElementByAccessibilityId("Send").click();

            Thread.sleep(5000); // wait for message to be sent and received

            // User B: Search for User A and verify message
            driverUserB.findElementByAccessibilityId("Search").click();
            MobileElement searchInputB = driverUserB.findElementById("com.whatsapp:id/search_src_text");
            searchInputB.sendKeys("User A Name"); // Replace with actual contact name

            Thread.sleep(2000);

            driverUserB.findElementByXPath("//android.widget.TextView[@text='User A Name']").click();

            Thread.sleep(2000);

            List<MobileElement> messages = driverUserB.findElementsById("com.whatsapp:id/message_text");
            String lastMessage = messages.get(messages.size() - 1).getText();

            if (messageText.equals(lastMessage)) {
                System.out.println("Message received and verified successfully!");
            } else {
                System.out.println("Message verification failed!");
            }

        } finally {
            driverUserA.quit();
            driverUserB.quit();
        }
    }
}
