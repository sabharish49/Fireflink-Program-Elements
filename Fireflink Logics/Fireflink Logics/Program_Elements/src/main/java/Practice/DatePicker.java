package Practice;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class DatePicker {
    static int Year = 1998;

    public static void main(String[] args) throws InterruptedException, MalformedURLException {
    	
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("noReset", "true");
        dc.setCapability("platformName", "Android");
        URL url = new URL("http://localhost:4723/wd/hub");
        AndroidDriver driver = new AndroidDriver(url, dc);
        System.out.println("app launched");
        String Actualdate = "08-Feb-2000";
        String[] dateComponents = Actualdate.split("-");
        List<String> dateList = Arrays.asList(dateComponents);
        driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.apacfin.apacm:id/edtDob']")).click();
        for (int i = 0; i < dateList.size(); i++) {
        Thread.sleep(1000);
            WebElement element = driver.findElement(By.xpath("(//android.widget.LinearLayout//android.widget.EditText[@resource-id='android:id/numberpicker_input'])[" + (i + 1) + "]"));
            String name = element.getText();
            String actual = dateList.get(i);
            Point location = element.getLocation();
            Dimension size = element.getSize();
            int startX = location.getX() + size.getWidth() / 2;
            int startY = location.getY() + size.getHeight() / 2;
            int endY = startY + 100;
            while (!name.equalsIgnoreCase(actual)) {
            	TouchAction<?> additionalScroll = new TouchAction<>(driver);
            	additionalScroll.press(PointOption.point(startX, startY))
            	               .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
            	               .moveTo(PointOption.point(startX, endY)) // Scroll a little more
            	               .release()
            	               .perform();           
                name = element.getText();
            }
            if (name.equalsIgnoreCase(actual)) {
                System.out.println("Actual date component found: " + actual);
            }
        }
        driver.findElement(By.xpath("//android.widget.Button[@text='OK']")).click();

    }
}
