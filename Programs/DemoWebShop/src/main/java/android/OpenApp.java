package android;
	import io.appium.java_client.AppiumDriver;
	import io.appium.java_client.MobileElement;
	import io.appium.java_client.android.AndroidDriver;
	import io.appium.java_client.remote.MobileCapabilityType;
	import org.openqa.selenium.remote.DesiredCapabilities;

	import java.net.MalformedURLException;
	import java.net.URL;

	public class OpenApp {

	    public static void main(String[] args) {
	        AppiumDriver<MobileElement> driver = null;

	        try {
	            // Set the Appium server URL
	            URL appiumServerURL = new URL("http://localhost:4723/wd/hub");

	            // Set desired capabilities for the Android device
	            DesiredCapabilities capabilities = new DesiredCapabilities();
	            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
	            capabilities.setCapability(MobileCapabilityType.UDID, "RZ8T902J6BL"); // Replace with your device's UDID
	            capabilities.setCapability(MobileCapabilityType.APP, "com.hsl.adbas"); // Replace with your app's package name
	            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "Galaxy A13"); // Replace with your device's Android version
	            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2"); // Use UiAutomator2 for Android

	            // Create an Android driver instance
	            driver = new AndroidDriver<>(appiumServerURL, capabilities);

	            System.out.println("App opened successfully!");

	            // Your test logic goes here

	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } finally {
	            // Close the driver session after the test
	            if (driver != null) {
	                driver.quit();
	            }
	        }
	    }
	}
