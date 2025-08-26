package webdriver;

import org.openqa.selenium.chrome.ChromeDriver;

public class ToLearnGetPageSource {

	public static void main(String[] args) {
		ChromeDriver driver= new ChromeDriver();
		driver.get("https://www.skillrary.com/");
		String pageSource=driver.getPageSource();
		System.out.println(pageSource);
		
		
		
	}

}
