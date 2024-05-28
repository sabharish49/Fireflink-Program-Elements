package Practice;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

public class SwipeExample {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
        System.out.println("App launched");
        Thread.sleep(5000);
        String Date = "November-25-2023";
        String[] parts = Date.split("-");
        int k = 1;
        List<String> dateList = Arrays.asList(parts);
        while (true) {
            if (k == 4) {
            	 WebElement ele = driver.findElement(By.xpath("(//android.view.View[@content-desc=\"Okay\"]//android.widget.SeekBar)[1]"));
                 int bottomY = ele.getLocation().getY() + ele.getSize().getHeight()+100;
                 System.out.println("Bottom coordinate Y: " + bottomY);
                int x = ele.getLocation().getX();
                Thread.sleep(2000);
                TouchAction<?> touchAction = new TouchAction<>(driver);
                touchAction.tap(PointOption.point(x, bottomY)).perform();
                System.out.println("click performed");
                break;
            }
            String month = dateList.get(0);
            String day = dateList.get(1);
            String year = dateList.get(2);
            System.out.println("k value"+k);
       	 WebElement ele = driver.findElement(By.xpath("(//android.view.View[@content-desc=\"Okay\"]//android.widget.SeekBar)["+k+"]"));

            String contentDesc = ele.getAttribute("content-desc");
            System.out.println("Content Description: " + contentDesc);
            if (contentDesc.equals(month) || contentDesc.equals(day) || contentDesc.equals(year)) {
                k++;
                continue;
            }
            int centerX = ele.getLocation().getX() + ele.getSize().getWidth() / 2;
            int centerY = ele.getLocation().getY() + ele.getSize().getHeight() / 2;
            int swipeDistance = 100;
            TouchAction<?> touchAction1 = new TouchAction<>(driver);
            touchAction1.press(PointOption.point(centerX, centerY - swipeDistance))
                       .moveTo(PointOption.point(centerX, centerY))
                       .release()
                       .perform();
            System.out.println("Swiped down");
        }
        driver.quit();
    }
}
