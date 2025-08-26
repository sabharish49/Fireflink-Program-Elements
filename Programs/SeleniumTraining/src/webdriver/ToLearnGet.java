package webdriver;

import org.openqa.selenium.chrome.ChromeDriver;

public class ToLearnGet {

	public static void main(String[] args) {
		ChromeDriver driver= new ChromeDriver();
		driver.get("https://www.amazon.com/");
		driver.get("https://www.flipkart.com/");

	}

}
