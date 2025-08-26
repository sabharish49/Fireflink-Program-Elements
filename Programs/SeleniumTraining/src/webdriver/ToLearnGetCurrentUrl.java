package webdriver;

import org.openqa.selenium.chrome.ChromeDriver;

public class ToLearnGetCurrentUrl {
	public static void main(String[] args) {
		ChromeDriver driver= new ChromeDriver();
		driver.get("https://www.amazon.com/");
		String url=driver.getCurrentUrl();
		System.out.println(url);
		if (url.equals("https://www.amazon.com/")) {
			System.out.println("user navigated to correct url");
			
		}
		else
		{
			System.out.println("need to navigate to correct url");
		}
		driver.get("https://www.flipkart.com/");
		String url1=driver.getCurrentUrl();
		System.out.println(url1);
		if (url1.equals("https://www.flipkart.com/")) {
			System.out.println("user navigated to correct url");
			
		}
		else
		{
			System.out.println("need to navigate to correct url");
		}
	}
	
}


