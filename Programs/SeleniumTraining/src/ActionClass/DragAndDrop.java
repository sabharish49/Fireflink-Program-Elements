package ActionClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DragAndDrop {
	
	public static void main(String[] args) {
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.globalsqa.com/demo-site/draganddrop/#Photo%20Manager");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);
		WebElement frame = driver.findElement(By.xpath("//iframe[contains(@data-src,'demoSite/practice/droppable/photo')]"));
		driver.switchTo().frame(frame);
		WebElement src=driver.findElement(By.xpath("//img[@alt='The peaks of High Tatras']"));
		WebElement dest=driver.findElement(By.id("trash"));
		action.dragAndDrop(src, dest).perform();
		driver.switchTo().defaultContent();
		driver.quit();

	}
}
