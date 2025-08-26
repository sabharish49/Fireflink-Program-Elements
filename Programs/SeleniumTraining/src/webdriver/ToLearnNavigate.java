package webdriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeDriver;

public class ToLearnNavigate {

	public static void main(String[] args) throws InterruptedException, MalformedURLException {
		ChromeDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://www.skillrary.com/");
		Thread.sleep(2000);
		driver.navigate().refresh();
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().to(new URL("https://www.flipkart.com/"));
		driver.quit();
		

	}

}
