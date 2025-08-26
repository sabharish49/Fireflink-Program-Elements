package webdriver;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class ToLearnGetWindowHandles {
	public static void main(String[] args) throws InterruptedException {
		ChromeDriver driver= new ChromeDriver();
		driver.get("https://shoppersstack.com/products_page/3");
		Thread.sleep(20000);
		driver.findElement(By.id("compare")).click();
		Set<String> windows=driver.getWindowHandles();
		for (String string : windows) {
			System.out.println(string);
		}
	}

}
