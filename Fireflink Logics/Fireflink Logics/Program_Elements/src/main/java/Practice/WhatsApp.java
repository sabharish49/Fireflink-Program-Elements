package Practice;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class WhatsApp {
    @SuppressWarnings("unlikely-arg-type")
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilitiesApp1 = new DesiredCapabilities();
        capabilitiesApp1.setCapability("platformName", "Android");
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilitiesApp1);
        Thread.sleep(5000);		
        int swipeCount = 2;
        ArrayList<String> list = new ArrayList<>();
        boolean shouldContinue = true; 
     while (shouldContinue) {
            for (int i = 1; i <=10 ; i++) {            	
                String title = driver.findElement(By.xpath("(//android.widget.TextView[@resource-id='com.whatsapp:id/top_message'])[last()]")).getText();
                list.add(title);
                System.out.println(list);
                Dimension screenSize = driver.manage().window().getSize();
                int screenCenter = (int) (screenSize.getWidth() * 0.5);
                int startPoint = (int) (screenSize.getHeight() * 0.5);
                int endPoint = (int) (screenSize.getHeight() * 0.2);
                int count = 0;            
                PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
                while (driver.findElements(By.xpath("//android.widget.TextView[@content-desc=\"You're receiving messages from this business.\"]"))
                        .isEmpty()) {
                    Sequence swipe = new Sequence(finger, 1);
                    swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(), screenCenter, startPoint));
                    swipe.addAction(finger.createPointerDown(0));
                    swipe.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(), screenCenter, endPoint));
                    swipe.addAction(finger.createPointerUp(0));
                    driver.perform(Arrays.asList(swipe));
                    if (++count >= swipeCount) {
                        break;
                    }
                }
                try {
                    WebElement breakElement = driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"You're receiving messages from this business.\"]"));
                    if (breakElement.isDisplayed()) {
                        shouldContinue = false;
                        String title1 = driver.findElement(By.xpath("(//android.widget.TextView[@resource-id='com.whatsapp:id/top_message'])[last()]")).getText();
                        System.out.println(title1);
                         if(list.contains(title1)) {
                        	 
                         }else {
                        	list.add(title1); 
                         }
                        break; 
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        System.out.println(list.size());
    }
     System.out.println("exicution completed");
}
}
