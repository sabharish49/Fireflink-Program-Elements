package default_package;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.StringReader;
import org.xml.sax.InputSource;

import io.appium.java_client.android.AndroidDriver;

public class getPageSource {
	public static String expectedError = "Product has been removed from favourites";

	public static void main(String[] args) throws InterruptedException, MalformedURLException {
		 AndroidDriver driver = null;
         DesiredCapabilities capabilities = new DesiredCapabilities();
         capabilities.setCapability("appium:platformName", "Android");
         capabilities.setCapability("appium:deviceName", "Galaxy A13"); 
         capabilities.setCapability("appium:appPackage", "com.c2info.wellnessforever"); 
         capabilities.setCapability("appium:appActivity", "com.c2info.wellnessforever.ui.on_boarding.activity.SplashActivity");
         capabilities.setCapability("appium:noReset", true);
         
         URL appiumServerURL = new URL("http://localhost:4723/wd/hub");
         driver = new AndroidDriver(appiumServerURL, capabilities);

         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			System.out.println("Application launched");
			Thread.sleep(20000);
			System.out.println("Waited");
			
			driver.findElement(By.xpath("//android.widget.TextView[contains(@resource-id,\"productNameList\") and contains(@text,\"Dolo 650mg\")]/../..//android.widget.ImageView[@resource-id=\"com.c2info.wellnessforever:id/ivAddFavourites\"]")).click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Toast[@text=\"Product has been added to favourites\"]")));
			WebElement toasterMessage = driver.findElement(By.xpath("//android.widget.Toast[@text=\"Product has been removed from favourites\"]"));
			wait.until(ExpectedConditions.domAttributeToBe(toasterMessage, "text", expectedError));

        //    Thread.sleep(2000);
			String pageData = driver.getPageSource();
			System.out.println("pageData is" +pageData);
			if (expectedError.equals(getErrorData(pageData))) {
				System.out.println(expectedError +"Is displayed ");
				System.out.println("Error is displayed");
			}else {
				System.out.println("Error is not displayed");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static String getErrorData(String pageData) {
		
		String errorMessage="";

		try {

			// Parse the XML string
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(pageData)));

			// Get the Toast element that contains the error message
			NodeList toastNodes = document.getElementsByTagName("android.widget.Toast");
			System.out.println("Size of toast tag name is" +toastNodes.getLength());
			for (int i = 0; i < toastNodes.getLength(); i++) {
				Node toastNode = toastNodes.item(i);
				Element toastElement = (Element) toastNode;
				errorMessage = toastElement.getAttribute("text");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return errorMessage;
	}

}