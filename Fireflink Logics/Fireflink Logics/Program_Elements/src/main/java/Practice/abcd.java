package Practice;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class abcd {
public static void main(String[] args) throws MalformedURLException {
	 DesiredCapabilities capabilitiesApp1 = new DesiredCapabilities();
     capabilitiesApp1.setCapability("platformName", "Android");
     AndroidDriver  driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilitiesApp1);   
     String xpath="//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Safety and emergency\"]";
     int maxSwipe=4;
     WebDriverWait impWait= new WebDriverWait(driver, Duration.ofSeconds(2000));
     swipe(driver, xpath,maxSwipe, impWait);
}
public static void swipe(AndroidDriver driver, String xpath, int maxSwipeCount, WebDriverWait impWait) {        
    try {
        int count = 0;            
        while (driver.findElements(By.xpath(xpath)).isEmpty()) {
            Dimension screenSize = driver.manage().window().getSize();
            int width = screenSize.getWidth();
            int height = screenSize.getHeight();
            int startX = width / 2;
            int startY = (int) (height * 0.8);
            int endY = (int) (height * 0.2);    
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 0);
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX, endY));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(Arrays.asList(swipe));
            
            count++;
            if (count >= maxSwipeCount) {
                
            }
        }
        
    } catch (Exception e) {
    }
}
}
