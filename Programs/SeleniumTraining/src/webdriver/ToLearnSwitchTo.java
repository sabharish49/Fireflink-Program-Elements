package webdriver;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class ToLearnSwitchTo {

	public static void main(String[] args) throws InterruptedException {
		ChromeDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://shoppersstack.com/products_page/3");
		Thread.sleep(20000);
		driver.findElement(By.id("compare")).click();
		Set<String> windows=driver.getWindowHandles();
		for (String string : windows) {
			driver.switchTo().window(string);
			if(driver.getCurrentUrl().equals("https://www.flipkart.com/"))
			{
				driver.close();
			}
		}

	}

}
