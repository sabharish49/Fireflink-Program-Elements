package default_package;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class DragAndDropExample {

	public static AndroidDriver driver;

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("appium:platformName", "Android");
        URL url = new URL("http://localhost:4723/wd/hub");
        AndroidDriver driver = new AndroidDriver(url, dc);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("app launched");
        
        Set<String> allContentText = driver.getContextHandles();
        System.out.println("allContentText is" +allContentText);
        
        
        
        if (allContentText.size() > 1) {
            for (String context : allContentText) {
                if (context.equals("WEBVIEW_com.tatadigital.tcp")) {
                    driver.context(context); // Switch to WebView context
                    System.out.println("Switched to context: " + context);
                    driver.findElement(By.xpath("// button[text()=\"Try again\"]")).isDisplayed();
                    System.out.println("Tryagain element is displayed");
                    break;
                }
            }  
        }
      
        
        


	
		System.out.println("waited");
		List<WebElement> dragList = driver.findElements(By.xpath("//android.view.View[contains(@resource-id,'drag')]"));
		List<WebElement> dropelementsList = driver.findElements(By.xpath("//android.view.View[contains(@resource-id,'droppable')]"));
		int size = dragList.size();
		System.out.println(size);
		for (int i = 0; i < size; i++) {

			System.out.println("entered loop");
			WebElement goal = driver.findElement(By.xpath("//android.view.View[contains(@resource-id,'drag')][last()]"));
			WebElement priority = driver.findElement(By.xpath("//android.view.View[contains(@resource-id,'droppable')][last()]"));


			dragAndDropUsingSequence(goal, priority,driver);
			



		}
	}


	private static void dragAndDropUsingSequence(WebElement source, WebElement target,AndroidDriver driver ) {

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");


		int startX = source.getLocation().getX() + (source.getSize().getWidth() / 2);
		int startY = source.getLocation().getY() + (source.getSize().getHeight() / 2);
		int endX = target.getLocation().getX() + (target.getSize().getWidth() / 2);
		int endY = target.getLocation().getY() + (target.getSize().getHeight() / 2);


		Sequence dragAndDrop = new Sequence(finger, 1)
				.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY))
				.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY))
				.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));


		driver.perform(Arrays.asList(dragAndDrop));
	}
}
