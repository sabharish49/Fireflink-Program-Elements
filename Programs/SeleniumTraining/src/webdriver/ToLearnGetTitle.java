package webdriver;

import org.openqa.selenium.chrome.ChromeDriver;

public class ToLearnGetTitle {

	public static void main(String[] args) {
		ChromeDriver driver= new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		System.out.println(driver.getTitle());
		driver.get("https://www.amazon.com/");
		System.out.println(driver.getTitle());
	}

}
